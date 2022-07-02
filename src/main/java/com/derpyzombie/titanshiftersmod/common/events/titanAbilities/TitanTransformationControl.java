package com.derpyzombie.titanshiftersmod.common.events.titanAbilities;

import java.util.Random;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersStats;
import com.derpyzombie.titanshiftersmod.common.events.PlayerSizeControl;
import com.derpyzombie.titanshiftersmod.common.reach.BlockReachExtender;
import com.derpyzombie.titanshiftersmod.common.reach.EntityReachExtender;
import com.derpyzombie.titanshiftersmod.core.network.TitanShiftersNetwork;
import com.derpyzombie.titanshiftersmod.core.network.message.ClientMessage;
import com.derpyzombie.titanshiftersmod.mobs.entity.ErenEntity;
import com.derpyzombie.titanshiftersmod.util.SoundEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class TitanTransformationControl {
	
	static Random rand = new Random();
	
	StringTextComponent text = new StringTextComponent("You have inherited the attack titan.");
	
	@SubscribeEvent
	public void death(LivingDeathEvent event) {
		
		PlayerEntity player = null;
		
		LazyOptional<ITitanShifters> titan;
		ITitanShifters titanShifter = null;
		
		if(event.getSource().getDirectEntity() instanceof PlayerEntity) {
			player = (PlayerEntity)event.getSource().getDirectEntity();
			titan = player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
			titanShifter = titan.orElse(new TitanShifters());
		}
		
		if(event.getSource().getDirectEntity() instanceof PlayerEntity && event.getEntity() instanceof ErenEntity && titanShifter.getPureTitan()) {
			System.out.println("Working!");
			InheritAttack(player);
			player.sendMessage(new StringTextComponent("You have inherited the attack titan.").withStyle(TextFormatting.GREEN), null);
		}
	}
	
	
	
	public static void InheritAttack(PlayerEntity player) {
		
		LazyOptional<ITitanShifters> titan = player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());

		TitanShiftersStats.setAttackTitan(true, player);
		
		pureUnshift(player);
		
		System.out.println(player.getScoreboardName() + " is pure titan: " + titanShifter.getPureTitan().toString());
		
		ServerPlayerEntity p = (ServerPlayerEntity)player;
    	
    	if(!p.getCommandSenderWorld().isClientSide()) {
    		p.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY).ifPresent(capability -> {
    			CompoundNBT nbt = new CompoundNBT();
		    	Capability<ITitanShifters> cap = TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY;
				Capability.IStorage<ITitanShifters> storage = cap.getStorage();
				nbt.put(cap.getName(), storage.writeNBT(cap, capability, TitanShiftersMod.direction));
		    	ClientMessage message = new ClientMessage(nbt);
		    	TitanShiftersNetwork.CLIENTCHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> p), message);
    		});
    	}
		
		System.out.println(player.getScoreboardName() + " has inherited the attack titan!");
	}
	
	public static void InheritArmored(PlayerEntity player) {
		
		TitanShiftersStats.setArmoredTitan(true, player);
		TitanShiftersStats.setPureTitan(false, player);
		
		System.out.println(player.getScoreboardName() + " has inherited the armored titan!");
	}
	
	public static void InheritColossal(PlayerEntity player) {
		
		TitanShiftersStats.setColossalTitan(true, player);
		TitanShiftersStats.setPureTitan(false, player);
		
		System.out.println(player.getScoreboardName() + " has inherited the colossal titan!");
	}
	
	
	
	public static void pureShift(PlayerEntity player) {
		float randTitanSizeFloat = 1.5f + rand.nextFloat() * (7.5f - 1.5f);
		
		PlayerSizeControl.changeSize(randTitanSizeFloat);
		TitanAblilities.setSpeed(player, 0.10f);
		TitanAblilities.setStrength(player, 100f);
		TitanAblilities.setHealth(player, 20);
		BlockReachExtender.setBlockRange(player, PlayerSizeControl.pureTitanReach);
		EntityReachExtender.setEntityRange(PlayerSizeControl.pureTitanReach);
		PlayerSizeControl.setTitanSize(player);
		player.playSound(SoundEvents.TITAN_TRANSFORMATION.get(), 1f, 1f);
		
		TitanShiftersStats.setPureTitan(true, player);
	}
	
	public static void pureUnshift(PlayerEntity player) {
		PlayerSizeControl.presizemultiplier = 1f;
		PlayerSizeControl.sizemultiplier = 1f;
		PlayerSizeControl.changeSize(1f);
		TitanAblilities.removeSpeed(player);
		TitanAblilities.removeStrength(player);
		TitanAblilities.removeHealth(player);
		BlockReachExtender.setBlockRange(player, 0);
		EntityReachExtender.setEntityRange(0);
		
		TitanShiftersStats.setPureTitan(false, player);
	}
}
