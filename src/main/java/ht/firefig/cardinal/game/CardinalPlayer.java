package ht.firefig.cardinal.game;

import org.bukkit.entity.Player;

public class CardinalPlayer extends CardinalTicked
{
	private Player player;

	public CardinalPlayer(Player player, CardinalGame game)
	{
		super(game, 20);
		this.player = player;
	}

	public void onTick()
	{

	}

	public void dispose()
	{

	}

	public Player getPlayer()
	{
		return player;
	}
}
