package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;

public class ProtectionSpellCard extends AbstractLichCard {

    public static final String ID = "TheLich:ProtectionSpellCard";
    public static final	String NAME = "Protection spell";
    public static final	String DESCRIPTION = "Adds !B! block to a Minion.";
    private static final int COST = 1;

    private static int BLOCK = 8;
    private static int UPGRADE_BLOCK= 4;

    public ProtectionSpellCard() {
        super(ID, NAME, ImageLibrary.PROTECTION_SPELL, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
        this.baseBlock = BLOCK;
    }

    public AbstractCard makeCopy() {
        return new ProtectionSpellCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        this.addToBot(new GainBlockAction(target,this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBlock(UPGRADE_BLOCK);
        }
        super.upgrade();
    }
}
