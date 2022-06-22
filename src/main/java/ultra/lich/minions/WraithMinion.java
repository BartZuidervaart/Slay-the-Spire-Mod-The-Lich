package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SummonerPower;

public class WraithMinion extends AbstractLichMinion {
    private static String NAME = "Wraith";
    private static String ID = "TheLich:WraithMinion";

    public WraithMinion(SummonerPower caster, int hp, int attack, int defense) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Wraith.atlas","img/minions/Wraith.json",1f), "animtion0", caster,attack,defense);
    }

    @Override
    public void attack(AbstractMonster target, int damage){
        this.applyPowerAsPartOfAttack(target);
        if(target.hasPower(WeakPower.POWER_ID)){
            AbstractPower weakPower = target.getPower(WeakPower.POWER_ID);
            weakPower.amount = weakPower.amount - damage;
        } else {
            addToBot(new ApplyPowerAction(target, this, new WeakPower(target,damage,false)));
        }
        if(target.hasPower(StrengthPower.POWER_ID)){
            AbstractPower strengthPower = target.getPower(StrengthPower.POWER_ID);
            strengthPower.amount = strengthPower.amount - this.getTotalDefense();
        } else {
            addToBot(new ApplyPowerAction(target, this, new StrengthPower(target,-this.getTotalDefense())));
        }
    }

    @Override
    public void defend(AbstractMonster target){
        //don't defend
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new PutridPower(this)));
    }
}
