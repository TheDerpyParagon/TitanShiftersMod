package com.derpyzombie.titanshiftersmod.common.events.titanAbilities;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Explosion.Mode;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class TitanAblilities {

	static ModifiableAttributeInstance speedAttribute;
	static ModifiableAttributeInstance strengthAttribute;
	static ModifiableAttributeInstance healthAttribute;
	
	static AttributeModifier speedAttributeModifier;
	static AttributeModifier strengthAttributeModifier;
	static AttributeModifier healthAttributeModifier;

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void ReachExplosion(LeftClickBlock event) {

		LazyOptional<ITitanShifters> titan = event.getPlayer()
				.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		if (titanShifter.getPureTitan() == true) {
			if(!event.getWorld().isClientSide) {
				event.getWorld().explode(event.getPlayer(), event.getPos().getX(), event.getPos().getY(),
						event.getPos().getZ(), 1, Mode.BREAK);
			}
		}
	}

	public static void setSpeed(PlayerEntity player, float speed) {

		speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
		speedAttributeModifier = new AttributeModifier("speed", speed, Operation.ADDITION);

		speedAttribute.addPermanentModifier(speedAttributeModifier);
	}
	
	public static void removeSpeed(PlayerEntity player) {
		speedAttribute.removeModifiers();
	}

	public static void setStrength(PlayerEntity player, float strength) {

		strengthAttribute = player.getAttribute(Attributes.ATTACK_DAMAGE);
		strengthAttributeModifier = new AttributeModifier("strength", strength, Operation.ADDITION);

		strengthAttribute.addPermanentModifier(strengthAttributeModifier);
	}
	
	public static void removeStrength(PlayerEntity player) {
		strengthAttribute.removeModifiers();
	}

	public static void setHealth(PlayerEntity player, float health) {

		healthAttribute = player.getAttribute(Attributes.MAX_HEALTH);
		healthAttributeModifier = new AttributeModifier("strength", health, Operation.ADDITION);

		healthAttribute.addPermanentModifier(healthAttributeModifier);
	}
	
	public static void removeHealth(PlayerEntity player) {
		healthAttribute.removeModifiers();
	}


	@SubscribeEvent
	public static void dropItems(TickEvent.PlayerTickEvent event) {
		LazyOptional<ITitanShifters> titan = event.player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY,
				TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		if (event.player.inventory.isEmpty() == false && titanShifter.getPureTitan() == true) {
			event.player.inventory.clearContent();
		}
	}

}
