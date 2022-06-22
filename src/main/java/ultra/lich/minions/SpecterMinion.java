package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.powers.SummonerPower;

public class SpecterMinion extends AbstractLichMinion {
    private static String NAME = "Specter";
    private static String ID = "TheLich:SpecterMinion";

    int intendedMaxHealth;

    public SpecterMinion(SummonerPower caster, int hp, int attack, int defense) {
        super(NAME, ID, hp+defense, new SpineAnimation("img/minions/Specter.atlas","img/minions/Specter.json",1f), "animtion0", caster,attack,defense);
        this.intendedMaxHealth = hp;
    }

    @Override
    public void attack(AbstractMonster target, int damage){
        super.attack(target,damage);
        this.heal(damage);
    }

    @Override
    public int getTotalDefense(){
        int total = super.getTotalDefense();
        if(this.maxHealth < intendedMaxHealth + total){
            increaseMaxHp((intendedMaxHealth + total) - this.maxHealth,true);
        } else if (this.maxHealth > intendedMaxHealth + total){
            decreaseMaxHealth(this.maxHealth - (total + intendedMaxHealth));
        }
        return total;
    }

    @Override
    public void increaseMaxHp(int amount, boolean showEffect) {
        this.intendedMaxHealth += amount;
        super.increaseMaxHp(amount,showEffect);
    }

}
