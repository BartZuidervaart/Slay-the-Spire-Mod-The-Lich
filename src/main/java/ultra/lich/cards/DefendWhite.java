package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.TheLich;
import ultra.lich.images.ImageLibrary;

public class DefendWhite extends AbstractLichCard {

    public static final String ID = "Defend_W";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public DefendWhite() {
        super(ID, NAME, ImageLibrary.DEFEND_WHITE, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = BLOCK_AMT;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (com.megacrit.cardcrawl.core.Settings.isDebug) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
        } else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
    }

    public AbstractCard makeCopy() {
        return new DefendWhite();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
