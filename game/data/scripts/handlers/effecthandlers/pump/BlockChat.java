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

import org.l2jdevs.gameserver.instancemanager.PunishmentManager;
import org.l2jdevs.gameserver.model.StatsSet;
import org.l2jdevs.gameserver.model.conditions.Condition;
import org.l2jdevs.gameserver.model.effects.AbstractEffect;
import org.l2jdevs.gameserver.model.effects.L2EffectType;
import org.l2jdevs.gameserver.model.punishment.PunishmentAffect;
import org.l2jdevs.gameserver.model.punishment.PunishmentTask;
import org.l2jdevs.gameserver.model.punishment.PunishmentType;
import org.l2jdevs.gameserver.model.skills.BuffInfo;

/**
 * Block Chat effect implementation.
 * @author BiggBoss
 */
public final class BlockChat extends AbstractEffect
{
	public BlockChat(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
	}
	
	@Override
	public boolean canStart(BuffInfo info)
	{
		return (info.getEffected() != null) && info.getEffected().isPlayer();
	}
	
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.CHAT_BLOCK;
	}
	
	@Override
	public void onExit(BuffInfo info)
	{
		PunishmentManager.getInstance().stopPunishment(info.getEffected().getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.CHAT_BAN);
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		PunishmentManager.getInstance().startPunishment(new PunishmentTask(0, info.getEffected().getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.CHAT_BAN, 0, "Chat banned bot report", "system", true));
	}
}
