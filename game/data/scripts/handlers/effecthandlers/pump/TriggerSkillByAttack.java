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

import org.l2jdevs.gameserver.enums.InstanceType;
import org.l2jdevs.gameserver.handler.ITargetTypeHandler;
import org.l2jdevs.gameserver.handler.TargetHandler;
import org.l2jdevs.gameserver.model.L2Object;
import org.l2jdevs.gameserver.model.StatsSet;
import org.l2jdevs.gameserver.model.actor.L2Character;
import org.l2jdevs.gameserver.model.conditions.Condition;
import org.l2jdevs.gameserver.model.effects.AbstractEffect;
import org.l2jdevs.gameserver.model.events.EventType;
import org.l2jdevs.gameserver.model.events.impl.character.OnCreatureDamageDealt;
import org.l2jdevs.gameserver.model.events.listeners.ConsumerEventListener;
import org.l2jdevs.gameserver.model.holders.SkillHolder;
import org.l2jdevs.gameserver.model.items.type.WeaponType;
import org.l2jdevs.gameserver.model.skills.BuffInfo;
import org.l2jdevs.gameserver.model.skills.Skill;
import org.l2jdevs.gameserver.model.skills.targets.L2TargetType;
import org.l2jdevs.gameserver.util.Util;
import org.l2jdevs.util.Rnd;

/**
 * Trigger Skill By Attack effect implementation.
 * @author Zealar
 */
public final class TriggerSkillByAttack extends AbstractEffect
{
	private final int _minAttackerLevel;
	private final int _maxAttackerLevel;
	private final int _minDamage;
	private final int _chance;
	private final SkillHolder _skill;
	private final L2TargetType _targetType;
	private final InstanceType _attackerType;
	private int _allowWeapons;
	private final boolean _isCritical;
	
	/**
	 * @param attachCond
	 * @param applyCond
	 * @param set
	 * @param params
	 */
	
	public TriggerSkillByAttack(Condition attachCond, Condition applyCond, StatsSet set, StatsSet params)
	{
		super(attachCond, applyCond, set, params);
		
		_minAttackerLevel = params.getInt("minAttackerLevel", 1);
		_maxAttackerLevel = params.getInt("maxAttackerLevel", 100);
		_minDamage = params.getInt("minDamage", 1);
		_chance = params.getInt("chance", 100);
		_skill = new SkillHolder(params.getInt("skillId"), params.getInt("skillLevel", 1));
		_targetType = params.getEnum("targetType", L2TargetType.class, L2TargetType.SELF);
		_attackerType = params.getEnum("attackerType", InstanceType.class, InstanceType.L2Character);
		_isCritical = params.getBoolean("isCritical", false);
		
		if (params.getString("allowWeapons").equalsIgnoreCase("ALL"))
		{
			_allowWeapons = 0;
		}
		else
		{
			for (String s : params.getString("allowWeapons").split(","))
			{
				_allowWeapons |= WeaponType.valueOf(s).mask();
			}
		}
	}
	
	public void onAttackEvent(OnCreatureDamageDealt event)
	{
		if ((event.getSkill() != null) || event.isDamageOverTime() || event.isReflect() || (_chance == 0) || ((_skill.getSkillId() == 0) || (_skill.getSkillLvl() == 0)))
		{
			return;
		}
		
		if (((_targetType == L2TargetType.SELF) && (_skill.getSkill().getCastRange() > 0)) && (Util.calculateDistance(event.getAttacker(), event.getTarget(), true, false) > _skill.getSkill().getCastRange()))
		{
			return;
		}
		
		if (_isCritical != event.isCritical())
		{
			return;
		}
		
		final ITargetTypeHandler targetHandler = TargetHandler.getInstance().getHandler(_targetType);
		if (targetHandler == null)
		{
			_log.warning("Handler for target type: " + _targetType + " does not exist.");
			return;
		}
		
		if (event.getAttacker() == event.getTarget())
		{
			return;
		}
		
		if ((event.getAttacker().getLevel() < _minAttackerLevel) || (event.getAttacker().getLevel() > _maxAttackerLevel))
		{
			return;
		}
		
		if ((event.getDamage() < _minDamage) || (Rnd.get(100) > _chance) || !event.getAttacker().getInstanceType().isType(_attackerType))
		{
			return;
		}
		
		if (_allowWeapons > 0)
		{
			if ((event.getAttacker().getActiveWeaponItem() == null) || ((event.getAttacker().getActiveWeaponItem().getItemType().mask() & _allowWeapons) == 0))
			{
				return;
			}
		}
		
		final Skill triggerSkill = _skill.getSkill();
		final L2Object[] targets = targetHandler.getTargetList(triggerSkill, event.getAttacker(), false, event.getTarget());
		
		for (L2Object triggerTarget : targets)
		{
			if ((triggerTarget == null) || !triggerTarget.isCharacter())
			{
				continue;
			}
			
			final L2Character targetChar = (L2Character) triggerTarget;
			if (!targetChar.isInvul())
			{
				event.getAttacker().makeTriggerCast(triggerSkill, targetChar);
			}
		}
	}
	
	@Override
	public void onExit(BuffInfo info)
	{
		info.getEffected().removeListenerIf(EventType.ON_CREATURE_DAMAGE_DEALT, listener -> listener.getOwner() == this);
	}
	
	@Override
	public void onStart(BuffInfo info)
	{
		info.getEffected().addListener(new ConsumerEventListener(info.getEffected(), EventType.ON_CREATURE_DAMAGE_DEALT, (OnCreatureDamageDealt event) -> onAttackEvent(event), this));
	}
}
