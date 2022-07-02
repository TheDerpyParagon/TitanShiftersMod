package com.derpyzombie.titanshiftersmod.core.network.message;

import java.util.function.Supplier;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClientMessage {

	private CompoundNBT data;
	
	public ClientMessage(CompoundNBT d) {
		data = d;
	}
	
	public static void encode(ClientMessage message, PacketBuffer buffer) {
		buffer.writeNbt(message.data);
	}
	
	public static ClientMessage decode(PacketBuffer buffer) {
		return new ClientMessage(buffer.readNbt());
	}
	
	public static void handle(ClientMessage message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
        	
        	if(context.getDirection().getReceptionSide().isClient() && context.getDirection().getOriginationSide().isServer()) {
        		@SuppressWarnings("resource")
				ClientPlayerEntity p = Minecraft.getInstance().player;
        		
        		p.getEntity().getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY).ifPresent(capability -> {
        			Capability.IStorage<ITitanShifters> storage = TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY.getStorage();
        			storage.readNBT(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, capability, TitanShiftersMod.direction, message.data);
        		});
        	}
        	
        });
        context.setPacketHandled(true);
	}

}
