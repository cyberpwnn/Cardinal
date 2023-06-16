package ht.firefig.cardinal.game;

import ninja.bytecode.shuriken.bench.PrecisionStopwatch;

public abstract class CardinalTicked
{
	protected final double tickRate;
	protected double tickRolloverTime;
	protected final PrecisionStopwatch tickProfiler;
	protected final PrecisionStopwatch tickDelta;
	protected final CardinalGame game;
	protected double timeAlive;

	public CardinalTicked(CardinalGame game, double rate)
	{
		this.game = game;
		this.tickRate = rate;
		tickDelta = PrecisionStopwatch.start();
		tickProfiler = PrecisionStopwatch.start();
		timeAlive = 0;
	}
	
	public CardinalGame getGame()
	{
		return game;
	}

	private double getTimeInterval()
	{
		return 1000D / tickRate;
	}

	public void tick()
	{
		tickDelta.end();
		tickDelta.begin();
		tickProfiler.begin();
		timeAlive += tickDelta.getTime();
		tickRolloverTime += tickDelta.getTime();
		double v = getTimeInterval();
		
		while(tickRolloverTime >= v)
		{
			onTick();
			tickRolloverTime -= v;
		}
		
		tickProfiler.end();
	}
	
	public abstract void dispose();

	public abstract void onTick();
}
