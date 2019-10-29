package ht.firefig.cardinal.projectile;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface IProjectile
{
	public Vector getPosition();

	public Vector getDirection();

	public float getMass();

	public float getVelocity();

	public float getVolume();

	public float getDensity();

	public float getDrag();

	public void tick();

	public void impulse(Vector v);
	
	public boolean isDead();
}
