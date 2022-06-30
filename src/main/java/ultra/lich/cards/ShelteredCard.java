package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.LowPriorityTarget;
import ultra.lich.powers.SummonerPower;

public class ShelteredCard extends AbstractLichCard {

    public static final String ID = "TheLich:Sheltered_card";
    public static final String NAME = "Sheltered";
    public static final String DESCRIPTION = "Makes a minion low priority. It gains !B! block.";
    private static final int COST = 1;

    public static int BLOCK = 4;

    public static final Logger LOGGER = LogManager.getLogger(ShelteredCard.class.getName());

    public ShelteredCard() {
        super(ID, NAME, ImageLibrary.SHELTERED, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
        this.baseBlock = BLOCK;
    }

    public AbstractCard makeCopy() {
        return new ShelteredCard();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);

        this.addToBot(new GainBlockAction(target,this.block));

        if (abstractPlayer.hasPower(SummonerPower.POWER_ID)) {
            SummonerPower caster = (SummonerPower) abstractPlayer.getPower(SummonerPower.POWER_ID);
            addToBot(new ApplyPowerAction(target, caster.owner, new LowPriorityTarget(target, caster), 1));
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
