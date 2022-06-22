package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.powers.PutridPower;

public class ElixirCard extends AbstractLichCard {

    public static final String ID = "TheLich:ElixirCard";
    public static final	String NAME = "Elixir";
    public static final	String DESCRIPTION = "Discard 1 card. Removes 1 putrid from a Minion.";
    public static final	String UPGRADE_DESCRIPTION = "Discard 1 card. Removes 2 putrid from a Minion.";
    private static final int COST = 0;

    public ElixirCard() {
        super(ID, NAME, ImageLibrary.ELIXIR, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new ElixirCard();
    }

    @Override
    public void upgrade() {
        this.upgradeBaseCost(0);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        addToBot(new DiscardAction(p, p, 1, false));
        AbstractPower power = target.getPower(PutridPower.POWER_ID);
        if(target.hasPower(PutridPower.POWER_ID) && (power.amount > 0 || power.amount == -1)){
            int removeAmount = this.upgraded ? 2 : 1;
            power.amount = power.amount - removeAmount;
            if(power.amount <= 0){
                addToBot(new RemoveSpecificPowerAction(target,p,PutridPower.POWER_ID));
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        return abstractPlayer.hand.size() >= 1;
    }
}