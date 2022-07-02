package com.derpyzombie.titanshiftersmod.core.itemgroup;

import com.derpyzombie.titanshiftersmod.common.items.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TitanShiftersModItemGroup extends ItemGroup {

	public static final TitanShiftersModItemGroup TitanShiftersMod = new TitanShiftersModItemGroup(
			ItemGroup.TABS.length, "titan_shifters_mod");

	public TitanShiftersModItemGroup(int index, String label) {
		super(index, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.TITANSPINALFLUID.get());
	}
}