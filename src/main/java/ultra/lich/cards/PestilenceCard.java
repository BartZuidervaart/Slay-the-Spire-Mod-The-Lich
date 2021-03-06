package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;

public class PestilenceCard extends AbstractLichCard {

    public static final String ID = "TheLich:PestilenceCard";
    public static final	String NAME = "Pestilence";
    public static final	String DESCRIPTION = "Apply 3 poison to a minion. Poisoned minion attacks apply poison to their target. Fleshrot.";
    public static final	String UPGRADED_DESCRIPTION = "Apply 5 poison to a minion. Poisoned minion attacks apply poison to their target. Fleshrot.";
    private static final int COST = 0;

    public PestilenceCard() {
        super(ID, NAME, ImageLibrary.PESTILENCE, COST, DESCRIPTION, UPGRADED_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new PestilenceCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        int amount = this.upgraded ? 5 : 3;

        this.addToBot(new ApplyPowerAction(target,p, new PoisonPower(target,p,amount)));
        this.addToBot(new MakeTempCardInHandAction(new FleshRot(),1));

    }
}