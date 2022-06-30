package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.core.AbstractCreature;
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

    public FlameSkull(AbstractCreature summoner){
        this(summoner,3,3,1,3,-1,1);
    }

    public FlameSkull(AbstractCreature summoner, int hp, int attack, int defense, int soak, int explosive, int summonSickness) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/flameskull.atlas","img/minions/flameskull.json",1.2f), "animtion0", summoner,attack,defense);
        this.soak = soak;
        this.explosive = explosive;
        this.summonSickness = summonSickness;
        this.baseAttackEffect = AbstractGameAction.AttackEffect.FIRE;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new SummonSicknessPower(this, summonSickness),1)); //slowly dies
        addToBot(new ApplyPowerAction(this, this, new SoakPower(this, soak)));
        addToBot(new ApplyPowerAction(this, this, new ExplosiveDeathPower(this,explosive)));
    }
}
