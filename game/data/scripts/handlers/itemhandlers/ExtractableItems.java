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

import java.util.List;

import org.l2jdevs.Config;
import org.l2jdevs.gameserver.handler.IItemHandler;
import org.l2jdevs.gameserver.model.L2ExtractableProduct;
import org.l2jdevs.gameserver.model.actor.L2Playable;
import org.l2jdevs.gameserver.model.actor.instance.L2PcInstance;
import org.l2jdevs.gameserver.model.items.L2EtcItem;
import org.l2jdevs.gameserver.model.items.instance.L2ItemInstance;
import org.l2jdevs.gameserver.network.SystemMessageId;
import org.l2jdevs.util.Rnd;

/**
 * Extractable Items handler.
 * @author HorridoJoho
 */
public class ExtractableItems implements IItemHandler
{
	@Override
	public boolean useItem(L2Playable playable, L2ItemInstance item, boolean forceUse)
	{
		if (!playable.isPlayer())
		{
			playable.sendPacket(SystemMessageId.ITEM_NOT_FOR_PETS);
			return false;
		}
		
		final L2PcInstance activeChar = playable.getActingPlayer();
		final L2EtcItem etcitem = (L2EtcItem) item.getItem();
		final List<L2ExtractableProduct> exitem = etcitem.getExtractableItems();
		if (exitem == null)
		{
			_log.info("No extractable data defined for " + etcitem);
			return false;
		}
		
		// destroy item
		if (!activeChar.destroyItem("Extract", item.getObjectId(), 1, activeChar, true))
		{
			return false;
		}
		
		boolean created = false;
		for (L2ExtractableProduct expi : exitem)
		{
			if (Rnd.get(100000) <= expi.getChance())
			{
				final int min = (int) (expi.getMin() * Config.RATE_EXTRACTABLE);
				final int max = (int) (expi.getMax() * Config.RATE_EXTRACTABLE);
				
				int createItemAmount = (max == min) ? min : (Rnd.get((max - min) + 1) + min);
				if (createItemAmount == 0)
				{
					continue;
				}
				
				if (item.isStackable() || (createItemAmount == 1))
				{
					activeChar.addItem("Extract", expi.getId(), createItemAmount, activeChar, true);
				}
				else
				{
					while (createItemAmount > 0)
					{
						activeChar.addItem("Extract", expi.getId(), 1, activeChar, true);
						createItemAmount--;
					}
				}
				created = true;
			}
		}
		
		if (!created)
		{
			activeChar.sendPacket(SystemMessageId.NOTHING_INSIDE_THAT);
		}
		return true;
	}
}
