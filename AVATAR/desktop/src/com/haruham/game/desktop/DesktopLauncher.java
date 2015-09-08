package com.haruham.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.haruham.game.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 1280;
        config.height = 720;
        config.title = "2D RPG";

        config.vSyncEnabled = true;
        //config.resizable = false;

        new LwjglApplication(new GameMain(), config);
	}
}
