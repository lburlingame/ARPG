package com.patts.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.patts.game.GameApp;

public class DesktopLauncher {
	public static void main (String[] arg) throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
/*

    config.addIcon("assets/icon-256.png", FileType.Internal);
        config.addIcon("assets/icon-64.png", FileType.Internal);
        config.addIcon("assets/icon-32.png", FileType.Internal);
 */

        boolean fullscreen = false;
        /*fullscreen = true;*/

        if (fullscreen) {
            config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());

        }else{
            config.width = 1280;  // 1280
            config.height = 720;  //720
        }

        config.fullscreen = fullscreen;
        config.resizable = false;


        config.title = GameApp.TITLE;
        config.vSyncEnabled = true;

       // System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

        /*config.foregroundFPS = 0;
        config.backgroundFPS = 0;*/



        LwjglApplication lwjgl = new LwjglApplication(new GameApp(), config);


	}
}