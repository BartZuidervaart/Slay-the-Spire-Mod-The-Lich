package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.LastRemorsePower;

public class LastRemorseCard extends AbstractLichCard {

    public static final String ID = "TheLich:LastRemorseCard";
    public static final	String NAME = "Last Remorse";
    public static final	String DESCRIPTION = "When a minion is sacrificed. Deal 5 damage to all enemies.";
    public static final String UPGRADE_DESCRIPTION = "When a minion is sacrificed. Deal 8 damage to all enemies." ;
    private static final int COST = 3;

    public LastRemorseCard() {
        super(ID, NAME, ImageLibrary.LAST_REMORSE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    }

    public AbstractCard makeCopy() {
        return new LastRemorseCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int amount = this.upgraded ? 8 : 5;
        addToBot(new ApplyPowerAction(p, p, new LastRemorsePower(p,amount)));
    }
}
