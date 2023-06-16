package ht.firefig.cardinal.projectile;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import ht.firefig.cardinal.game.CardinalGame;
import ht.firefig.cardinal.game.CardinalTicked;

public abstract class Fired extends CardinalTicked implements IFired
{
	private Location position;
	private Vector direction;
	private double damage;

	public Fired(CardinalGame game, double rate, Location position, Vector direction)
	{
		super(game, rate);
		this.position = position;
		this.direction = direction;
		this.damage = 6;
	}
	
	@Override
	public Location getPosition()
	{
		return position;
	}

	@Override
	public void setPosition(Location newPosition)
	{
		this.position = newPosition;
	}

	@Override
	public Vector getDirection()
	{
		return direction;
	}

	@Override
	public void setDirection(Vector newDirection)
	{
		this.direction = newDirection;
	}

	@Override
	public double getDamage()
	{
		return damage;
	}

	@Override
	public void setDamage(double damage)
	{
		this.damage = damage;
	}

	@Override
	public abstract void dispose();

	@Override
	public abstract void onTick();
}
