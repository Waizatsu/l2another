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
package handlers.voicedcommandhandlers;

import java.util.StringTokenizer;

import org.l2jdevs.Config;
import org.l2jdevs.gameserver.datatables.LanguageData;
import org.l2jdevs.gameserver.handler.IVoicedCommandHandler;
import org.l2jdevs.gameserver.model.actor.instance.L2PcInstance;
import org.l2jdevs.gameserver.network.serverpackets.NpcHtmlMessage;
import org.l2jdevs.util.StringUtil;

public class Lang implements IVoicedCommandHandler
{
	private static final String[] VOICED_COMMANDS =
	{
		"lang"
	};
	
	@Override
	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String params)
	{
		if (!Config.L2JMOD_MULTILANG_ENABLE || !Config.L2JMOD_MULTILANG_VOICED_ALLOW)
		{
			return false;
		}
		
		final NpcHtmlMessage msg = new NpcHtmlMessage();
		if (params == null)
		{
			final StringBuilder html = StringUtil.startAppend(100);
			for (String lang : Config.L2JMOD_MULTILANG_ALLOWED)
			{
				StringUtil.append(html, "<button value=\"", lang.toUpperCase(), "\" action=\"bypass -h voice .lang ", lang, "\" width=60 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><br>");
			}
			
			msg.setFile(activeChar.getHtmlPrefix(), "data/html/mods/Lang/LanguageSelect.htm");
			msg.replace("%list%", html.toString());
			activeChar.sendPacket(msg);
			return true;
		}
		
		final StringTokenizer st = new StringTokenizer(params);
		if (st.hasMoreTokens())
		{
			final String lang = st.nextToken().trim();
			if (activeChar.setLang(lang))
			{
				// Multi-Language System
				LanguageData.getInstance().setPlayerLang(activeChar, lang);
				activeChar.sendMessage(LanguageData.getInstance().getMsg(activeChar, "lang_current_successfully") + " " + lang);
				
				msg.setFile(activeChar.getHtmlPrefix(), "data/html/mods/Lang/Ok.htm");
				activeChar.sendPacket(msg);
				return true;
			}
			msg.setFile(activeChar.getHtmlPrefix(), "data/html/mods/Lang/Error.htm");
			activeChar.sendPacket(msg);
			return true;
		}
		return false;
	}
	
	@Override
	public String[] getVoicedCommandList()
	{
		return VOICED_COMMANDS;
	}
}