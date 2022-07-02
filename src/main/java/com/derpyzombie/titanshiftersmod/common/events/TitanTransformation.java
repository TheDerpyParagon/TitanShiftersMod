package com.derpyzombie.titanshiftersmod.common.events;

import java.util.Random;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersStats;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class TitanTransformation {
	
	static Random rand = new Random();
	
	public static World worldIn;
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void transformationParticle(TickEvent.PlayerTickEvent event)
			throws InstantiationException, IllegalAccessException {
		LazyOptional<ITitanShifters> titan = event.player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());

		PlayerEntity playerIn = event.player;
		World world = Minecraft.getInstance().level;

		float chance = 0.50f;

		if (titanShifter.getPureTitan() == true && titanShifter.getCounter() < 80) {
			TitanShiftersStats.setCounter(titanShifter.getCounter() + 1, playerIn);
			if (chance < rand.nextFloat()) {
				world.addParticle(ParticleTypes.POOF, playerIn.getEntity().getX(), playerIn.getEntity().getY() + 1,
						playerIn.getEntity().getZ(), 0.02, 0.02, 0.02);
			}
		}
	}

	@SubscribeEvent
	public static void Lightning(PlayerTickEvent event) {
		LazyOptional<ITitanShifters> titan = event.player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		
		World world = worldIn;
		PlayerEntity player = event.player;
		
		if(titanShifter.getCounter() == 79) {
			LightningBoltEntity lightningbolt = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, world);
			lightningbolt.setPos(player.getX(), player.getY(), player.getZ());
			world.addFreshEntity(lightningbolt);
		}
	}
	
	@SubscribeEvent
	public static void Explosion(PlayerTickEvent event) {
		LazyOptional<ITitanShifters> titan = event.player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		
		World world = worldIn;
		PlayerEntity player = event.player;
		
		if(titanShifter.getCounter() == 79) {
			if(!world.isClientSide) {
				world.explode(player, player.getX(), player.getY() + 5, player.getZ(), 5f, Mode.BREAK);
			}
		}
	}
}
