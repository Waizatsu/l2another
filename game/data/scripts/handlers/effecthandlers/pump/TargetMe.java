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
package handlers.effecthandlers.pump;

import org.l2jdevs.gameserver.model.StatsSet;
import org.l2jdevs.gameserver.model.actor.L2Playable;
import org.l2jdevs.gameserver.model.actor.instance.L2PcInstance;
import org.l2jdevs.gameserver.model.conditions.Condition;
import org.l2jdevs.gameserver.model.effects.AbstractEffect;
import org.l2jdevs.gameserver.model.skills.BuffInfo;

/**
 * Target Me effect implementation.
 * @author -Nemesiss-
 */
public final class TargetMe extends AbstractEffect
{
	public TargetMe(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
	}
	
	@Override
	public void onExit(BuffInfo info)
	{
		if (info.getEffected().isPlayable())
		{
			((L2Playable) info.getEffected()).setLockedTarget(null);
		}
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		if (info.getEffected().isPlayable())
		{
			if (info.getEffected().getTarget() != info.getEffector())
			{
				L2PcInstance effector = info.getEffector().getActingPlayer();
				// If effector is null, then its not a player, but NPC. If its not null, then it should check if the skill is pvp skill.
				if ((effector == null) || effector.checkPvpSkill(info.getEffected(), info.getSkill()))
				{
					// Target is different
					info.getEffected().setTarget(info.getEffector());
				}
			}
			
			((L2Playable) info.getEffected()).setLockedTarget(info.getEffector());
		}
	}
}
