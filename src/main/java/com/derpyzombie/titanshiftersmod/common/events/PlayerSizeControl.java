package com.derpyzombie.titanshiftersmod.common.events;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.capabilities.ITitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShifters;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersProvider;
import com.derpyzombie.titanshiftersmod.capabilities.TitanShiftersStats;

import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Bus.FORGE)
public class PlayerSizeControl {

	public static float sizemultiplier = 1f;
	public static float presizemultiplier = 1f;
	private static float stepUp = 0.5f;
	
	public static float pureTitanReach = sizemultiplier * 3;

	public static void changeSize(float size) {
		presizemultiplier = size;
		stepUp = presizemultiplier / 3;
	}
	
	@SubscribeEvent
	public static void updateSizeMultiplier(TickEvent.PlayerTickEvent event) {
		LazyOptional<ITitanShifters> titan = event.player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		
		if(titanShifter.getCounter() >= 79) {
			sizemultiplier = presizemultiplier;
			if(sizemultiplier == 30f) {
				event.player.animationSpeed = 0.1f;
			}
		}
	}

	//Sets player size capability to sizemultiplier
	public static void setTitanSize(PlayerEntity player) {
		TitanShiftersStats.setTitanSize(presizemultiplier, player);
	}
	
	//Rendering
	@SubscribeEvent
	public void playerRenderPre(RenderPlayerEvent.Pre event) {
		
			event.getMatrixStack().pushPose();
			event.getMatrixStack().scale(sizemultiplier, sizemultiplier, sizemultiplier);
	}
	
	//Rendering
	@SubscribeEvent
	public void playerRenderPost(RenderPlayerEvent.Post event) {

		event.getMatrixStack().popPose();
	}

	@SubscribeEvent
	public static void changeHeight(EntityEvent.Size event) {
			if (event.getEntity() instanceof PlayerEntity) {
				if(sizemultiplier != 1f) {
					event.setNewEyeHeight(sizemultiplier * 1.6f);
					event.setNewSize(event.getNewSize().scale(sizemultiplier));
				} else if(sizemultiplier == 1f) {
					if(event.getEntity().getPose() == Pose.STANDING) {
						event.setNewEyeHeight(sizemultiplier * 1.6f);
						event.setNewSize(event.getNewSize().scale(sizemultiplier));
					}
			}
			
			if(presizemultiplier == 1f) {
				stepUp = 0.5f;
				event.getEntity().maxUpStep = stepUp;
			} else {
				event.getEntity().maxUpStep = stepUp;
			}
		}
	}

	@SubscribeEvent
	public static void Crouching(TickEvent.PlayerTickEvent event) {
		PlayerEntity player = event.player;

		LazyOptional<ITitanShifters> titan = player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction);
		ITitanShifters titanShifter = titan.orElse(new TitanShifters());
		
		if (titanShifter.getPureTitan() == true) {
			event.player.setPose(Pose.CROUCHING);
			event.player.setPose(Pose.STANDING);
		}
		
		System.out.println(titanShifter.getPureTitan());
		
	}
	
	@SubscribeEvent
	public static void playerSizePose(EntityEvent.Size event) {
		
		if(event.getEntity() instanceof PlayerEntity) {
			if(sizemultiplier == 1f) {
				if(event.getEntity().getPose() == Pose.CROUCHING) {
					event.setNewSize(event.getNewSize().scale(sizemultiplier * 0.95f));
					event.setNewEyeHeight(sizemultiplier * 1.3f);
				}
				if(event.getEntity().getPose() == Pose.SWIMMING) {
					event.setNewSize(event.getNewSize().scale(sizemultiplier * 1f));
					event.setNewEyeHeight(sizemultiplier * 0.6f);
				}
				if(event.getEntity().getPose() == Pose.SLEEPING) {
					event.setNewSize(event.getNewSize().scale(sizemultiplier * 1f));
					event.setNewEyeHeight(sizemultiplier * 0.6f);
				}
				if(event.getEntity().getPose() == Pose.FALL_FLYING) {
					event.setNewSize(event.getNewSize().scale(sizemultiplier * 1f));
					event.setNewEyeHeight(sizemultiplier * 0.6f);
				}
			}
		}
	}
}
