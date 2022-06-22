package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.minionactions.LichMinionMove;
import ultra.lich.images.ImageLibrary;
import ultra.lich.player.LichClass;
import ultra.lich.powers.PutridPower;

import java.util.ArrayList;

public class WightMinion extends AbstractControlableLichMinion{

    private static String NAME = "Wight";
    private static String ID = "TheLich:WightMinion";

    private boolean summoning = false;

    private int minionAttack;
    private int minionDefense;
    private int minionSoak;
    private int minionHealth;

    private Runnable attackAction = () -> {
        AbstractMonster target = this.getTarget();
        this.applyPowerAsPartOfAttack(target);
        DamageInfo info = new DamageInfo(this, this.getTotalAttack(), DamageInfo.DamageType.NORMAL);
        info.applyPowers(this,target);
        addToBot(new DamageAction(target, info));
    };

    private Runnable summonAction = () -> {
        addToBot(new SummonMinionAction(this.getCaster(),new ZombieMinion(this.getCaster(), this.minionHealth, this.minionAttack,this.minionDefense,this.minionSoak)));
    };

    public WightMinion(LichClass caster, int hp, int attack, int defense, int putrid, int minionAttack, int minionDefense, int minionHealth, int minionSoak) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Skeleton_sword_shield.atlas","img/minions/Skeleton_sword_shield.json",1.2f), "animtion0", caster,attack,defense);
        addMoves();
        this.minionAttack = minionAttack;
        this.minionDefense = minionDefense;
        this.minionSoak = minionSoak;
        this.minionHealth = minionHealth;
    }

    @Override
    public ArrayList<LichMinionMove> setMoves() {
        ArrayList<LichMinionMove> returnList = new ArrayList<>();
        returnList.add(new LichMinionMove("Attack",this, ImageMaster.loadImage(ImageLibrary.ATTACK_MINION_MOVE_ICON), "Deal damage", this.attackAction));
        returnList.add(new LichMinionMove("Summon", this, ImageMaster.loadImage(ImageLibrary.SUMMON_MINION_MOVE_ICON),"Summon zombie", this.summonAction));
        return returnList;
    }

    @Override
    public void noTurnTaken() {
        this.attackAction.run();
    }

    @Override
    public void takeTurn(){
        super.takeTurn();
        if(getTotalDefense() > 0){
            this.defend(this);
        }
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new PutridPower(this)));
    }

}