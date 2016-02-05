package com.haruham.game.net.client;


import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Listener;
import com.haruham.game.net.PacketUpdateX;
import com.haruham.game.net.PacketUpdateY;


public class ClientProgram extends Listener {

	private Network network;

	private Player player;
	Map<Integer,MPPlayer> players;

	public ClientProgram() {
		player  = new Player();
		network = new Network(this);
		network.connect("hi");
		players = new HashMap<Integer,MPPlayer>();
	}
	
	public void update(float delta) {
		player.update();
		
		//Update position
		if(player.netpos.x != player.pos.x) {
			//Send the player's X value
			PacketUpdateX packet = new PacketUpdateX();
			packet.x = player.pos.x;
			network.sendUDP(packet);
			
			player.netpos.x = player.pos.x;
		}
		if(player.netpos.y != player.pos.y) {
			//Send the player's Y value
			PacketUpdateY packet = new PacketUpdateY();
			packet.y = player.pos.y;
			network.sendUDP(packet);

			player.netpos.y = player.pos.y;
		}
	}
	
	public void render() {

		for(MPPlayer mpPlayer : players.values()) {

		}

	}
}
