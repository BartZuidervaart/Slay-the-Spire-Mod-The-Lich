package ultra.lich.cards.status;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SoakPower;
import ultra.lich.powers.SummonerPower;

import java.util.ArrayList;

public class CannibalizeCard extends CustomCard {

    public static final String ID = "TheLich:CannibalizeCard";
    public static final	String NAME = "Cannibalize";
    public static final	String DESCRIPTION = "Sacrifice a Minion. All other minions heal 4 health. Ethereal. Exhaust.";
    public static final	String UPGRADED_DESCRIPTION = "Sacrifice a Minion. All other minions heal 6 health. Ethereal. Exhaust.";
    private static final int COST = 0;

    public CannibalizeCard() {
        super(ID, NAME, ImageLibrary.CANNIBALIZE, COST, DESCRIPTION, AbstractCard.CardType.SKILL, CardColor.COLORLESS,
                CardRarity.SPECIAL, MinionTargeting.MINION);
        this.exhaust = true;
        this.isEthereal = true;
    }

    public AbstractCard makeCopy() {
        return new CannibalizeCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower)p.getPower(SummonerPower.POWER_ID);

            //kill
            addToBot(new SacrificeMinionAction(caster,target));

            //heal other minions
            ArrayList<AbstractMonster> minions = caster.minions.monsters;
            if(minions.size() > 0){ //else this will fire an ArithmeticException for dividing by 0
                int healAmount = this.upgraded ? 6 : 4;
                minions.forEach(monster -> addToBot(new HealAction(monster,caster.owner,healAmount)));
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
