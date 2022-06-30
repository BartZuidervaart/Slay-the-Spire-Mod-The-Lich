package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import ultra.lich.powers.SummonSicknessPower;
import ultra.lich.powers.SummonerPower;

public class ClawMinion extends AbstractLichMinion {

    private static String NAME = "TheLich:Claw";
    private static String ID = "Claw";

    private int summonSickness;

    public ClawMinion(AbstractCreature summoner) {
        this(summoner, 2, 3,0,1);
    }

    public ClawMinion(AbstractCreature summoner, int hp, int attack, int defense, int summonSickness) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Arm.atlas","img/minions/Arm.json",1f), "animtion0", summoner,attack,defense);
        this.summonSickness = summonSickness;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();
        addToTop(new ApplyPowerAction(this, this, new SummonSicknessPower(this, summonSickness))); //slowly dies
    }
}
