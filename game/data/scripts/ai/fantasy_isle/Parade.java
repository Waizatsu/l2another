/*
 * Copyright © 2004-2019 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.fantasy_isle;

import java.text.SimpleDateFormat;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;

import org.l2jdevs.gameserver.GameTimeController;
import org.l2jdevs.gameserver.ThreadPoolManager;
import org.l2jdevs.gameserver.ai.CtrlIntention;
import org.l2jdevs.gameserver.model.Location;
import org.l2jdevs.gameserver.model.actor.L2Npc;

import ai.npc.AbstractNpcAI;

/**
 * Fantasy Isle Parade AI.
 * @author JOJO, Pandragon, Sacrifice
 */
public final class Parade extends AbstractNpcAI
{
	// @formatter:off
	protected static final int[] ACTORS =
	{
		32379,	0,	32379,
		32379,	0,	32379,
		32379,	0,	32379,
		0,	0,	0,
		32380,	0,	32380,
		32380,	32381,	32380,
		32380,	0,	32380,
		32380,	32381,	32380,
		0,	0,	0,
		32382,	32382,	32382,
		32382,	32383,	32382,
		32383,	32384,	32383,
		32383,	32384,	32383,
		0,	0,	0,
		0,	32385,	0,
		32385,	0,	32385,
		0,	32385,	0,
		0,	0,	0,
		32412,	0,	32411,
		0,	0,	0,
		32421,	0,	32409,
		32423,	0,	32422,
		0,	0,	0,
		32420,	32419,	32417,
		32418,	0,	32416,
		0,	0,	0,
		32414,	0,	32414,
		0,	32413,	0,
		32414,	0,	32414,
		0,	0,	0,
		32393,	0,	32394,
		0,	32430,	0,
		32392,	0,	32391,
		0,	0,	0,
		0,	32404,	0,
		32403,	0,	32401,
		0,	0,	0,
		0,	32408,	0,
		32406,	0,	32407,
		0,	32405,	0,
		0,	0,	0,
		32390,	32389,	32387,
		32388,	0,	32386,
		0,	0,	0,
		0,	32400,	0,
		32397,	32398,	32396,
		0,	0,	0,
		0,	32450,	0,
		32448,	32449,	32447,
		0,	0,	0,
		32380,	0,	32380,
		32380,	32381,	32380,
		32380,	0,	32380,
		32380,	32381,	32380,
		0,	0,	0,
		32379,	0,	32379,
		32379,	0,	32379,
		32379,	0,	32379,
		0,	0,	0,
		0,	32415,	0
	};
	
	// (Northbound 270 degrees) Route 1
	private static final int[][] START1 = {{-54780, -56810, -2015, 49152}, {-54860, -56810, -2015, 49152}, {-54940, -56810, -2015, 49152}};
	private static final int[][] GOAL1  = {{-54780, -57965, -2015, 49152}, {-54860, -57965, -2015, 49152}, {-54940, -57965, -2015, 49152}};
	// (Westbound 180 degrees) Route 2
	private static final int[][] START2 = {{-55715, -58900, -2015, 32768}, {-55715, -58820, -2015, 32768}, {-55715, -58740, -2015, 32768}};
	private static final int[][] GOAL2  = {{-60850, -58900, -2015, 32768}, {-60850, -58820, -2015, 32768}, {-60850, -58740, -2015, 32768}};
	// (Southbound 90 degrees) Route 3
	private static final int[][] START3 = {{-61790, -57965, -2015, 16384}, {-61710, -57965, -2015, 16384}, {-61630, -57965, -2015, 16384}};
	private static final int[][] GOAL3  = {{-61790, -53890, -2116, 16384}, {-61710, -53890, -2116, 16384}, {-61630, -53890, -2116, 16384}};
	// (Eastbound 0 degrees) Route 4
	private static final int[][] START4 = {{-60840, -52990, -2108, 0}, {-60840, -53070, -2108, 0}, {-60840, -53150, -2108, 0}};
	private static final int[][] GOAL4  = {{-58620, -52990, -2015, 0}, {-58620, -53070, -2015, 0}, {-58620, -53150, -2015, 0}};
	// (To 315 degrees northeast) Route 5
	private static final int[][] START5 = {{-57233, -53554, -2015, 57344}, {-57290, -53610, -2015, 57344}, {-57346, -53667, -2015, 57344}};
	private static final int[][] GOAL5  = {{-55338, -55435, -2015, 57344}, {-55395, -55491, -2015, 57344}, {-55451, -55547, -2015, 57344}};
	
	protected static final int[][][] START = {START1, START2, START3, START4, START5};
	protected static final int[][][] GOAL = {GOAL1, GOAL2, GOAL3, GOAL4, GOAL5};
	// @formatter:on
	
	protected static int npcIndex;
	protected static CopyOnWriteArrayList<L2Npc> spawns;
	protected static ScheduledFuture<?> spawnTask;
	protected static ScheduledFuture<?> deleteTask;
	protected static ScheduledFuture<?> cleanTask;
	
	private Parade()
	{
		super(Parade.class.getSimpleName(), "ai/fantasy_isle");
		
		// Starts at 8:00 and repeats every 6 hours.
		final long diff = timeLeftMillis(8, 0, 0), cycle = 3600000L;
		ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new Start(), diff, cycle);
		
		// Test - Starts 3 minutes after server startup and repeats every 20 minutes.
		// final long diff = timeLeftMillis(8, 0, 0), cycle = 600000L;
		// ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new Start(), 180000L, cycle);
		
		final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		_log.info("Fantasy Isle: Parade starting at " + format.format(System.currentTimeMillis() + diff) + " and is scheduled each next " + (cycle / 3600000) + " hours.");
	}
	
	protected void load()
	{
		npcIndex = 0;
		spawns = new CopyOnWriteArrayList<>();
	}
	
	protected void clean()
	{
		if (spawns != null)
		{
			spawns.forEach(L2Npc::deleteMe);
		}
		spawns = null;
	}
	
	private long timeLeftMillis(int hours, int minutes, int seconds)
	{
		int now = (GameTimeController.getInstance().getGameTicks() * 60) / 100;
		int days = ((hours * 3600) + (minutes * 60) + seconds) - (now % 86400);
		if (days < 0)
		{
			days += 86400;
		}
		return (days * 1000L) / 6L;
	}
	
	protected class Start implements Runnable
	{
		@Override
		public void run()
		{
			load();
			spawnTask = ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new Spawn(), 0, 5000);
			deleteTask = ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new Delete(), 10000, 1000);
			cleanTask = ThreadPoolManager.getInstance().scheduleAi(new Clean(), 420000);
		}
	}
	
	protected class Spawn implements Runnable
	{
		@Override
		public void run()
		{
			for (int i = 0; i < 3; ++i)
			{
				if (npcIndex >= ACTORS.length)
				{
					spawnTask.cancel(false);
					break;
				}
				int npcId = ACTORS[npcIndex++];
				if (npcId == 0)
				{
					continue;
				}
				for (int route = 0; route < 5; ++route)
				{
					int[] start = START[route][i];
					int[] goal = GOAL[route][i];
					L2Npc actor = addSpawn(npcId, start[0], start[1], start[2], start[3], false, 0);
					actor.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(goal[0], goal[1], goal[2], goal[3]));
					spawns.add(actor);
				}
			}
		}
	}
	
	protected class Delete implements Runnable
	{
		@Override
		public void run()
		{
			if (spawns.size() > 0)
			{
				for (L2Npc actor : spawns)
				{
					if (actor != null)
					{
						if (actor.calculateDistance(actor.getXdestination(), actor.getYdestination(), 0, false, true) < (100 * 100))
						{
							actor.deleteMe();
							spawns.remove(actor);
						}
						else if (!actor.isMoving())
						{
							actor.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(actor.getXdestination(), actor.getYdestination(), actor.getZdestination(), actor.getHeading()));
						}
					}
				}
				if (spawns.size() == 0)
				{
					deleteTask.cancel(false);
				}
			}
		}
	}
	
	protected class Clean implements Runnable
	{
		@Override
		public void run()
		{
			spawnTask.cancel(false);
			spawnTask = null;
			deleteTask.cancel(false);
			deleteTask = null;
			cleanTask.cancel(false);
			cleanTask = null;
			clean();
		}
	}
	
	public static void main(String[] args)
	{
		new Parade();
	}
}