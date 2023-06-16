package ht.firefig.cardinal.projectile;

public interface IProjectile extends IFired
{	
	public double getGravitationalForce();
	
	public void setGravitationalForce(double g);
}
