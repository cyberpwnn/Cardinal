package ht.firefig.cardinal.sim;

import java.util.concurrent.locks.ReentrantLock;

import javax.print.attribute.standard.Chromaticity;

import ht.firefig.cardinal.projectile.IProjectile;
import ninja.bytecode.shuriken.collections.GList;
import ninja.bytecode.shuriken.execution.ChronoLatch;
import ninja.bytecode.shuriken.execution.J;
import ninja.bytecode.shuriken.execution.TaskExecutor;
import ninja.bytecode.shuriken.execution.TaskExecutor.TaskGroup;
import ninja.bytecode.shuriken.execution.TaskExecutor.TaskResult;
import ninja.bytecode.shuriken.format.F;

public class ProjectileSimulation extends Thread
{
	private TaskExecutor executor;
	private GList<IProjectile> projectiles;
	private ReentrantLock projectileLock;
	private boolean dead;
	private int rate = 120;
	private ChronoLatch cl;
	
	public ProjectileSimulation()
	{
		executor = new TaskExecutor(-1, Thread.MIN_PRIORITY, "Cardinal Projectile Simulator");
		projectiles = new GList<IProjectile>();
		projectileLock = new ReentrantLock();
		dead = false;
		cl = new ChronoLatch(1000);
		start();
	}
	
	public void run()
	{
		while(!dead)
		{
			J.sleep(1000 / rate);
			tick();
		}
	}
	
	public void shutdown()
	{
		executor.close();
		dead = true;
	}
	
	public void fire(IProjectile projectile)
	{
		projectileLock.lock();
		projectiles.add(projectile);
		projectileLock.unlock();
	}
	
	public void tick()
	{
		TaskGroup work = executor.startWork();
		
		projectiles.forEach((p) -> {
			work.queue(() -> {
				projectileLock.lock();
				tickProjectile(p);
				
				if(p.isDead())
				{
					J.a(() -> projectiles.remove(p));
				}
				
				projectileLock.unlock();
			});
		});
		
		work.execute();
	}

	private void tickProjectile(IProjectile p)
	{
		p.tick();
	}
}
