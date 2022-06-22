package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import ultra.lich.player.LichClass;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SoakPower;

public class BoneWallMinion extends AbstractLichMinion {
    private static String NAME = "Bone wall";
    private static String ID = "TheLich:BoneWallMinion";

    private int soak;
    private int putrid;

    public BoneWallMinion(LichClass caster, int hp, int attack, int defense, int soak, int putrid) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/BoneWall.atlas","img/minions/BoneWall.json",1f), "animtion0", caster,attack,defense);
        this.soak = soak;
        this.putrid = putrid;
    }

    @Override
    public void attack(AbstractMonster target, int damage){
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
