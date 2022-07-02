package com.derpyzombie.titanshiftersmod.capabilities;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class TitanShiftersStorage implements IStorage<ITitanShifters> {

	@Override
	public INBT writeNBT(Capability<ITitanShifters> capability, ITitanShifters instance, Direction side) {
		if(side == TitanShiftersMod.direction) {
			CompoundNBT tag = new CompoundNBT();
			tag.putBoolean("TitanShifter", instance.getTitanShifter());
			tag.putBoolean("FoundingTitan", instance.getFoundingTitan());
			tag.putBoolean("AttackTitan", instance.getAttackTitan());
			tag.putBoolean("ColossalTitan", instance.getColossalTitan());
			tag.putBoolean("JawTitan", instance.getJawTitan());
			tag.putBoolean("WarHammerTitan", instance.getWarHammerTitan());
			tag.putBoolean("ArmoredTitan", instance.getArmoredTitan());
			tag.putBoolean("BeastTitan", instance.getBeastTitan());
			tag.putBoolean("FemaleTitan", instance.getFemaleTitan());
			tag.putBoolean("CartTitan", instance.getCartTitan());
			tag.putBoolean("PureTitan", instance.getPureTitan());
			tag.putFloat("TitanSize", instance.getTitanSize());
			tag.putDouble("Counter", instance.getCounter());
			return tag;
		} else {
			return new CompoundNBT();
		}
	}

	@Override
	public void readNBT(Capability<ITitanShifters> capability, ITitanShifters instance, Direction side, INBT nbt) {
		if(side == TitanShiftersMod.direction) {
			CompoundNBT tag = (CompoundNBT) nbt;
			instance.setTitanShifter(tag.getBoolean("TitanShifter"));
			instance.setFoundingTitan(tag.getBoolean("FoundingTitan"));
			instance.setAttackTitan(tag.getBoolean("AttackTitan"));
			instance.setColossalTitan(tag.getBoolean("ColossalTitan"));
			instance.setJawTitan(tag.getBoolean("JawTitan"));
			instance.setWarHammerTitan(tag.getBoolean("WarHammerTitan"));
			instance.setArmoredTitan(tag.getBoolean("ArmoredTitan"));
			instance.setBeastTitan(tag.getBoolean("BeastTitan"));
			instance.setFemaleTitan(tag.getBoolean("FemaleTitan"));
			instance.setCartTitan(tag.getBoolean("CartTitan"));
			instance.setPureTitan(tag.getBoolean("PureTitan"));
			instance.setTitanSize(tag.getFloat("TitanSize"));
			instance.setCounter(tag.getDouble("Counter"));
		} else {
			return;
		}
	}

}
