package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.powers.*;

public class GhostMinion extends AbstractLichMinion {
    private static String NAME = "Ghost";
    private static String ID = "TheLich:GhostMinion";

    private int orbPower;

    public GhostMinion(AbstractCreature summoner, int hp, int attack, int defense, int orbPower) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Ghost.atlas","img/minions/Ghost.json",1f), "animtion0", summoner,attack,defense);
        this.orbPower = orbPower;
    }

    @Override
    public void attack(AbstractMonster target, int damage){
        addToBot(new AnimateSlowAttackAction(this));
        this.applyPowerAsPartOfAttack(target);
        addToBot(new ApplyPowerAction(target, this, new VulnerablePower(target, damage,false)));
        addToBot(new SummonMinionAction(this.getSummoner(), new OrbMinion(this.getSummoner(),this.getTotalDefense(),1,1,1,1)));
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this,this, new OrbPower(this,orbPower)));
    }

}