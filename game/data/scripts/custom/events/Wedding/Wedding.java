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
package custom.events.Wedding;

import org.l2jdevs.Config;
import org.l2jdevs.gameserver.datatables.LanguageData;
import org.l2jdevs.gameserver.instancemanager.CoupleManager;
import org.l2jdevs.gameserver.model.L2World;
import org.l2jdevs.gameserver.model.actor.L2Npc;
import org.l2jdevs.gameserver.model.actor.instance.L2PcInstance;
import org.l2jdevs.gameserver.model.entity.Couple;
import org.l2jdevs.gameserver.model.items.instance.L2ItemInstance;
import org.l2jdevs.gameserver.model.skills.CommonSkill;
import org.l2jdevs.gameserver.model.skills.Skill;
import org.l2jdevs.gameserver.network.serverpackets.MagicSkillUse;
import org.l2jdevs.gameserver.network.serverpackets.NpcHtmlMessage;
import org.l2jdevs.gameserver.util.Broadcast;

import ai.npc.AbstractNpcAI;

/**
 * Wedding AI.
 * @author Zoey76
 */
public final class Wedding extends AbstractNpcAI
{
	// NPC
	private static final int MANAGER_ID = 50007;
	// Item
	private static final int FORMAL_WEAR = 6408;
	
	public Wedding()
	{
		super(Wedding.class.getSimpleName(), "custom/events");
		addFirstTalkId(MANAGER_ID);
		addTalkId(MANAGER_ID);
		addStartNpc(MANAGER_ID);
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		if (player.getPartnerId() == 0)
		{
			return "NoPartner.html";
		}
		
		final L2PcInstance partner = L2World.getInstance().getPlayer(player.getPartnerId());
		if ((partner == null) || !partner.isOnline())
		{
			return "NotFound.html";
		}
		
		if (player.isMarried())
		{
			return "Already.html";
		}
		
		if (player.isMarryAccepted())
		{
			return "WaitForPartner.html";
		}
		
		String htmltext = null;
		if (player.isMarryRequest())
		{
			if (!isWearingFormalWear(player) || !isWearingFormalWear(partner))
			{
				htmltext = sendHtml(partner, "NoFormal.html", null, null);
			}
			else
			{
				player.setMarryRequest(false);
				partner.setMarryRequest(false);
				htmltext = getHtm(player.getHtmlPrefix(), "Ask.html");
				htmltext = htmltext.replaceAll("%player%", partner.getName());
			}
			return htmltext;
		}
		
		switch (event)
		{
			case "ask":
			{
				if (!isWearingFormalWear(player) || !isWearingFormalWear(partner))
				{
					htmltext = sendHtml(partner, "NoFormal.html", null, null);
				}
				else
				{
					player.setMarryAccepted(true);
					partner.setMarryRequest(true);
					
					sendHtml(partner, "Ask.html", "%player%", player.getName());
					
					htmltext = getHtm(player.getHtmlPrefix(), "Requested.html");
					htmltext = htmltext.replaceAll("%player%", partner.getName());
				}
				break;
			}
			case "accept":
			{
				if (!isWearingFormalWear(player) || !isWearingFormalWear(partner))
				{
					htmltext = sendHtml(partner, "NoFormal.html", null, null);
				}
				else if ((player.getAdena() < Config.L2JMOD_WEDDING_PRICE) || (partner.getAdena() < Config.L2JMOD_WEDDING_PRICE))
				{
					htmltext = sendHtml(partner, "Adena.html", "%fee%", String.valueOf(Config.L2JMOD_WEDDING_PRICE));
				}
				else
				{
					player.reduceAdena("Wedding", Config.L2JMOD_WEDDING_PRICE, player.getLastFolkNPC(), true);
					partner.reduceAdena("Wedding", Config.L2JMOD_WEDDING_PRICE, player.getLastFolkNPC(), true);
					
					// Accept the wedding request
					player.setMarryAccepted(true);
					Couple couple = CoupleManager.getInstance().getCouple(player.getCoupleId());
					couple.marry();
					
					// Messages to the couple
					player.sendMessage(LanguageData.getInstance().getMsg(player, "dp_wedding_married"));
					player.setMarried(true);
					player.setMarryRequest(false);
					partner.sendMessage(LanguageData.getInstance().getMsg(player, "dp_wedding_married"));
					partner.setMarried(true);
					partner.setMarryRequest(false);
					
					// Wedding march
					player.broadcastPacket(new MagicSkillUse(player, player, 2230, 1, 1, 0));
					partner.broadcastPacket(new MagicSkillUse(partner, partner, 2230, 1, 1, 0));
					
					// Fireworks
					Skill skill = CommonSkill.LARGE_FIREWORK.getSkill();
					if (skill != null)
					{
						player.doCast(skill);
						partner.doCast(skill);
					}
					
					Broadcast.toAllOnlinePlayers(LanguageData.getInstance().getMsg(player, "dp_wedding_married_notice").replace("%s%", player.getName() + "").replace("%i%", partner.getName() + ""));
					htmltext = sendHtml(partner, "Accepted.html", null, null);
				}
				break;
			}
			case "decline":
			{
				player.setMarryRequest(false);
				partner.setMarryRequest(false);
				player.setMarryAccepted(false);
				partner.setMarryAccepted(false);
				
				player.sendMessage(LanguageData.getInstance().getMsg(player, "dp_wedding_declined"));
				partner.sendMessage(LanguageData.getInstance().getMsg(player, "dp_wedding_declined_notice"));
				
				htmltext = sendHtml(partner, "Declined.html", null, null);
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		final String htmltext = getHtm(player.getHtmlPrefix(), "Start.html");
		return htmltext.replaceAll("%fee%", String.valueOf(Config.L2JMOD_WEDDING_PRICE));
	}
	
	private String sendHtml(L2PcInstance player, String fileName, String regex, String replacement)
	{
		String html = getHtm(player.getHtmlPrefix(), fileName);
		if ((regex != null) && (replacement != null))
		{
			html = html.replaceAll(regex, replacement);
		}
		player.sendPacket(new NpcHtmlMessage(html));
		return html;
	}
	
	private static boolean isWearingFormalWear(L2PcInstance player)
	{
		if (Config.L2JMOD_WEDDING_FORMALWEAR)
		{
			final L2ItemInstance formalWear = player.getChestArmorInstance();
			return (formalWear != null) && (formalWear.getId() == FORMAL_WEAR);
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		new Wedding();
	}
}
