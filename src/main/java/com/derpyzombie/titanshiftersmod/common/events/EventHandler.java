package com.derpyzombie.titanshiftersmod.common.events;

import java.util.Random;
import java.util.UUID;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersStats;
import com.derpyzombie.titanshiftersmod.common.items.ErenSpawnEgg;
import com.derpyzombie.titanshiftersmod.core.network.TitanShiftersNetwork;
import com.derpyzombie.titanshiftersmod.core.network.message.ClientMessage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class EventHandler {

	static Random rand = new Random();
	
    @SubscribeEvent
    public void playerLogsIn(PlayerLoggedInEvent event) {
    	PlayerEntity player = event.getPlayer();
		player.sendMessage(new StringTextComponent("Hello from DerpyZombie!"), new UUID(0, 0));
    }
    
    @SubscribeEvent
    public void loginCheck(PlayerLoggedInEvent event) {
    	PlayerEntity player = event.getPlayer();
    	LazyOptional<ITitanShifters> titan = player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		if(titanShifter.getPureTitan() == true) {
			PlayerSizeControl.changeSize(titanShifter.getTitanSize());
			player.getEntity().setPose(Pose.CROUCHING);
			player.getEntity().setPose(Pose.STANDING);
		} else if(titanShifter.getPureTitan() == false) {
			PlayerSizeControl.sizemultiplier = 1f;
			PlayerSizeControl.changeSize(1f);
			player.getEntity().setPose(Pose.CROUCHING);
			player.getEntity().setPose(Pose.STANDING);
		}
    }
    
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
    	PlayerEntity player = event.getPlayer();
    	
    	LazyOptional<ITitanShifters> titan = player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		LazyOptional<ITitanShifters> oldTitan = event.getOriginal().getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters oldTitanShifter = oldTitan.orElse(titanShifter);
		
		TitanShiftersStats.setPureTitan(oldTitanShifter.getPureTitan(), player);
		TitanShiftersStats.setTitanShifter(oldTitanShifter.getTitanShifter(), player);
		TitanShiftersStats.setAttackTitan(oldTitanShifter.getAttackTitan(), player);
    }
    
    
    
    //Start of sending capabilities to client
    @SubscribeEvent
    public void onPlayerTracking(PlayerEvent.StartTracking event) {
    	
    	if(event.getTarget() instanceof PlayerEntity) {
    		PlayerEntity player = (PlayerEntity)event.getTarget();
    		ServerPlayerEntity target = (ServerPlayerEntity)event.getPlayer();
    		if(!player.getCommandSenderWorld().isClientSide()) {
    			player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY).ifPresent(capability -> {
    				CompoundNBT nbt = new CompoundNBT();
    				Capability<ITitanShifters> cap = TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY;
    				Capability.IStorage<ITitanShifters> storage = cap.getStorage();
    				nbt.put(cap.getName(), storage.writeNBT(cap, capability, TitanShiftersMod.direction));
    				ClientMessage message = new ClientMessage(nbt);
    				TitanShiftersNetwork.CLIENTCHANNEL.send(PacketDistributor.PLAYER.with(() -> target), message);
    			});
    		}
    	}
    }
    
    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
    	ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
    	
    	if(!player.getCommandSenderWorld().isClientSide()) {
    		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY).ifPresent(capability -> {
    			CompoundNBT nbt = new CompoundNBT();
		    	Capability<ITitanShifters> cap = TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY;
				Capability.IStorage<ITitanShifters> storage = cap.getStorage();
				nbt.put(cap.getName(), storage.writeNBT(cap, capability, TitanShiftersMod.direction));
		    	ClientMessage message = new ClientMessage(nbt);
		    	TitanShiftersNetwork.CLIENTCHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    		});
    	}
    }
    
    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event) {
    	ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
    	
    	if(!player.getCommandSenderWorld().isClientSide()) {
    		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY).ifPresent(capability -> {
    			CompoundNBT nbt = new CompoundNBT();
		    	Capability<ITitanShifters> cap = TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY;
				Capability.IStorage<ITitanShifters> storage = cap.getStorage();
				nbt.put(cap.getName(), storage.writeNBT(cap, capability, TitanShiftersMod.direction));
		    	ClientMessage message = new ClientMessage(nbt);
		    	TitanShiftersNetwork.CLIENTCHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    		});
    	}
    }
    
    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerChangedDimensionEvent event) {
    	ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
    	
    	if(!player.getCommandSenderWorld().isClientSide()) {
    		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY).ifPresent(capability -> {
    			CompoundNBT nbt = new CompoundNBT();
		    	Capability<ITitanShifters> cap = TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY;
				Capability.IStorage<ITitanShifters> storage = cap.getStorage();
				nbt.put(cap.getName(), storage.writeNBT(cap, capability, TitanShiftersMod.direction));
		    	ClientMessage message = new ClientMessage(nbt);
		    	TitanShiftersNetwork.CLIENTCHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    		});
    	}
    }
    //End of sending capabilities to client
    

    
    @SubscribeEvent
    public static void RegisterMobEggs(RegistryEvent.Register<EntityType<?>> event) {
    	ErenSpawnEgg.initSpawnEggs();
    }

}
