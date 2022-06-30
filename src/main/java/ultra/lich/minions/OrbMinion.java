package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import ultra.lich.powers.OrbPower;
import ultra.lich.powers.SummonSicknessPower;
import ultra.lich.powers.SummonerPower;

public class OrbMinion extends AbstractLichMinion {
    private static String NAME = "Orb";
    private static String ID = "TheLich:OrbMinion";

    private int orbPower;
    private int summonSickness;

    public OrbMinion(AbstractCreature summoner, int hp, int attack, int defense, int orbPower, int summonSickness) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Orb.atlas","img/minions/Orb.json",1f), "animtion0", summoner,attack,defense);
        this.orbPower = orbPower;
        this.summonSickness = summonSickness;
        this.baseAttackEffect = AbstractGameAction.AttackEffect.LIGHTNING;
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this,this, new OrbPower(this,orbPower)));
        addToBot(new ApplyPowerAction(this,this, new SummonSicknessPower(this,summonSickness)));
    }
}