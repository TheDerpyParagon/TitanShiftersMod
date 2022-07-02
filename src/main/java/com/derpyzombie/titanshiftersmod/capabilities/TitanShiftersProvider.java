package com.derpyzombie.titanshiftersmod.capabilities;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TitanShiftersProvider implements ICapabilitySerializable<INBT> {

	@CapabilityInject(ITitanShifters.class)
	public static final Capability<ITitanShifters> TITAN_SHIFTERS_CAPABILITY = null;

	private LazyOptional<ITitanShifters> instance = LazyOptional.of(TITAN_SHIFTERS_CAPABILITY::getDefaultInstance);
	
	public Boolean hasCapability(Capability<?> capability, Direction side) {
		return capability == TITAN_SHIFTERS_CAPABILITY && side == TitanShiftersMod.direction;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == TITAN_SHIFTERS_CAPABILITY && side == TitanShiftersMod.direction ? instance.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		//@formatter:off
	
		return TITAN_SHIFTERS_CAPABILITY.getStorage().writeNBT(TITAN_SHIFTERS_CAPABILITY,
				this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), TitanShiftersMod.direction);
		//@formatter:on
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		//@formatter:off
		TITAN_SHIFTERS_CAPABILITY.getStorage().readNBT(TITAN_SHIFTERS_CAPABILITY,
				this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), TitanShiftersMod.direction, nbt);
		//@formatter:on
	}

}
