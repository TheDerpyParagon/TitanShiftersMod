package com.derpyzombie.titanshiftersmod.common.items;

import com.derpyzombie.titanshiftersmod.common.events.TitanTransformation;
import com.derpyzombie.titanshiftersmod.common.events.titanAbilities.TitanTransformationControl;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TitanSpinalFluid extends Item {

	public TitanSpinalFluid(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

		//Needed for extended range to work | Work in progress
		playerIn.getFeetBlockState().getBlock().playerDestroy(worldIn, playerIn, playerIn.blockPosition().below(),
				playerIn.getFeetBlockState(), playerIn.getFeetBlockState().createTileEntity(worldIn), getDefaultInstance());

		TitanTransformation.worldIn = worldIn;

		playerIn.setItemInHand(handIn, ItemStack.EMPTY);
		
		playerIn.heal(40);

		TitanTransformationControl.pureShift(playerIn);

		return ActionResult.success(playerIn.getItemInHand(handIn));
	}
}
