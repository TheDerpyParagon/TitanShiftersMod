package com.derpyzombie.titanshiftersmod.capabilities;

import com.derpyzombie.titanshiftersmod.TitanShiftersMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.NonNullConsumer;

public class TitanShiftersStats {

	public static void setPureTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setPureTitan(bool);
			}
		});
	}
	
	public static void setTitanShifter(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setTitanShifter(bool);
			}
		});
	}
	
public static void setFoundingTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setFoundingTitan(bool);
			}
		});
	}
	
	public static void setAttackTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setAttackTitan(bool);
			}
		});
	}
	
public static void setColossalTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setColossalTitan(bool);
			}
		});
	}
	
	public static void setJawTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setJawTitan(bool);
			}
		});
	}
	
public static void setWarHammerTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setWarHammerTitan(bool);
			}
		});
	}
	
	public static void setArmoredTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setArmoredTitan(bool);
			}
		});
	}
	
public static void setBeastTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setBeastTitan(bool);
			}
		});
	}
	
	public static void setFemaleTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setFemaleTitan(bool);
			}
		});
	}
	
public static void setCartTitan(boolean bool, PlayerEntity player) {
		
		player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

			@Override
			public void accept(ITitanShifters t) {
				t.setCartTitan(bool);
			}
		});
	}

public static void setTitanSize(float size, PlayerEntity player) {
	
	player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

		@Override
		public void accept(ITitanShifters t) {
			t.setTitanSize(size);
		}
	});
}

public static void setCounter(double counter, PlayerEntity player) {
	
	player.getCapability(TitanShiftersProvider.TITAN_SHIFTERS_CAPABILITY, TitanShiftersMod.direction).ifPresent(new NonNullConsumer<ITitanShifters>() {

		@Override
		public void accept(ITitanShifters t) {
			t.setCounter(counter);
		}
	});
}
}
