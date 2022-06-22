package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import ultra.lich.player.LichClass;
import ultra.lich.powers.ExplosiveDeathPower;
import ultra.lich.powers.SoakPower;

public class BloatedZombieMinion extends AbstractLichMinion {
    private static String NAME = "Bloated Zombie";
    private static String ID = "TheLich:BloatedZombieMinion";

    private int soak;
    private int explosive;

    public BloatedZombieMinion(LichClass caster, int hp, int attack, int defense, int soak, int explosive) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/BloatedZombie.atlas","img/minions/BloatedZombie.json",1f), "animtion0", caster,attack,defense);
        this.soak = soak;
        this.explosive = explosive;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();
        addToBot(new ApplyPowerAction(this, this, new SoakPower(this, soak)));
        addToBot(new ApplyPowerAction(this,this,new ExplosiveDeathPower(this,explosive)));
    }

}