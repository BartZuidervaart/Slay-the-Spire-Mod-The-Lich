package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;

public class BloodwellCard extends AbstractLichCard {

    public static final String ID = "TheLich:BloodwellCard";
    public static final	String NAME = "Blood well";
    public static final	String DESCRIPTION = "Heals a minion for 10 hp.";
    private static final int COST = 1;

    public BloodwellCard() {
        super(ID, NAME, ImageLibrary.BLOOD_WELL, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new BloodwellCard();
    }

    @Override
    public void upgrade() {
        this.upgradeBaseCost(0);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        addToBot(new HealAction(target,p, 10));
    }
}
