package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import ultra.lich.powers.SoakPower;
import ultra.lich.powers.SummonerPower;

public class ZombieMinion extends AbstractLichMinion {
    private static String NAME = "Zombie";
    private static String ID = "TheLich:ZombieMinion";

    private int soak;

    public ZombieMinion(AbstractCreature summoner){
        this(summoner, 5, 5);
    }

    public ZombieMinion(AbstractCreature summoner, int hp, int soak) {
        this(summoner,hp,0,0,soak);
    }

    public ZombieMinion(AbstractCreature summoner, int hp, int attack, int defense, int soak) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Zombie.atlas","img/minions/Zombie.json",1f), "animtion0", summoner,attack,defense);
        this.soak = soak;
        this.baseAttackEffect = AbstractGameAction.AttackEffect.BLUNT_LIGHT;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new SoakPower(this, soak))); //slowly dies
    }
}
