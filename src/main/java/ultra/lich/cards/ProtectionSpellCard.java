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
    public static final	String DESCRIPTION = "Adds 8 block to a Minion.";
    public static final String UPGRADE_DESCRIPTION = "Adds 12 block to a Minion." ;
    private static final int COST = 1;

    public ProtectionSpellCard() {
        super(ID, NAME, ImageLibrary.PROTECTION_SPELL, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new ProtectionSpellCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        int blockAmount = 8;
        if(upgraded){
            blockAmount = 12;
        }

        this.addToBot(new GainBlockAction(target,blockAmount));
    }
}
