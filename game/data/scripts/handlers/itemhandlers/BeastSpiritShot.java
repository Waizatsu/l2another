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
package handlers.itemhandlers;

import java.util.logging.Level;

import org.l2jdevs.gameserver.enums.ShotType;
import org.l2jdevs.gameserver.handler.IItemHandler;
import org.l2jdevs.gameserver.model.actor.L2Playable;
import org.l2jdevs.gameserver.model.actor.instance.L2PcInstance;
import org.l2jdevs.gameserver.model.holders.SkillHolder;
import org.l2jdevs.gameserver.model.items.instance.L2ItemInstance;
import org.l2jdevs.gameserver.network.SystemMessageId;
import org.l2jdevs.gameserver.network.serverpackets.MagicSkillUse;
import org.l2jdevs.gameserver.network.serverpackets.SystemMessage;
import org.l2jdevs.gameserver.util.Broadcast;

/**
 * Beast SpiritShot Handler
 * @author Tempy
 */
public class BeastSpiritShot implements IItemHandler
{
	@Override
	public boolean useItem(L2Playable playable, L2ItemInstance item, boolean forceUse)
	{
		if (!playable.isPlayer())
		{
			playable.sendPacket(SystemMessageId.ITEM_NOT_FOR_PETS);
			return false;
		}
		
		final L2PcInstance activeOwner = playable.getActingPlayer();
		if (!activeOwner.hasSummon())
		{
			activeOwner.sendPacket(SystemMessageId.PETS_ARE_NOT_AVAILABLE_AT_THIS_TIME);
			return false;
		}
		
		if (activeOwner.getSummon().isDead())
		{
			activeOwner.sendPacket(SystemMessageId.SOULSHOTS_AND_SPIRITSHOTS_ARE_NOT_AVAILABLE_FOR_A_DEAD_PET);
			return false;
		}
		
		final int itemId = item.getId();
		final boolean isBlessed = ((itemId == 6647) || (itemId == 20334)); // TODO: Unhardcode these!
		final short shotConsumption = activeOwner.getSummon().getSpiritShotsPerHit();
		final SkillHolder[] skills = item.getItem().getSkills();
		
		if (skills == null)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": is missing skills!");
			return false;
		}
		
		long shotCount = item.getCount();
		if (shotCount < shotConsumption)
		{
			// Not enough SpiritShots to use.
			if (!activeOwner.disableAutoShot(itemId))
			{
				activeOwner.sendPacket(SystemMessageId.NOT_ENOUGH_SPIRITHOTS_FOR_PET);
			}
			return false;
		}
		
		if (activeOwner.getSummon().isChargedShot(isBlessed ? ShotType.BLESSED_SPIRITSHOTS : ShotType.SPIRITSHOTS))
		{
			// shots are already active.
			return false;
		}
		
		if (!activeOwner.destroyItemWithoutTrace("Consume", item.getObjectId(), shotConsumption, null, false))
		{
			if (!activeOwner.disableAutoShot(itemId))
			{
				activeOwner.sendPacket(SystemMessageId.NOT_ENOUGH_SPIRITHOTS_FOR_PET);
			}
			return false;
		}
		
		activeOwner.getSummon().setChargedShot(isBlessed ? ShotType.BLESSED_SPIRITSHOTS : ShotType.SPIRITSHOTS, true);
		
		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.USE_S1_);
		sm.addItemName(itemId);
		activeOwner.sendPacket(sm);
		
		activeOwner.sendPacket(SystemMessageId.PET_USE_SPIRITSHOT);
		Broadcast.toSelfAndKnownPlayersInRadius(activeOwner, new MagicSkillUse(activeOwner.getSummon(), activeOwner.getSummon(), skills[0].getSkillId(), skills[0].getSkillLvl(), 0, 0), 600);
		return true;
	}
}
