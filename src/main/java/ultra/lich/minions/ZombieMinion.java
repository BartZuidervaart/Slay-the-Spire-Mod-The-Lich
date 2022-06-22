package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ultra.lich.player.LichClass;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SoakPower;

public class ZombieMinion extends AbstractLichMinion {
    private static String NAME = "Zombie";
    private static String ID = "TheLich:ZombieMinion";

    private int soak;

    public ZombieMinion(LichClass caster){
        this(caster, 5, 5);
    }

    public ZombieMinion(LichClass caster, int hp, int soak) {
        this(caster,hp,0,0,soak);
    }

    public ZombieMinion(LichClass caster, int hp, int attack, int defense, int soak) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Zombie.atlas","img/minions/Zombie.json",1f), "animtion0", caster,attack,defense);
        this.soak = soak;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new SoakPower(this, soak))); //slowly dies
    }
}
