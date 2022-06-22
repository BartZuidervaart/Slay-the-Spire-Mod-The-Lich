package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.SkeletonPikeman;
import ultra.lich.player.LichClass;

public class SkeletonPikemanCard extends AbstractLichCard {

    public static final String ID = "TheLich:SkeletonPikemanCard";
    public static final	String NAME = "Skeleton Pikeman";
    public static final	String DESCRIPTION = "Tribute 1. Summons a skeleton pikeman with stats 7/3/10. Applies vulnerable instead of defend. Controllable. Putrid.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 1. Summons a skeleton pikeman with stats 10/5/15. Applies vulnerable instead of defend. Controllable. Putrid." ;
    private static final int COST = 1;

    public SkeletonPikemanCard() {
        super(ID, NAME, ImageLibrary.SKELETON_PIKEMAN, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, MinionTargeting.MINION);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new SkeletonPikemanCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);

        if (p instanceof LichClass) {
            LichClass caster = (LichClass) p;
            SkeletonPikeman minion = this.upgraded ? new SkeletonPikeman(caster,15,10,5): new SkeletonPikeman(caster,10,7,3);
            addToBot(new SacrificeMinionAction(caster,target));
            addToBot(new SummonMinionAction(caster,minion));
        }
    }
}