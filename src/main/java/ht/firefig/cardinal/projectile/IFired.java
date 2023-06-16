package ht.firefig.cardinal.projectile;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface IFired
{
	public Location getPosition();

	public void setPosition(Location newPosition);

	public Vector getDirection();

	public void setDirection(Vector newDirection);

	public double getDamage();

	public void setDamage(double damage);
}
