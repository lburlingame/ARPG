package com.haruham.game.net.client;


import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryonet.Listener;
import com.haruham.game.net.PacketUpdateX;
import com.haruham.game.net.PacketUpdateY;


public class ClientProgram extends Listener {

	private Player player;
	private Network network;
	Map<Integer,MPPlayer> players;

	public ClientProgram() {
		player  = new Player();
		network = new Network(this);
		players = new HashMap<Integer,MPPlayer>();
	}
	
	public void update(float delta){
		player.update();
		
		//Update position
		if(player.networkPosition.x != player.position.x){
			//Send the player's X value
			PacketUpdateX packet = new PacketUpdateX();
			packet.x = player.position.x;
			network.sendUDP(packet);
			
			player.networkPosition.x = player.position.x;
		}
		if(player.networkPosition.y != player.position.y){
			//Send the player's Y value
			PacketUpdateY packet = new PacketUpdateY();
			packet.y = player.position.y;
			network.sendUDP(packet);
			
			player.networkPosition.y = player.position.y;
		}
	}
	
	public void render(){

		for(MPPlayer mpPlayer : players.values()){

		}

	}
}
