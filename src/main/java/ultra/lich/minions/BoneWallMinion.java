package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SoakPower;
import ultra.lich.powers.SummonerPower;

public class BoneWallMinion extends AbstractLichMinion {
    private static String NAME = "Bone wall";
    private static String ID = "TheLich:BoneWallMinion";

    private int soak;
    private int putrid;

    public BoneWallMinion(AbstractCreature summoner, int hp, int attack, int defense, int soak, int putrid) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/BoneWall.atlas","img/minions/BoneWall.json",1f), "animtion0", summoner,attack,defense);
        this.soak = soak;
        this.putrid = putrid;
    }

    @Override
    public void attack(AbstractMonster target, int damage){
        addToBot(new AnimateShakeAction(this, 1.0f,0.1f));
        if(this.hasPower(ThornsPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(this, this, ThornsPower.POWER_ID));
        }
        addToBot(new ApplyPowerAction(this, this,new ThornsPower(this,this.getTotalAttack()), this.getTotalAttack()));
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();
        addToBot(new ApplyPowerAction(this, this, new SoakPower(this, soak)));
        addToBot(new ApplyPowerAction(this, this, new PutridPower(this, putrid)));
    }
}
