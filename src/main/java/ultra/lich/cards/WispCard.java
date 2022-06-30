package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.OrbMinion;
import ultra.lich.powers.SummonerPower;

public class WispCard extends AbstractLichCard {

    public static final String ID = "TheLich:WispCard";
    public static final	String NAME = "Wisp";
    public static final	String DESCRIPTION = "Tribute 1. Summons an orb. Stats 1/1/3. Summoning sickness 1.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 1. Summons an orb. Stats 1/1/3. Summoning sickness 1. Gain [E] ." ;
    private static final int COST = 0;

    public WispCard() {
        super(ID, NAME, ImageLibrary.WISP, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new WispCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);

        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower)p.getPower(SummonerPower.POWER_ID);
            addToBot(new SacrificeMinionAction(caster,target));
        }

        addToBot(new SummonMinionAction(p,new OrbMinion(p,3,1,1,1,1)));

        if(upgraded){
            this.addToBot(new GainEnergyAction(1));
        }
    }
}