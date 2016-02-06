package com.haruham.game.net.client;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.haruham.game.net.PacketAddPlayer;
import com.haruham.game.net.PacketRemovePlayer;
import com.haruham.game.net.PacketUpdateX;
import com.haruham.game.net.PacketUpdateY;


public class Network extends Listener {

	private ClientProgram clientProgram;
	private Client client;
	private int port = 27960;

	public Network(ClientProgram clientProgram) {
		this.clientProgram = clientProgram;
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
			clientProgram.players.put(packet.id, newPlayer);
			
		}else if(o instanceof PacketRemovePlayer) {
			PacketRemovePlayer packet = (PacketRemovePlayer) o;
			clientProgram.players.remove(packet.id);
			
		}else if(o instanceof PacketUpdateX) {
			PacketUpdateX packet = (PacketUpdateX) o;
			clientProgram.players.get(packet.id).x = packet.x;
			
		}else if(o instanceof PacketUpdateY) {
			PacketUpdateY packet = (PacketUpdateY) o;
			clientProgram.players.get(packet.id).y = packet.y;
		}
	}

	public void sendUDP(Object object) {
		client.sendUDP(object);
	}
}
