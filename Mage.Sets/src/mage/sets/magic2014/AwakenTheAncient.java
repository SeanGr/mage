/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.magic2014;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.AttachEffect;
import mage.abilities.effects.common.continuous.BecomesCreatureAttachedEffect;
import mage.abilities.keyword.EnchantAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.common.FilterLandPermanent;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.game.permanent.token.Token;
import mage.target.TargetPermanent;
import mage.target.common.TargetLandPermanent;

/**
 *
 * @author jeffwadsworth
 */
public class AwakenTheAncient extends CardImpl {

    private static final FilterLandPermanent filter = new FilterLandPermanent("Mountain");

    static {
        filter.add(new SubtypePredicate("Mountain"));
    }

    public AwakenTheAncient(UUID ownerId) {
        super(ownerId, 126, "Awaken the Ancient", Rarity.RARE, new CardType[]{CardType.ENCHANTMENT}, "{1}{R}{R}{R}");
        this.expansionSetCode = "M14";
        this.subtype.add("Aura");

        this.color.setRed(true);

        // Enchant Mountain
        TargetPermanent auraTarget = new TargetLandPermanent(filter);
        this.getSpellAbility().addTarget(auraTarget);
        this.getSpellAbility().addEffect(new AttachEffect(Outcome.PutCreatureInPlay));
        Ability ability = new EnchantAbility(auraTarget.getTargetName());
        this.addAbility(ability);

        // Enchanted Mountain is a 7/7 red Giant creature with haste. It's still a land.
        Ability ability2 = new SimpleStaticAbility(Zone.BATTLEFIELD, new BecomesCreatureAttachedEffect(new GiantToken(), "Enchanted Mountain is a 7/7 red Giant creature with haste. It's still a land", Duration.WhileOnBattlefield));
        this.addAbility(ability2);

    }

    public AwakenTheAncient(final AwakenTheAncient card) {
        super(card);
    }

    @Override
    public AwakenTheAncient copy() {
        return new AwakenTheAncient(this);
    }

    private class GiantToken extends Token {

        GiantToken() {
            super("Giant", "7/7 red Giant creature with haste");
            cardType.add(CardType.CREATURE);
            color.setRed(true);
            subtype.add("Giant");
            power = new MageInt(7);
            toughness = new MageInt(7);
            this.addAbility(HasteAbility.getInstance());
        }
    }
}
