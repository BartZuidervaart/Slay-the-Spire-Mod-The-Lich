package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.SkeletonSentinel;
import ultra.lich.powers.SummonerPower;

public class SkeletonSentinelCard extends AbstractLichCard {

    public static final String ID = "TheLich:SkeletonSentinelCard";
    public static final	String NAME = "Skeleton Sentinel";
    public static final	String DESCRIPTION = "Tribute 1. Summons a skeleton sentinel with stats 3/7/10. Applies frail instead of attack. Controllable. Putrid.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 1. Summons a skeleton sentinel with stats 5/10/15. Applies frail instead of attack. Controllable. Putrid." ;
    private static final int COST = 1;

    public SkeletonSentinelCard() {
        super(ID, NAME, ImageLibrary.SKELETON_SENTINEL, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, MinionTargeting.MINION);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new SkeletonSentinelCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        if (p.hasPower(SummonerPower.POWER_ID)) {
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);
            SkeletonSentinel minion = this.upgraded ? new SkeletonSentinel(caster,15,10,5): new SkeletonSentinel(caster,10,7,3);
            addToBot(new SacrificeMinionAction(caster,target));
            addToBot(new SummonMinionAction(caster,minion));
        }
    }
}

