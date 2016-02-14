package com.haruham.game.net.client;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.haruham.game.net.PacketAddPlayer;
import com.haruham.game.net.PacketRemovePlayer;
import com.haruham.game.net.PacketUpdateX;
import com.haruham.game.net.PacketUpdateY;


public class ClientProgram extends Listener {

    private Client client;
    private int port = 27960;
	private Player player;
	private Map<Integer,MPPlayer> players;

    public ClientProgram() {
        init();
    }

	public ClientProgram(String ip) {
        init();
        connect(ip);
    }

    private void init() {
        player  = new Player();
        players = new HashMap<Integer,MPPlayer>();
    }

    public void connect(String ip) {
        client = new Client();
        client.getKryo().register(PacketUpdateX.class);
        client.getKryo().register(PacketUpdateY.class);
        client.getKryo().register(PacketAddPlayer.class);
        client.getKryo().register(PacketRemovePlayer.class);
        client.addListener(this);

        client.start();
        try {
            client.connect(5000, ip, port, port);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void received(Connection c, Object o) {
        if(o instanceof PacketAddPlayer) {
            PacketAddPlayer packet = (PacketAddPlayer) o;
            MPPlayer newPlayer = new MPPlayer();
            players.put(packet.id, newPlayer);

        }else if(o instanceof PacketRemovePlayer) {
            PacketRemovePlayer packet = (PacketRemovePlayer) o;
            players.remove(packet.id);

        }else if(o instanceof PacketUpdateX) {
            PacketUpdateX packet = (PacketUpdateX) o;
            players.get(packet.id).x = packet.x;

        }else if(o instanceof PacketUpdateY) {
            PacketUpdateY packet = (PacketUpdateY) o;
            players.get(packet.id).y = packet.y;
        }
    }

	public void update(float delta) {
		player.update();
		
		//Update position
		if(player.netpos.x != player.pos.x) {
			//Send the player's X value
			PacketUpdateX packet = new PacketUpdateX();
			packet.x = player.pos.x;
			client.sendUDP(packet);
			
			player.netpos.x = player.pos.x;
		}
		if(player.netpos.y != player.pos.y) {
			//Send the player's Y value
			PacketUpdateY packet = new PacketUpdateY();
			packet.y = player.pos.y;
			client.sendUDP(packet);

			player.netpos.y = player.pos.y;
		}
	}
	
	public void render() {

		for(MPPlayer mpPlayer : players.values()) {

		}

	}


}
