package com.derpyzombie.titanshiftersmod.common.events;

import java.util.Random;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class RegenerationEvent {
	
	static Random rand = new Random();

	@SubscribeEvent
	public static void regeneration(TickEvent.PlayerTickEvent event) {
		LazyOptional<ITitanShifters> titan = event.player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		float heal;
		if(titanShifter.getFoundingTitan() == true) {
			heal = 0.02f;
		} else {
			heal = 0.01f;
		}
		
		if (titanShifter.getTitanShifter() == true && titanShifter.getPureTitan() == false) {
			if (event.player.getHealth() <= 19) {
				event.player.heal(heal);
			}
		}
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void regenerationParticle(TickEvent.PlayerTickEvent event)
			throws InstantiationException, IllegalAccessException {
		LazyOptional<ITitanShifters> titan = event.player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());

		PlayerEntity playerIn = event.player;
		World worldIn = Minecraft.getInstance().level;

		float chance = 0.50f;

		if (titanShifter.getTitanShifter() == true && playerIn.getHealth() <= 19 && titanShifter.getPureTitan() == false) {

			if (chance < rand.nextFloat()) {
				worldIn.addParticle(ParticleTypes.POOF, playerIn.getEntity().getX(), playerIn.getEntity().getY() + 1,
						playerIn.getEntity().getZ(), 0.02, 0.02, 0.02);
			}
		}
	}
}
