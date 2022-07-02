package com.derpyzombie.titanshiftersmod.capabilities;

public interface ITitanShifters {

	Boolean getPureTitan();
	Boolean getTitanShifter();
	Boolean getFoundingTitan();
	Boolean getAttackTitan();
	Boolean getColossalTitan();
	Boolean getJawTitan();
	Boolean getWarHammerTitan();
	Boolean getArmoredTitan();
	Boolean getBeastTitan();
	Boolean getFemaleTitan();
	Boolean getCartTitan();
	Float getTitanSize();
	Double getCounter();
	
	void setPureTitan(boolean PureTitan);
	void setTitanShifter(boolean TitanShifter);
	void setFoundingTitan(boolean FoundingTitan);
	void setAttackTitan(boolean AttackTitan);
	void setColossalTitan(boolean ColossalTitan);
	void setJawTitan(boolean JawTitan);
	void setWarHammerTitan(boolean WarHammerTitan);
	void setArmoredTitan(boolean ArmoredTitan);
	void setBeastTitan(boolean BeastTitan);
	void setFemaleTitan(boolean FemaleTitan);
	void setCartTitan(boolean CartTitan);
	void setTitanSize(float size);
	void setCounter(double counter);
}
