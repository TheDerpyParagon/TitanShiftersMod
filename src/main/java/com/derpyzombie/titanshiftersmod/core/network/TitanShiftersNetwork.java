package com.derpyzombie.titanshiftersmod.core.network;

import java.util.Objects;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.core.network.message.ClientMessage;
import com.derpyzombie.titanshiftersmod.core.network.message.InputMessage;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class TitanShiftersNetwork {
	
	private static int id = 0;
	
	public static final String NETWORK_VERSION = "0.1.0";
	
	private static ResourceLocation loc = new ResourceLocation(TitanShiftersMod.MOD_ID, "network");
	private static ResourceLocation Clientloc = new ResourceLocation(TitanShiftersMod.MOD_ID, "networkclient");
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(loc, () -> NETWORK_VERSION, version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));
	
	public static final SimpleChannel CLIENTCHANNEL = NetworkRegistry.ChannelBuilder.named(Clientloc).clientAcceptedVersions(s -> Objects.equals(s, "1"))
			.serverAcceptedVersions(s -> Objects.equals(s, "1")).networkProtocolVersion(() -> "1").simpleChannel();
	
	public static void init() {
		
		CHANNEL.registerMessage(0, InputMessage.class, InputMessage::encode, InputMessage::decode, InputMessage::handle);
		CLIENTCHANNEL.messageBuilder(ClientMessage.class, id++).decoder(ClientMessage::decode).encoder(ClientMessage::encode).consumer(ClientMessage::handle).add();
		
	}
}
	