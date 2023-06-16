package ht.firefig.cardinal.game;

public enum TickProiority
{
	HIGH(0.6),
	NORMAL(0.3),
	LOW(0.1);
	
	private double allocation;
	
	private TickProiority(double allocation)
	{
		this.allocation = allocation;
	}
	
	public double getAllocation()
	{
		return allocation;
	}
}
