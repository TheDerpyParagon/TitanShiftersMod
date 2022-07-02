package com.derpyzombie.titanshiftersmod.common.items.init;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;
import com.derpyzombie.titanshiftersmod.common.items.ErenSpawnEgg;
import com.derpyzombie.titanshiftersmod.common.items.TitanSpinalFluid;
import com.derpyzombie.titanshiftersmod.core.itemgroup.TitanShiftersModItemGroup;
import com.derpyzombie.titanshiftersmod.mobs.MobInit;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			TitanShiftersMod.MOD_ID);

	public static final RegistryObject<TitanSpinalFluid> TITANSPINALFLUID = ITEMS.register("titan_spinal_fluid",
			() -> new TitanSpinalFluid(
					new Item.Properties().tab(TitanShiftersModItemGroup.TitanShiftersMod).stacksTo(1)));

	public static final RegistryObject<ErenSpawnEgg> ERENSPAWNEGG = ITEMS.register("eren_spawn_egg",
			() -> new ErenSpawnEgg(MobInit.EREN, 0x12228a, 0x4a382c,
					new Item.Properties().tab(TitanShiftersModItemGroup.TitanShiftersMod)));
}
