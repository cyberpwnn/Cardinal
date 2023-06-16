package ht.firefig.cardinal.projectile;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import ht.firefig.cardinal.game.CardinalGame;

public class Projectile extends Fired implements IProjectile
{
	private double gravitationalForce;
	
	public Projectile(CardinalGame game, double rate, Location position, Vector direction)
	{
		super(game, rate, position, direction);
		this.gravitationalForce = 1;
	}

	@Override
	public double getGravitationalForce()
	{
		return gravitationalForce;
	}

	@Override
	public void setGravitationalForce(double g)
	{
		this.gravitationalForce = g;
	}

	@Override
	public void dispose()
	{
		
	}

	@Override
	public void onTick()
	{
		updatePosition();
		getPosition().getBlock().setType(Material.GLOWSTONE);
		
		if(timeAlive > 25000 || getPosition().getY() > 255 || getPosition().getY() < 0)
		{
			game.unregisterTicked(this);
		}
	}

	private void updatePosition()
	{
		getDirection().add(game.getGravity());
		getPosition().add(getDirection());
	}
}
