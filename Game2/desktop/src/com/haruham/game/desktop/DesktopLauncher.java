package com.haruham.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.haruham.game.GameApp;

public class DesktopLauncher {
	public static void main (String[] arg) throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

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
/*        config.foregroundFPS = 180;
        config.backgroundFPS = 180;*/
        config.addIcon("other/icon32.png", Files.FileType.Internal);
        config.addIcon("other/icon64.png", Files.FileType.Internal);
        config.addIcon("other/icon128.png", Files.FileType.Internal);
        // System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");


        LwjglApplication lwjgl = new LwjglApplication(new GameApp(), config);

	}
}
