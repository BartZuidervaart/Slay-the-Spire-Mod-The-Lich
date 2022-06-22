package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.SpecterMinion;
import ultra.lich.player.LichClass;

public class SpecterCard extends AbstractLichCard {

    public static final String ID = "TheLich:SpecterCard";
    public static final	String NAME = "Specter";
    public static final	String DESCRIPTION = "Summons a specter. Stats 5/0/10. Heals as part of an attack. Gains additional health equal to its defense.";
    public static final String UPGRADE_DESCRIPTION = "Summons a specter. Stats 7/0/15. Heals as part of an attack. Gains additional health equal to its defense." ;
    private static final int COST = 2;

    public SpecterCard() {
        super(ID, NAME, ImageLibrary.SPECTER, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new SpecterCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (p instanceof LichClass) {
            LichClass caster = (LichClass) p;
            SpecterMinion minion = this.upgraded ? new SpecterMinion(caster, 15, 7, 0) : new SpecterMinion(caster, 10, 5, 0);
            addToBot(new SummonMinionAction(caster,minion));
        }
    }
}
