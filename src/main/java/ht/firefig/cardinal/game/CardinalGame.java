package ht.firefig.cardinal.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import ht.firefig.cardinal.Cardinal;
import ninja.bytecode.shuriken.bench.PrecisionStopwatch;
import ninja.bytecode.shuriken.collections.GList;
import ninja.bytecode.shuriken.collections.GMap;

public class CardinalGame
{
	private double maxTickTime;
	private GMap<TickProiority, GList<CardinalTicked>> ticked;
	private GMap<Player, CardinalPlayer> players;
	private int t;
	
	public CardinalGame()
	{
		t = Bukkit.getScheduler().scheduleSyncRepeatingTask(Cardinal.instance, this::tick, 0, 0);
		maxTickTime = 48;
		players = new GMap<>();
		ticked = new GMap<TickProiority, GList<CardinalTicked>>();
		
		for(TickProiority i : TickProiority.values())
		{
			ticked.put(i, new GList<>());
		}
	}
	
	public void dispose()
	{
		Bukkit.getScheduler().cancelTask(t);
	}
	
	public void unregisterTicked(CardinalTicked t)
	{
		for(TickProiority i : TickProiority.values())
		{
			ticked.get(i).remove(t);
		}
		
		t.dispose();
	}
	
	public void registerTicked(CardinalTicked t)
	{
		registerTicked(TickProiority.NORMAL, t);
	}
	
	public void registerTicked(TickProiority f, CardinalTicked t)
	{
		ticked.get(f).add(t);
	}
	
	public void tick()
	{
		double avalibility = maxTickTime;
		
		for(TickProiority i : TickProiority.values())
		{
			double allocation = i.getAllocation() * avalibility;
			PrecisionStopwatch pst = PrecisionStopwatch.start();
			
			for(int j = 0; j < ticked.get(i).size(); j++)
			{
				CardinalTicked ct = ticked.get(i).getRandom();
				ct.tick();
				
				if(pst.getMilliseconds() >= allocation)
				{
					break;
				}
			}
			
			pst.end();
			avalibility -= pst.getMilliseconds();
			
			if(allocation <= 0)
			{
				break;
			}
		}
	}
	
	public GList<CardinalPlayer> cardinalPlayers()
	{
		return players.v();
	}
	
	public GList<Player> players()
	{
		return players.k();
	}
	
	public boolean isJoinable(Player p)
	{
		return !isPlaying(p);
	}
	
	public boolean join(Player p)
	{
		if(!isJoinable(p))
		{
			return false;
		}
		
		CardinalPlayer c = new CardinalPlayer(p, this);
		players.put(p, c);
		registerTicked(c);
		p.sendMessage("Joined Game");
		return true;
	}
	
	public boolean quit(Player p)
	{
		if(isPlaying(p))
		{
			CardinalPlayer c = getPlayer(p);
			unregisterTicked(c);
			c.dispose();
			players.remove(p);
			return true;
		}
		
		return false;
	}
	
	public boolean isPlaying(Player p)
	{
		return players.containsKey(p);
	}
	
	public CardinalPlayer getPlayer(Player p)
	{
		return players.get(p);
	}

	public Vector getGravity()
	{
		return new Vector(0, -1D / 20D, 0);
	}
}
