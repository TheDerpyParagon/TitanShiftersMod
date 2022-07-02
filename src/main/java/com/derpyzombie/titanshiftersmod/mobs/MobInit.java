package com.derpyzombie.titanshiftersmod.mobs;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.mobs.entity.ErenEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MobInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			"titanshiftersmod");

	public static final RegistryObject<EntityType<ErenEntity>> EREN = ENTITIES.register("eren",
			() -> EntityType.Builder.of(ErenEntity::new, EntityClassification.CREATURE)
					.build(new ResourceLocation(TitanShiftersMod.MOD_ID, "eren_entity").toString()));
}
