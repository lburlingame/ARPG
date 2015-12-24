package com.patts.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.patts.game.obj.Character;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LightRenderer {

	public final int width = 200;
	public final int height = 200;

	public ArrayList<Light> lights = new ArrayList<Light>();
	public ArrayList<Block> blocks = new ArrayList<Block>();

    private ArrayList<com.patts.game.obj.Character> lightEntities = new ArrayList<Character>();
    private ArrayList<Character> blockEntities = new ArrayList<Character>();

	private int fragmentShader;
	private int shaderProgram;



	public void render(OrthographicCamera camera) {
		//glClear(GL_COLOR_BUFFER_BIT);
		for (Character light : lightEntities) {
			glColorMask(false, false, false, false);
			glStencilFunc(GL_ALWAYS, 1, 1);
			glStencilOp(GL_KEEP, GL_KEEP, GL_REPLACE);

			for (Character block : blockEntities) {
				Vector2[] vertices = block.getVertices();
				for (int i = 0; i < vertices.length; i++) {
					Vector2 nextVertex = vertices[(i + 1) % vertices.length];
                    Vector2 currentVertex = vertices[i];
                    Vector2 edge = nextVertex.sub(currentVertex);
					Vector2 normal = new Vector2(edge.y, -edge.x);
					Vector2 lightToCurrent = currentVertex.sub(new Vector2(light.getPosition().x, light.getPosition().y));
					if (normal.dot(lightToCurrent) > 0) {
						Vector2 point1 = currentVertex.add(currentVertex.sub(new Vector2(light.getPosition().x, light.getPosition().y)).scl(800));
						Vector2 point2 = nextVertex.add(nextVertex.sub(new Vector2(light.getPosition().x, light.getPosition().y)).scl(800));
						glBegin(GL_QUADS); {
							glVertex2f(currentVertex.x, currentVertex.y);
							glVertex2f(point1.x, point1.y);
							glVertex2f(point2.x, point2.y);
							glVertex2f(nextVertex.x, nextVertex.y);
						} glEnd();
					}
				}
			}

			glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
			glStencilFunc(GL_EQUAL, 0, 1);
			glColorMask(true, true, true, true);

			glUseProgram(shaderProgram);

            /*glUniform2f(glGetUniformLocation(shaderProgram, "lightLocation"), light.location.x, height - light.location.y);
            glUniform3f(glGetUniformLocation(shaderProgram, "lightColor"), light.red, light.green, light.blue);*/
            Vector3 lightpos = camera.project(light.getPosition());
			glUniform2f(glGetUniformLocation(shaderProgram, "lightLocation"), lightpos.x, lightpos.y + light.getZ());
			glUniform3f(glGetUniformLocation(shaderProgram, "lightColor"), 50, 50, 50);
			glEnable(GL_BLEND);
			glBlendFunc(GL_ONE, GL_ONE);

			glBegin(GL_QUADS); {
				glVertex2f(0, 0);
				glVertex2f(0, height);
				glVertex2f(width, height);
				glVertex2f(width, 0);
			} glEnd();
            // nothing
			glDisable(GL_BLEND);
			glUseProgram(0);
			glClear(GL_STENCIL_BUFFER_BIT);
		}
		glColor3f(0, 0, 0);
		for (Character block : blockEntities) {
			glBegin(GL_QUADS); {
				for (Vector2 vertex : block.getVertices()) {
					glVertex2f(vertex.x, vertex.y);
				}
			} glEnd();
		}
		//Display.update();
		//Display.sync(60);
	}

	private void setUpObjects() {
		int lightCount = 5;
		int blockCount = 15;

		for (int i = 1; i <= lightCount; i++) {
			Vector2 location = new Vector2((float) Math.random() * width, (float) Math.random() * height);
			lights.add(new Light(location, (float) Math.random() * 10, (float) Math.random() * 10, (float) Math.random() * 10));
		}

		for (int i = 1; i <= blockCount; i++) {
			int width = 50;
			int height = 50;
			int x = (int) (Math.random() * (this.width - width));
			int y = (int) (Math.random() * (this.height - height));
			blocks.add(new Block(x, y, width, height));
		}
	}

	private void initialize() {
		shaderProgram = glCreateProgram();
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		StringBuilder fragmentShaderSource = new StringBuilder();

		try {
			String line;
            FileHandle file = Gdx.files.internal("shaders/lights.frag");
            BufferedReader reader = file.reader(1);
			while ((line = reader.readLine()) != null) {
				fragmentShaderSource.append(line).append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Fragment shader not compiled!");
		}

		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);


		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_STENCIL_TEST);
		//glClearColor(0, 0, 0, 0);
	}

	private void cleanup() {
		glDeleteShader(fragmentShader);
		glDeleteProgram(shaderProgram);
	}

    public LightRenderer() {
        setUpObjects();
        initialize();
    }

    public void dispose() {
        cleanup();
    }

    public void addLight(Character character) {
        lightEntities.add(character);
    }

    public void removeLight(Character character) {
        lightEntities.remove(character);
    }

    public void addBlock(Character character) {
        blockEntities.add(character);
    }

    public void removeBlock(Character character) {
        blockEntities.remove(character);
    }



}