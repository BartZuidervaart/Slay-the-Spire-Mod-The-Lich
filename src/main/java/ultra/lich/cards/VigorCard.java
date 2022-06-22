package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;

public class VigorCard extends AbstractLichCard {

    public static final String ID = "TheLich:VigorCard";
    public static final	String NAME = "Vigor";
    public static final	String DESCRIPTION = "Raise max health of target minion by 10. Exhaust.";
    public static final	String UPGRADED_DESCRIPTION = "Raise max health of target minion by 14. Exhaust.";
    private static final int COST = 1;

    public VigorCard() {
        super(ID, NAME, ImageLibrary.VIGOR, COST, DESCRIPTION, UPGRADED_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, MinionTargeting.MINION);
        this.exhaust = true;
    }

    public AbstractCard makeCopy() {
        return new VigorCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        int hpIncrease = this.upgraded ? 14 : 10;
        target.increaseMaxHp(hpIncrease, true);
    }
}