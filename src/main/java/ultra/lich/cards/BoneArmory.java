package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import ultra.lich.TheLich;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.FlameSkull;

public class BoneArmory extends AbstractLichCard {

    public static final String ID = "Bone_Armory";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = "At the start of your turn : Gain 1 Bones. NL Innate." ;
    private static final int COST = 1;
    private final int AMOUNT = 1;

    public BoneArmory() {
        super(ID, NAME, ImageLibrary.BONE_ARMORY, COST, DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = AMOUNT;


    }

    public AbstractCard makeCopy() {
        return new BoneArmory();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = BoneArmory.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if(p instanceof AbstractPlayerWithMinions){
            AbstractPlayerWithMinions player = (AbstractPlayerWithMinions) p;
            player.addMinion(new FlameSkull(player));
        }
    }
}
