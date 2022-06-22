package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.WightMinion;
import ultra.lich.player.LichClass;

public class WightCard extends AbstractLichCard {

    public static final String ID = "TheLich:WightCard";
    public static final	String NAME = "Wight";
    public static final	String DESCRIPTION = "Tribute 1. Summons a wight with stats 5/0/10. Controllable. Putrid. Can summon a zombie with stats 3/0/5 and 5 soak.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 1. Summons a wight with stats 7/0/15. Controllable. Putrid. Can summon a zombie with stats 3/0/10 and 5 soak." ;
    private static final int COST = 2;

    public WightCard() {
        super(ID, NAME, ImageLibrary.SKELETON_SOLDIER, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, MinionTargeting.MINION);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new WightCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        if (p instanceof LichClass) {
            LichClass caster = (LichClass) p;
            addToBot(new SacrificeMinionAction(caster, target));

            WightMinion minion = new WightMinion(caster,10,5,0,1,3,0,5,5);
            WightMinion upgradedMinion = new WightMinion(caster,15,7,0,1,5,0,10,5);
            addToBot(new SummonMinionAction(caster, this.upgraded ? upgradedMinion : minion));
        }
    }
}
