package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.HighPriorityTarget;
import ultra.lich.powers.SummonerPower;

public class FrontlineCard extends AbstractLichCard {

    public static final String ID = "TheLich:Frontline_card";
    public static final String NAME = "Frontline";
    public static final String DESCRIPTION = "Makes a minion high priority. It gains !B! block.";
    private static final int COST = 1;

    public static int BLOCK = 4;

    public FrontlineCard() {
        super(ID, NAME, ImageLibrary.FRONTLINE, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
        this.baseBlock = BLOCK;
    }

    public AbstractCard makeCopy() {
        return new FrontlineCard();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);

        this.addToBot(new GainBlockAction(target,this.block));

        if (abstractPlayer.hasPower(SummonerPower.POWER_ID)) {
            SummonerPower caster = (SummonerPower) abstractPlayer.getPower(SummonerPower.POWER_ID);
            addToBot(new ApplyPowerAction(target, caster.owner, new HighPriorityTarget(target, caster), 1));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.cost = 0;
        }
        super.upgrade();
    }
}
