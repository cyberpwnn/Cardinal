package ht.firefig.cardinal;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import ht.firefig.cardinal.projectile.IProjectile;
import ht.firefig.cardinal.projectile.Projectile;
import ht.firefig.cardinal.sim.ProjectileSimulation;
import ninja.bytecode.shuriken.execution.J;
import ninja.bytecode.shuriken.execution.NastyRunnable;

public class Cardinal extends JavaPlugin implements Listener
{
	public static Cardinal instance;
	public static final Vector GRAVITY = new Vector(0, -1, 0).multiply(1D / 20D);
	private ProjectileSimulation sim;
	
	public void onEnable()
	{
		instance = this;
		sim = new ProjectileSimulation();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable()
	{
		sim.shutdown();
		getServer().getScheduler().cancelTasks(this);
		HandlerList.unregisterAll((Plugin)this);
	}

	public static void s(NastyRunnable object)
	{
		instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> J.attempt(object), 0);
	}
}
