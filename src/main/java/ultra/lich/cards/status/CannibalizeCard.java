package ultra.lich.cards.status;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.player.LichClass;
import ultra.lich.powers.SoakPower;

import java.util.ArrayList;

public class CannibalizeCard extends CustomCard {

    public static final String ID = "TheLich:CannibalizeCard";
    public static final	String NAME = "Cannibalize";
    public static final	String DESCRIPTION = "Sacrifice a Minion. All other minions heal equal to its remaining health including its soak divided by the amount of minions. Ethereal. Exhaust.";
    public static final	String UPGRADED_DESCRIPTION = "Sacrifice a Minion. All other minions heal equal to twice its remaining health including its soak divided by the amount of minions. Ethereal. Exhaust.";
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
        if(p instanceof LichClass){
            LichClass caster = (LichClass)p;

            //get info before death
            int currentHealth = target.currentHealth;
            int soak = target.hasPower(SoakPower.POWER_ID) ? target.getPower(SoakPower.POWER_ID).amount : 0;
            int total = currentHealth + soak;

            //kill
            addToBot(new SacrificeMinionAction(caster,target));

            //heal other minions
            ArrayList<AbstractMonster> minions = caster.getMinions().monsters;
            if(minions.size() > 0){ //else this will fire an ArithmeticException for dividing by 0
                int healAmount = this.upgraded ? (Math.round(total / minions.size())*2) : Math.round(total / minions.size());
                minions.forEach(monster -> addToBot(new HealAction(monster,caster,healAmount)));
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
