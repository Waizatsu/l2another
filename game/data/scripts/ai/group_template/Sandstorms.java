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
package ai.group_template;

import org.l2jdevs.gameserver.model.actor.L2Npc;
import org.l2jdevs.gameserver.model.actor.instance.L2PcInstance;
import org.l2jdevs.gameserver.model.holders.SkillHolder;

import ai.npc.AbstractNpcAI;

/**
 * Sandstorms AI.
 * @author Ectis
 */
public class Sandstorms extends AbstractNpcAI
{
	// NPCs
	private static final int SANDSTORM = 32350;
	// Skills
	private static final SkillHolder GUST = new SkillHolder(5435); // Gust
	
	public Sandstorms()
	{
		super(Sandstorms.class.getSimpleName(), "ai/group_template");
		addAggroRangeEnterId(SANDSTORM); // Sandstorm
	}
	
	@Override
	public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isSummon)
	{
		npc.setTarget(player);
		npc.doCast(GUST);
		return super.onAggroRangeEnter(npc, player, isSummon);
	}
	
	public static void main(String[] args)
	{
		new Sandstorms();
	}
}
