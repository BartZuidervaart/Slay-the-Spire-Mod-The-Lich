package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.player.LichClass;
import ultra.lich.powers.HighPriorityTarget;

public class FrontlineCard extends AbstractLichCard {

    public static final String ID = "TheLich:Frontline_card";
    public static final String NAME = "Frontline";
    public static final String DESCRIPTION = "Makes a minion high priority. Fleshrot.";
    public static final String UPGRADE_DESCRIPTION = "Makes a minion high priority.";
    private static final int COST = 0;

    public FrontlineCard() {
        super(ID, NAME, ImageLibrary.FRONTLINE, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new FrontlineCard();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);

        if(!this.upgraded){
            this.addToBot(new MakeTempCardInHandAction(new FleshRot(),1));
        }

        if (abstractPlayer instanceof LichClass) {
            LichClass caster = (LichClass) abstractPlayer;
            addToBot(new ApplyPowerAction(target, caster, new HighPriorityTarget(target, caster), 1));
        }
    }
}
