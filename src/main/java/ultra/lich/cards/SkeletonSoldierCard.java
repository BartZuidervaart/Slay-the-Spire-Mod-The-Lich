package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.SkeletonSoldier;
import ultra.lich.powers.SummonerPower;

public class SkeletonSoldierCard extends AbstractLichCard {

    public static final String ID = "TheLich:SkeletonSwordShieldCard";
    public static final	String NAME = "Skeleton Soldier";
    public static final	String DESCRIPTION = "Tribute 1. Summons a skeleton soldier with stats 5/5/10. Controllable. Putrid.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 1. Summons a skeleton soldier with stats 7/7/15. Controllable. Putrid." ;
    private static final int COST = 1;

    public SkeletonSoldierCard() {
        super(ID, NAME, ImageLibrary.SKELETON_SOLDIER, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, MinionTargeting.MINION);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new SkeletonSoldierCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        if (p.hasPower(SummonerPower.POWER_ID)) {
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);
            SkeletonSoldier minion = this.upgraded ? new SkeletonSoldier(caster,15,7,7) : new SkeletonSoldier(caster,10,5,5);
            addToBot(new SacrificeMinionAction(caster,target));
            addToBot(new SummonMinionAction(caster,minion));
        }
    }
}
