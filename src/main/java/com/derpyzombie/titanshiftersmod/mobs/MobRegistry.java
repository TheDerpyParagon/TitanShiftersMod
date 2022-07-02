package com.derpyzombie.titanshiftersmod.mobs;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.mobs.entity.ErenEntity;
import com.derpyzombie.titanshiftersmod.mobs.render.ErenRender;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TitanShiftersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobRegistry {

	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(MobInit.EREN.get(), ErenEntity.setCustomAttributes().build());
	}
	
	public static void registerEntities() {
		RenderingRegistry.registerEntityRenderingHandler(MobInit.EREN.get(), new ErenRender.RenderFactory());
	}
}
