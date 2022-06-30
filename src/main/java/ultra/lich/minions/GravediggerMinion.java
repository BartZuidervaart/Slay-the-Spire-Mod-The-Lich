package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.powers.SummonerPower;

public class GravediggerMinion extends AbstractLichMinion {
    private static String NAME = "Gravedigger";
    private static String ID = "TheLich:GravediggerMinion";

    public GravediggerMinion(AbstractCreature summoner, int hp, int attack, int defense) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Gravedigger.atlas","img/minions/Gravedigger.json",1.5f), "animtion0", summoner,attack,defense);
    }

    @Override
    public void attack(AbstractMonster target, int damage){
        //attacks deal no damage
        addToBot(new AnimateShakeAction(this, 1.0f,0.1f));
        this.applyPowerAsPartOfAttack(target);
    }

    @Override
    public void applyStartOfTurnPowers(){
        super.applyStartOfTurnPowers();
        this.addToBot(new DrawCardAction(this.getSummoner(), this.getTotalAttack()));
    }
}
