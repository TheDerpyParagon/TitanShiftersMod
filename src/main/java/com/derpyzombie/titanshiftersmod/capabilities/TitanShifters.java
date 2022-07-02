package com.derpyzombie.titanshiftersmod.capabilities;

public class TitanShifters implements ITitanShifters {

	private boolean pureTitan;
	private boolean TitanShifter;
	private boolean FoundingTitan;
	private boolean AttackTitan;
	private boolean ColossalTitan;
	private boolean JawTitan;
	private boolean WarHammerTitan;
	private boolean ArmoredTitan;
	private boolean BeastTitan;
	private boolean FemaleTitan;
	private boolean CartTitan;
	private float TitanSize;
	private double counter;
	
	public TitanShifters() {
		this.TitanShifter = true;
		this.pureTitan = false;
		this.FoundingTitan = false;
		this.AttackTitan = false;
		this.ColossalTitan = false;
		this.JawTitan = false;
		this.WarHammerTitan = false;
		this.ArmoredTitan = false;
		this.BeastTitan = false;
		this.FemaleTitan = false;
		this.CartTitan = false;
		this.TitanSize = 1;
		this.counter = 0;
	}

	@Override
	public Boolean getPureTitan() {
		return this.pureTitan;
	}
	
	@Override
	public Boolean getTitanShifter() {
		return this.TitanShifter;
	}

	@Override
	public Boolean getFoundingTitan() {
		return this.FoundingTitan;
	}

	@Override
	public Boolean getAttackTitan() {
		return this.AttackTitan;
	}

	@Override
	public Boolean getColossalTitan() {
		return this.ColossalTitan;
	}

	@Override
	public Boolean getJawTitan() {
		return this.JawTitan;
	}

	@Override
	public Boolean getWarHammerTitan() {
		return this.WarHammerTitan;
	}

	@Override
	public Boolean getArmoredTitan() {
		return this.ArmoredTitan;
	}

	@Override
	public Boolean getBeastTitan() {
		return this.BeastTitan;
	}

	@Override
	public Boolean getFemaleTitan() {
		return this.FemaleTitan;
	}

	@Override
	public Boolean getCartTitan() {
		return this.CartTitan;
	}

	@Override
	public void setPureTitan(boolean PureTitan) {
		this.pureTitan = PureTitan;
	}
	
	@Override
	public void setTitanShifter(boolean TitanShifter) {
		this.TitanShifter = TitanShifter;
	}

	@Override
	public void setFoundingTitan(boolean FoundingTitan) {
		this.FoundingTitan = FoundingTitan;
	}

	@Override
	public void setAttackTitan(boolean AttackTitan) {
		this.AttackTitan = AttackTitan;
	}

	@Override
	public void setColossalTitan(boolean ColossalTitan) {
		this.ColossalTitan = ColossalTitan;
	}

	@Override
	public void setJawTitan(boolean JawTitan) {
		this.JawTitan = JawTitan;
	}

	@Override
	public void setWarHammerTitan(boolean WarHammerTitan) {
		this.WarHammerTitan = WarHammerTitan;
	}

	@Override
	public void setArmoredTitan(boolean ArmoredTitan) {
		this.ArmoredTitan = ArmoredTitan;
	}

	@Override
	public void setBeastTitan(boolean BeastTitan) {
		this.BeastTitan = BeastTitan;
	}

	@Override
	public void setFemaleTitan(boolean FemaleTitan) {
		this.FemaleTitan = FemaleTitan;
	}

	@Override
	public void setCartTitan(boolean CartTitan) {
		this.CartTitan = CartTitan;
	}

	@Override
	public Float getTitanSize() {
		return this.TitanSize;
	}

	@Override
	public void setTitanSize(float size) {
		this.TitanSize = size;
		
	}

	@Override
	public Double getCounter() {
		return counter;
	}

	@Override
	public void setCounter(double counter) {
		this.counter = counter;
	}
}
