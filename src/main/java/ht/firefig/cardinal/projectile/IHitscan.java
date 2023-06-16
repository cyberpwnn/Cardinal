package ht.firefig.cardinal.projectile;

public interface IHitscan extends IFired
{
	public double getRange();
	
	public void setRange(double range);

	public double getDamageFalloffStart();
	
	public void setDamageFalloffStart(double dfs);
	
	public double getPower();
	
	public void setPower(double power);
}
