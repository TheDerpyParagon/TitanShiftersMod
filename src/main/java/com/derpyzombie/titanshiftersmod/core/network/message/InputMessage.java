package com.derpyzombie.titanshiftersmod.core.network.message;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class InputMessage {
	
	private final int entNum;
	
	public InputMessage(int n) {
		entNum = n;
	}
	
	public static void encode(InputMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.entNum);
	}
	
	public static InputMessage decode(PacketBuffer buffer) {
		return new InputMessage(buffer.readInt());
	}
	
	public static void handle(InputMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			
			ServerPlayerEntity player = context.getSender();
			World world = player.getCommandSenderWorld();
			
			player.attack(world.getEntity(message.entNum));
			
		});
		context.setPacketHandled(true);
	}
}
