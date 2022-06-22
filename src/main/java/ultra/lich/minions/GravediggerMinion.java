package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.player.LichClass;

public class GravediggerMinion extends AbstractLichMinion {
    private static String NAME = "Gravedigger";
    private static String ID = "TheLich:GravediggerMinion";

    public GravediggerMinion(LichClass caster, int hp, int attack, int defense) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Gravedigger.atlas","img/minions/Gravedigger.json",1.5f), "animtion0", caster,attack,defense);
    }

    @Override
    public void attack(AbstractMonster target, int damage){
        //attacks deal no damage
        this.applyPowerAsPartOfAttack(target);
    }

    @Override
    public void applyStartOfTurnPowers(){
        super.applyStartOfTurnPowers();
        this.addToBot(new DrawCardAction(this.getCaster(), this.getTotalAttack()));
    }
}
