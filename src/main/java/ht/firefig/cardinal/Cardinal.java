package ht.firefig.cardinal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ht.firefig.cardinal.game.CardinalGame;
import ht.firefig.cardinal.game.TestGame;
import ninja.bytecode.shuriken.execution.J;
import ninja.bytecode.shuriken.execution.NastyRunnable;

public class Cardinal extends JavaPlugin implements Listener
{
	public static Cardinal instance;
	private CardinalGame game;
	
	public void onEnable()
	{
		instance = this;
		game = new TestGame();
		getServer().getPluginManager().registerEvents(this, this);
		
		for(Player i : Bukkit.getOnlinePlayers())
		{
			game.join(i);
		}
	}
	
	@EventHandler
	public void on(PlayerJoinEvent e)
	{
		game.join(e.getPlayer());
	}
	
	@EventHandler
	public void on(PlayerQuitEvent e)
	{
		game.quit(e.getPlayer());
	}
	
	public void onDisable()
	{
		game.dispose();
		getServer().getScheduler().cancelTasks(this);
		HandlerList.unregisterAll((Plugin)this);
	}

	public static void s(NastyRunnable object)
	{
		instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> J.attempt(object), 0);
	}
}
