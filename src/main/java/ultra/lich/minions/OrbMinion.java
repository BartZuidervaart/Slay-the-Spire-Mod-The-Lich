package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import ultra.lich.player.LichClass;
import ultra.lich.powers.OrbPower;
import ultra.lich.powers.SummonSicknessPower;

public class OrbMinion extends AbstractLichMinion {
    private static String NAME = "Orb";
    private static String ID = "TheLich:OrbMinion";

    private int orbPower;
    private int summonSickness;

    public OrbMinion(LichClass caster, int hp, int attack, int defense, int orbPower, int summonSickness) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Orb.atlas","img/minions/Orb.json",1f), "animtion0", caster,attack,defense);
        this.orbPower = orbPower;
        this.summonSickness = summonSickness;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this,this, new OrbPower(this,orbPower)));
        addToBot(new ApplyPowerAction(this,this, new SummonSicknessPower(this,summonSickness)));
    }
}