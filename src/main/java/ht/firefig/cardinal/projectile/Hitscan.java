package ht.firefig.cardinal.projectile;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import ht.firefig.cardinal.game.CardinalGame;

public class Hitscan extends Fired implements IHitscan
{
	private double range;
	private double damageFalloffStart;
	private double power;
	
	public Hitscan(CardinalGame game, double rate, Location position, Vector direction)
	{
		super(game, rate, position, direction);
	}

	@Override
	public double getRange()
	{
		return range;
	}

	@Override
	public void setRange(double range)
	{
		this.range = range;
	}

	@Override
	public double getDamageFalloffStart()
	{
		return damageFalloffStart;
	}

	@Override
	public void setDamageFalloffStart(double dfs)
	{
		this.damageFalloffStart = dfs;
	}

	@Override
	public double getPower()
	{
		return power;
	}

	@Override
	public void setPower(double power)
	{
		this.power = power;
	}

	@Override
	public void dispose()
	{
		
	}

	@Override
	public void onTick()
	{
		
	}
}
