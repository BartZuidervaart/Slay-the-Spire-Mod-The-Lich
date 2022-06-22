package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import ultra.lich.powers.ExplosiveDeathPower;
import ultra.lich.powers.SoakPower;
import ultra.lich.powers.SummonSicknessPower;
import ultra.lich.powers.SummonerPower;

public class FlameSkull extends AbstractLichMinion{

    private static String NAME = "Flame Skull";
    private static String ID = "TheLich:FlameSkull";

    private int soak;
    private int explosive;
    private int summonSickness;

    public FlameSkull(SummonerPower caster){
        this(caster,3,3,1,3,-1,1);
    }

    public FlameSkull(SummonerPower caster, int hp, int attack, int defense, int soak, int explosive, int summonSickness) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/flameskull.atlas","img/minions/flameskull.json",1.2f), "animtion0", caster,attack,defense);
        this.soak = soak;
        this.explosive = explosive;
        this.summonSickness = summonSickness;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new SummonSicknessPower(this, summonSickness),1)); //slowly dies
        addToBot(new ApplyPowerAction(this, this, new SoakPower(this, soak)));
        addToBot(new ApplyPowerAction(this, this, new ExplosiveDeathPower(this,explosive)));
    }
}
