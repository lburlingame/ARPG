package com.haruham.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.haruham.game.GameApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
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
            config.width = 1280;
            config.height = 720;
        }

        config.fullscreen = fullscreen;
        config.resizable = !fullscreen;


        config.title = GameApp.TITLE;
        config.vSyncEnabled = true;



        new LwjglApplication(new GameApp(), config);
	}
}