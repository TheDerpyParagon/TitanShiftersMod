package com.derpyzombie.titanshiftersmod.common.reach;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameType;
import net.minecraftforge.common.util.LazyOptional;

public class BlockReachExtender extends PlayerController {
	
	private static Minecraft minecraft = Minecraft.getInstance();
	private static GameType localPlayerMode = GameType.SURVIVAL;
	
	public BlockReachExtender(Minecraft mc, ClientPlayNetHandler clientHandler) {
		super(mc, clientHandler);
	}
	
	public static float getBlockRange() {
		float attrib = (float)minecraft.player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
		return localPlayerMode.isCreative() ? attrib : attrib - 0.5F;
	}
	
	public static void setBlockRange(PlayerEntity player, float range) {
		LazyOptional<ITitanShifters> titan = player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		ModifiableAttributeInstance reach = minecraft.player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get());
		final AttributeModifier attributeModifier = new AttributeModifier("reach", range, Operation.ADDITION);
		
		if(titanShifter.getPureTitan() == true) {
			reach.addPermanentModifier(attributeModifier);
		} else {
			reach.removeModifier(attributeModifier);
		}
	}
}
