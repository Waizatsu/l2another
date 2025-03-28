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
package handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.l2jdevs.Config;
import org.l2jdevs.gameserver.handler.ActionHandler;
import org.l2jdevs.gameserver.handler.ActionShiftHandler;
import org.l2jdevs.gameserver.handler.AdminCommandHandler;
import org.l2jdevs.gameserver.handler.BypassHandler;
import org.l2jdevs.gameserver.handler.ChatHandler;
import org.l2jdevs.gameserver.handler.CommunityBoardHandler;
import org.l2jdevs.gameserver.handler.IHandler;
import org.l2jdevs.gameserver.handler.ItemHandler;
import org.l2jdevs.gameserver.handler.PunishmentHandler;
import org.l2jdevs.gameserver.handler.TargetHandler;
import org.l2jdevs.gameserver.handler.TelnetHandler;
import org.l2jdevs.gameserver.handler.UserCommandHandler;
import org.l2jdevs.gameserver.handler.VoicedCommandHandler;

import handlers.actionhandlers.L2ArtefactInstanceAction;
import handlers.actionhandlers.L2DecoyAction;
import handlers.actionhandlers.L2DoorInstanceAction;
import handlers.actionhandlers.L2ItemInstanceAction;
import handlers.actionhandlers.L2NpcAction;
import handlers.actionhandlers.L2PcInstanceAction;
import handlers.actionhandlers.L2PetInstanceAction;
import handlers.actionhandlers.L2StaticObjectInstanceAction;
import handlers.actionhandlers.L2SummonAction;
import handlers.actionhandlers.L2TrapAction;
import handlers.actionshifthandlers.L2DoorInstanceActionShift;
import handlers.actionshifthandlers.L2ItemInstanceActionShift;
import handlers.actionshifthandlers.L2NpcActionShift;
import handlers.actionshifthandlers.L2PcInstanceActionShift;
import handlers.actionshifthandlers.L2StaticObjectInstanceActionShift;
import handlers.actionshifthandlers.L2SummonActionShift;
import handlers.admincommandhandlers.AdminAdmin;
import handlers.admincommandhandlers.AdminAnnouncements;
import handlers.admincommandhandlers.AdminBBS;
import handlers.admincommandhandlers.AdminBuffs;
import handlers.admincommandhandlers.AdminCHSiege;
import handlers.admincommandhandlers.AdminCamera;
import handlers.admincommandhandlers.AdminChangeAccessLevel;
import handlers.admincommandhandlers.AdminClan;
import handlers.admincommandhandlers.AdminCreateItem;
import handlers.admincommandhandlers.AdminCursedWeapons;
import handlers.admincommandhandlers.AdminDebug;
import handlers.admincommandhandlers.AdminDelete;
import handlers.admincommandhandlers.AdminDisconnect;
import handlers.admincommandhandlers.AdminDoorControl;
import handlers.admincommandhandlers.AdminEditChar;
import handlers.admincommandhandlers.AdminEffects;
import handlers.admincommandhandlers.AdminElement;
import handlers.admincommandhandlers.AdminEnchant;
import handlers.admincommandhandlers.AdminEventEngine;
import handlers.admincommandhandlers.AdminEvents;
import handlers.admincommandhandlers.AdminExpSp;
import handlers.admincommandhandlers.AdminFightCalculator;
import handlers.admincommandhandlers.AdminFortSiege;
import handlers.admincommandhandlers.AdminGeodata;
import handlers.admincommandhandlers.AdminGm;
import handlers.admincommandhandlers.AdminGmChat;
import handlers.admincommandhandlers.AdminGraciaSeeds;
import handlers.admincommandhandlers.AdminGrandBoss;
import handlers.admincommandhandlers.AdminHeal;
import handlers.admincommandhandlers.AdminHtml;
import handlers.admincommandhandlers.AdminInstance;
import handlers.admincommandhandlers.AdminInstanceZone;
import handlers.admincommandhandlers.AdminInvul;
import handlers.admincommandhandlers.AdminKick;
import handlers.admincommandhandlers.AdminKill;
import handlers.admincommandhandlers.AdminLevel;
import handlers.admincommandhandlers.AdminLogin;
import handlers.admincommandhandlers.AdminMammon;
import handlers.admincommandhandlers.AdminManor;
import handlers.admincommandhandlers.AdminMenu;
import handlers.admincommandhandlers.AdminMessages;
import handlers.admincommandhandlers.AdminMobGroup;
import handlers.admincommandhandlers.AdminMonsterRace;
import handlers.admincommandhandlers.AdminPForge;
import handlers.admincommandhandlers.AdminPathNode;
import handlers.admincommandhandlers.AdminPcCondOverride;
import handlers.admincommandhandlers.AdminPetition;
import handlers.admincommandhandlers.AdminPledge;
import handlers.admincommandhandlers.AdminPolymorph;
import handlers.admincommandhandlers.AdminPunishment;
import handlers.admincommandhandlers.AdminQuest;
import handlers.admincommandhandlers.AdminReload;
import handlers.admincommandhandlers.AdminRepairChar;
import handlers.admincommandhandlers.AdminRes;
import handlers.admincommandhandlers.AdminRide;
import handlers.admincommandhandlers.AdminScan;
import handlers.admincommandhandlers.AdminShop;
import handlers.admincommandhandlers.AdminShowQuests;
import handlers.admincommandhandlers.AdminShutdown;
import handlers.admincommandhandlers.AdminSiege;
import handlers.admincommandhandlers.AdminSkill;
import handlers.admincommandhandlers.AdminSpawn;
import handlers.admincommandhandlers.AdminSummon;
import handlers.admincommandhandlers.AdminTarget;
import handlers.admincommandhandlers.AdminTargetSay;
import handlers.admincommandhandlers.AdminTeleport;
import handlers.admincommandhandlers.AdminTerritoryWar;
import handlers.admincommandhandlers.AdminTest;
import handlers.admincommandhandlers.AdminTvTEvent;
import handlers.admincommandhandlers.AdminUnblockIp;
import handlers.admincommandhandlers.AdminVitality;
import handlers.admincommandhandlers.AdminZone;
import handlers.bypasshandlers.Augment;
import handlers.bypasshandlers.Buy;
import handlers.bypasshandlers.BuyShadowItem;
import handlers.bypasshandlers.ChatLink;
import handlers.bypasshandlers.ClanWarehouse;
import handlers.bypasshandlers.EventEngine;
import handlers.bypasshandlers.Festival;
import handlers.bypasshandlers.Freight;
import handlers.bypasshandlers.ItemAuctionLink;
import handlers.bypasshandlers.Link;
import handlers.bypasshandlers.Loto;
import handlers.bypasshandlers.Multisell;
import handlers.bypasshandlers.NpcViewMod;
import handlers.bypasshandlers.Observation;
import handlers.bypasshandlers.OlympiadManagerLink;
import handlers.bypasshandlers.OlympiadObservation;
import handlers.bypasshandlers.PlayerHelp;
import handlers.bypasshandlers.PrivateWarehouse;
import handlers.bypasshandlers.QuestLink;
import handlers.bypasshandlers.QuestList;
import handlers.bypasshandlers.ReceivePremium;
import handlers.bypasshandlers.ReleaseAttribute;
import handlers.bypasshandlers.RentPet;
import handlers.bypasshandlers.Rift;
import handlers.bypasshandlers.SkillList;
import handlers.bypasshandlers.SupportBlessing;
import handlers.bypasshandlers.SupportMagic;
import handlers.bypasshandlers.TerritoryStatus;
import handlers.bypasshandlers.TutorialClose;
import handlers.bypasshandlers.VoiceCommand;
import handlers.bypasshandlers.Wear;
import handlers.chathandlers.ChatAll;
import handlers.chathandlers.ChatAlliance;
import handlers.chathandlers.ChatBattlefield;
import handlers.chathandlers.ChatClan;
import handlers.chathandlers.ChatHeroVoice;
import handlers.chathandlers.ChatParty;
import handlers.chathandlers.ChatPartyMatchRoom;
import handlers.chathandlers.ChatPartyRoomAll;
import handlers.chathandlers.ChatPartyRoomCommander;
import handlers.chathandlers.ChatPetition;
import handlers.chathandlers.ChatShout;
import handlers.chathandlers.ChatTell;
import handlers.chathandlers.ChatTrade;
import handlers.communityboard.ClanBoard;
import handlers.communityboard.FavoriteBoard;
import handlers.communityboard.FriendsBoard;
import handlers.communityboard.HomeBoard;
import handlers.communityboard.HomepageBoard;
import handlers.communityboard.MailBoard;
import handlers.communityboard.MemoBoard;
import handlers.communityboard.RegionBoard;
import handlers.itemhandlers.BeastSoulShot;
import handlers.itemhandlers.BeastSpiritShot;
import handlers.itemhandlers.BlessedSpiritShot;
import handlers.itemhandlers.Book;
import handlers.itemhandlers.Bypass;
import handlers.itemhandlers.Calculator;
import handlers.itemhandlers.CharmOfCourage;
import handlers.itemhandlers.Disguise;
import handlers.itemhandlers.Elixir;
import handlers.itemhandlers.EnchantAttribute;
import handlers.itemhandlers.EnchantScrolls;
import handlers.itemhandlers.EventItem;
import handlers.itemhandlers.ExtractableItems;
import handlers.itemhandlers.FishShots;
import handlers.itemhandlers.Harvester;
import handlers.itemhandlers.ItemSkills;
import handlers.itemhandlers.ItemSkillsTemplate;
import handlers.itemhandlers.ManaPotion;
import handlers.itemhandlers.Maps;
import handlers.itemhandlers.MercTicket;
import handlers.itemhandlers.NicknameColor;
import handlers.itemhandlers.PetFood;
import handlers.itemhandlers.Recipes;
import handlers.itemhandlers.RollingDice;
import handlers.itemhandlers.Seed;
import handlers.itemhandlers.SevenSignsRecord;
import handlers.itemhandlers.SoulShots;
import handlers.itemhandlers.SpecialXMas;
import handlers.itemhandlers.SpiritShot;
import handlers.itemhandlers.SummonItems;
import handlers.itemhandlers.TeleportBookmark;
import handlers.punishmenthandlers.BanHandler;
import handlers.punishmenthandlers.ChatBanHandler;
import handlers.punishmenthandlers.JailHandler;
import handlers.targethandlers.Area;
import handlers.targethandlers.AreaCorpseMob;
import handlers.targethandlers.AreaFriendly;
import handlers.targethandlers.AreaSummon;
import handlers.targethandlers.Aura;
import handlers.targethandlers.AuraCorpseMob;
import handlers.targethandlers.AuraFriendly;
import handlers.targethandlers.AuraUndeadEnemy;
import handlers.targethandlers.BehindArea;
import handlers.targethandlers.BehindAura;
import handlers.targethandlers.Clan;
import handlers.targethandlers.ClanMember;
import handlers.targethandlers.CommandChannel;
import handlers.targethandlers.CorpseClan;
import handlers.targethandlers.CorpseMob;
import handlers.targethandlers.Enemy;
import handlers.targethandlers.EnemyOnly;
import handlers.targethandlers.EnemySummon;
import handlers.targethandlers.FlagPole;
import handlers.targethandlers.FrontArea;
import handlers.targethandlers.FrontAura;
import handlers.targethandlers.Ground;
import handlers.targethandlers.Holy;
import handlers.targethandlers.One;
import handlers.targethandlers.OwnerPet;
import handlers.targethandlers.Party;
import handlers.targethandlers.PartyClan;
import handlers.targethandlers.PartyMember;
import handlers.targethandlers.PartyNotMe;
import handlers.targethandlers.PartyOther;
import handlers.targethandlers.PcBody;
import handlers.targethandlers.Pet;
import handlers.targethandlers.Self;
import handlers.targethandlers.Servitor;
import handlers.targethandlers.Summon;
import handlers.targethandlers.TargetParty;
import handlers.targethandlers.Unlockable;
import handlers.telnethandlers.ChatsHandler;
import handlers.telnethandlers.DebugHandler;
import handlers.telnethandlers.HelpHandler;
import handlers.telnethandlers.PlayerHandler;
import handlers.telnethandlers.ReloadHandler;
import handlers.telnethandlers.ServerHandler;
import handlers.telnethandlers.StatusHandler;
import handlers.telnethandlers.ThreadHandler;
import handlers.usercommandhandlers.ChannelDelete;
import handlers.usercommandhandlers.ChannelInfo;
import handlers.usercommandhandlers.ChannelLeave;
import handlers.usercommandhandlers.ClanPenalty;
import handlers.usercommandhandlers.ClanWarsList;
import handlers.usercommandhandlers.Dismount;
import handlers.usercommandhandlers.InstanceZone;
import handlers.usercommandhandlers.Loc;
import handlers.usercommandhandlers.Mount;
import handlers.usercommandhandlers.MyBirthday;
import handlers.usercommandhandlers.OlympiadStat;
import handlers.usercommandhandlers.PartyInfo;
import handlers.usercommandhandlers.SiegeStatus;
import handlers.usercommandhandlers.Time;
import handlers.usercommandhandlers.Unstuck;
import handlers.voicedcommandhandlers.Banking;
import handlers.voicedcommandhandlers.ChangePassword;
import handlers.voicedcommandhandlers.ChatAdmin;
import handlers.voicedcommandhandlers.Debug;
import handlers.voicedcommandhandlers.Lang;
import handlers.voicedcommandhandlers.StatsVCmd;
import handlers.voicedcommandhandlers.Wedding;

/**
 * Master handler.
 * @author UnAfraid
 * @author Zoey76
 */
public class MasterHandler
{
	private static final Logger LOG = LoggerFactory.getLogger(MasterHandler.class);
	
	private static final Class<?>[] ACTION_HANDLERS =
	{
		L2ArtefactInstanceAction.class,
		L2DecoyAction.class,
		L2DoorInstanceAction.class,
		L2ItemInstanceAction.class,
		L2NpcAction.class,
		L2PcInstanceAction.class,
		L2PetInstanceAction.class,
		L2StaticObjectInstanceAction.class,
		L2SummonAction.class,
		L2TrapAction.class,
	};
	
	private static final Class<?>[] ACTION_SHIFT_HANDLERS =
	{
		L2DoorInstanceActionShift.class,
		L2ItemInstanceActionShift.class,
		L2NpcActionShift.class,
		L2PcInstanceActionShift.class,
		L2StaticObjectInstanceActionShift.class,
		L2SummonActionShift.class,
	};
	
	private static final Class<?>[] ADMIN_HANDLERS =
	{
		AdminAdmin.class,
		AdminAnnouncements.class,
		AdminBBS.class,
		AdminBuffs.class,
		AdminCamera.class,
		AdminChangeAccessLevel.class,
		AdminCHSiege.class,
		AdminClan.class,
		AdminPcCondOverride.class,
		AdminCreateItem.class,
		AdminCursedWeapons.class,
		AdminDebug.class,
		AdminDelete.class,
		AdminDisconnect.class,
		AdminDoorControl.class,
		AdminEditChar.class,
		AdminEffects.class,
		AdminElement.class,
		AdminEnchant.class,
		AdminEventEngine.class,
		AdminEvents.class,
		AdminExpSp.class,
		AdminFightCalculator.class,
		AdminFortSiege.class,
		AdminGeodata.class,
		AdminGm.class,
		AdminGmChat.class,
		AdminGraciaSeeds.class,
		AdminGrandBoss.class,
		AdminHeal.class,
		AdminHtml.class,
		AdminInstance.class,
		AdminInstanceZone.class,
		AdminInvul.class,
		AdminKick.class,
		AdminKill.class,
		AdminLevel.class,
		AdminLogin.class,
		AdminMammon.class,
		AdminManor.class,
		AdminMenu.class,
		AdminMessages.class,
		AdminMobGroup.class,
		AdminMonsterRace.class,
		AdminPathNode.class,
		AdminPetition.class,
		AdminPForge.class,
		AdminPledge.class,
		AdminPolymorph.class,
		AdminPunishment.class,
		AdminQuest.class,
		AdminReload.class,
		AdminRepairChar.class,
		AdminRes.class,
		AdminRide.class,
		AdminScan.class,
		AdminShop.class,
		AdminShowQuests.class,
		AdminShutdown.class,
		AdminSiege.class,
		AdminSkill.class,
		AdminSpawn.class,
		AdminSummon.class,
		AdminTarget.class,
		AdminTargetSay.class,
		AdminTeleport.class,
		AdminTerritoryWar.class,
		AdminTest.class,
		AdminTvTEvent.class,
		AdminUnblockIp.class,
		AdminVitality.class,
		AdminZone.class,
	};
	
	private static final Class<?>[] BYPASS_HANDLERS =
	{
		Augment.class,
		Buy.class,
		BuyShadowItem.class,
		ChatLink.class,
		ClanWarehouse.class,
		EventEngine.class,
		Festival.class,
		Freight.class,
		ItemAuctionLink.class,
		Link.class,
		Loto.class,
		Multisell.class,
		NpcViewMod.class,
		Observation.class,
		OlympiadObservation.class,
		OlympiadManagerLink.class,
		QuestLink.class,
		PlayerHelp.class,
		PrivateWarehouse.class,
		QuestList.class,
		ReceivePremium.class,
		ReleaseAttribute.class,
		RentPet.class,
		Rift.class,
		SkillList.class,
		SupportBlessing.class,
		SupportMagic.class,
		TerritoryStatus.class,
		TutorialClose.class,
		VoiceCommand.class,
		Wear.class,
	};
	
	private static final Class<?>[] CHAT_HANDLERS =
	{
		ChatAll.class,
		ChatAlliance.class,
		ChatBattlefield.class,
		ChatClan.class,
		ChatHeroVoice.class,
		ChatParty.class,
		ChatPartyMatchRoom.class,
		ChatPartyRoomAll.class,
		ChatPartyRoomCommander.class,
		ChatPetition.class,
		ChatShout.class,
		ChatTell.class,
		ChatTrade.class,
	};
	
	private static final Class<?>[] COMMUNITY_HANDLERS =
	{
		ClanBoard.class,
		FavoriteBoard.class,
		FriendsBoard.class,
		HomeBoard.class,
		HomepageBoard.class,
		MailBoard.class,
		MemoBoard.class,
		RegionBoard.class,
	};
	
	private static final Class<?>[] ITEM_HANDLERS =
	{
		BeastSoulShot.class,
		BeastSpiritShot.class,
		BlessedSpiritShot.class,
		Book.class,
		Bypass.class,
		Calculator.class,
		CharmOfCourage.class,
		Disguise.class,
		Elixir.class,
		EnchantAttribute.class,
		EnchantScrolls.class,
		EventItem.class,
		ExtractableItems.class,
		FishShots.class,
		Harvester.class,
		ItemSkillsTemplate.class,
		ItemSkills.class,
		ManaPotion.class,
		Maps.class,
		MercTicket.class,
		NicknameColor.class,
		PetFood.class,
		Recipes.class,
		RollingDice.class,
		Seed.class,
		SevenSignsRecord.class,
		SoulShots.class,
		SpecialXMas.class,
		SpiritShot.class,
		SummonItems.class,
		TeleportBookmark.class,
	};
	
	private static final Class<?>[] PUNISHMENT_HANDLERS =
	{
		BanHandler.class,
		ChatBanHandler.class,
		JailHandler.class,
	};
	
	private static final Class<?>[] USER_COMMAND_HANDLERS =
	{
		ClanPenalty.class,
		ClanWarsList.class,
		Dismount.class,
		Unstuck.class,
		InstanceZone.class,
		Loc.class,
		Mount.class,
		PartyInfo.class,
		Time.class,
		OlympiadStat.class,
		ChannelLeave.class,
		ChannelDelete.class,
		ChannelInfo.class,
		MyBirthday.class,
		SiegeStatus.class,
	};
	
	private static final Class<?>[] TARGET_HANDLERS =
	{
		Area.class,
		AreaCorpseMob.class,
		AreaFriendly.class,
		AreaSummon.class,
		Aura.class,
		AuraCorpseMob.class,
		AuraFriendly.class,
		AuraUndeadEnemy.class,
		BehindArea.class,
		BehindAura.class,
		Clan.class,
		ClanMember.class,
		CommandChannel.class,
		CorpseClan.class,
		CorpseMob.class,
		Enemy.class,
		EnemyOnly.class,
		EnemySummon.class,
		FlagPole.class,
		FrontArea.class,
		FrontAura.class,
		Ground.class,
		Holy.class,
		One.class,
		OwnerPet.class,
		Party.class,
		PartyClan.class,
		PartyMember.class,
		PartyNotMe.class,
		PartyOther.class,
		PcBody.class,
		Pet.class,
		Self.class,
		Servitor.class,
		Summon.class,
		TargetParty.class,
		Unlockable.class,
	};
	
	private static final Class<?>[] TELNET_HANDLERS =
	{
		ChatsHandler.class,
		DebugHandler.class,
		HelpHandler.class,
		PlayerHandler.class,
		ReloadHandler.class,
		ServerHandler.class,
		StatusHandler.class,
		ThreadHandler.class,
	};
	
	private static final Class<?>[] VOICED_COMMAND_HANDLERS =
	{
		StatsVCmd.class,
		// TODO: Add configuration options for this voiced commands:
		// CastleVCmd.class,
		// SetVCmd.class,
		(Config.L2JMOD_ALLOW_WEDDING ? Wedding.class : null),
		(Config.BANKING_SYSTEM_ENABLED ? Banking.class : null),
		(Config.L2JMOD_CHAT_ADMIN ? ChatAdmin.class : null),
		(Config.L2JMOD_MULTILANG_ENABLE && Config.L2JMOD_MULTILANG_VOICED_ALLOW ? Lang.class : null),
		(Config.L2JMOD_DEBUG_VOICE_COMMAND ? Debug.class : null),
		(Config.L2JMOD_ALLOW_CHANGE_PASSWORD ? ChangePassword.class : null),
	};
	
	// TODO(Zoey76): Add this handler.
	// private static final Class<?>[] CUSTOM_HANDLERS =
	// {
	// CustomAnnouncePkPvP.class
	// };
	
	public static void main(String[] args)
	{
		LOG.info("Loading Handlers...");
		loadHandlers(VoicedCommandHandler.getInstance(), VOICED_COMMAND_HANDLERS);
		loadHandlers(ActionHandler.getInstance(), ACTION_HANDLERS);
		loadHandlers(ActionShiftHandler.getInstance(), ACTION_SHIFT_HANDLERS);
		loadHandlers(AdminCommandHandler.getInstance(), ADMIN_HANDLERS);
		loadHandlers(BypassHandler.getInstance(), BYPASS_HANDLERS);
		loadHandlers(ChatHandler.getInstance(), CHAT_HANDLERS);
		loadHandlers(CommunityBoardHandler.getInstance(), COMMUNITY_HANDLERS);
		loadHandlers(ItemHandler.getInstance(), ITEM_HANDLERS);
		loadHandlers(PunishmentHandler.getInstance(), PUNISHMENT_HANDLERS);
		loadHandlers(UserCommandHandler.getInstance(), USER_COMMAND_HANDLERS);
		loadHandlers(TargetHandler.getInstance(), TARGET_HANDLERS);
		loadHandlers(TelnetHandler.getInstance(), TELNET_HANDLERS);
		LOG.info("Handlers Loaded...");
	}
	
	private static void loadHandlers(IHandler<?, ?> handler, Class<?>[] classes)
	{
		for (Class<?> c : classes)
		{
			if (c == null)
			{
				continue;
			}
			
			try
			{
				handler.registerByClass(c);
			}
			catch (Exception ex)
			{
				LOG.error("Failed loading handler {}!", c.getSimpleName(), ex);
			}
		}
		LOG.info("{}: Loaded {} handlers.", handler.getClass().getSimpleName(), handler.size());
	}
}