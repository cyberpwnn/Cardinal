package ht.firefig.cardinal.projectile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import ht.firefig.cardinal.Cardinal;
import ht.firefig.cardinal.sim.Area;
import ht.firefig.cardinal.sim.BlastResistance;
import ht.firefig.cardinal.sim.LineParticleManipulator;
import ninja.bytecode.shuriken.execution.J;

public class Projectile implements IProjectile
{
	private float velocity;
	private float g;
	private float mass;
	private float volume;
	private float drag;
	private Location location;
	private boolean dead;
	private double distance;
	private double minSize;
	
	public Projectile(Location location)
	{
		this(1, 1, 1, 1, location);
	}

	public Projectile(float velocity, float mass, float volume, float drag, Location location)
	{
		this.velocity = velocity;
		this.mass = mass;
		this.volume = volume;
		this.drag = drag;
		this.location = location;
		dead = false;
		distance = 0;
		g = 0;
		minSize = 0;
	}

	public float getMass()
	{
		return mass;
	}

	public float getVolume()
	{
		return volume;
	}

	public float getDensity()
	{
		return mass / volume;
	}

	public float getDrag()
	{
		return drag;
	}

	public float getVelocity()
	{
		return velocity;
	}

	public Vector getPosition()
	{
		return location.toVector();
	}

	public Vector getDirection()
	{
		return location.getDirection();
	}

	@Override
	public void tick()
	{
		if(isDead())
		{
			return;
		}
		
		if(location.getY() < 0)
		{
			dead = true; 
			return;
		}
		
		if(!location.getWorld().isChunkLoaded(location.getBlockX() >> 4, location.getBlockZ() >> 4))
		{
			dead = true;
			return;
		}
		
		Location from = location.clone();
		location.add(getDirection().multiply(velocity));
		location.add(0, g -= ((1D / 120D)), 0);
		velocity = drag > 0 ? velocity - ((velocity * drag) * (1f / 120f)) : velocity;
		distance += velocity;
		minSize = Math.max(distance / 6D, 0.33);
		LineParticleManipulator lp = new LineParticleManipulator()
		{
			@Override
			public void play(Location lxx)
			{
				location.getWorld().spawnParticle(Particle.TOWN_AURA, lxx, 0);
				
				if(check(location))
				{
					dead = true;
				}
			}
		};
		
		lp.play(location, from, 1D / minSize);

	}

	private boolean check(Location l)
	{
		boolean checked = false;
		boolean hit = true;
		
		while(!checked)
		{
			try
			{
				Block b = l.getBlock();
				checked = true;
				if(b.isEmpty() || BlastResistance.get(b.getType()) <= 1 || b.getType().equals(Material.LONG_GRASS))
				{
					for(Entity i : l.getWorld().getNearbyEntities(l, Math.max(minSize * 3, volume), Math.max(minSize * 3, volume), Math.max(minSize * 3, volume)))
					{
						if(i instanceof LivingEntity)
						{
							double dmg = (Math.min(velocity, 5) * Math.min(5, mass)) * 2.8;
							System.out.print(dmg + " Damage");
							Cardinal.s(() -> ((LivingEntity) i).damage(dmg));
							break;
						}
					}
					
					return false;
				}
				
				else
				{
					return true;
				}
			}
			
			catch(Throwable e)
			{
				
			}
			
			J.sleep(1);
		}
		
		return hit;
	}

	@Override
	public void impulse(Vector v)
	{
		location.setDirection(location.getDirection().add(v));
	}

	@Override
	public boolean isDead()
	{
		return dead;
	}
}
