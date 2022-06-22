package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ultra.lich.player.LichClass;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SoakPower;
import ultra.lich.powers.SummonSicknessPower;

public class ClawMinion extends AbstractLichMinion {

    private static String NAME = "TheLich:Claw";
    private static String ID = "Claw";

    private int summonSickness;

    public ClawMinion(LichClass caster) {
        this(caster, 2, 3,0,1);
    }

    public ClawMinion(LichClass caster, int hp, int attack, int defense, int summonSickness) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Arm.atlas","img/minions/Arm.json",1f), "animtion0", caster,attack,defense);
        this.summonSickness = summonSickness;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();
        addToTop(new ApplyPowerAction(this, this, new SummonSicknessPower(this, summonSickness))); //slowly dies
    }
}
