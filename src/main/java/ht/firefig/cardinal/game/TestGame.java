package ht.firefig.cardinal.game;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import ht.firefig.cardinal.Cardinal;

public class TestGame extends CardinalGame implements Listener
{
	public TestGame()
	{
		Bukkit.getPluginManager().registerEvents(this, Cardinal.instance);
	}

	@EventHandler
	public void on(PlayerInteractEvent e)
	{
		if(!isPlaying(e.getPlayer()))
		{
			return;
		}
	}

	public void dispose()
	{
		super.dispose();
		HandlerList.unregisterAll(this);
	}
}
