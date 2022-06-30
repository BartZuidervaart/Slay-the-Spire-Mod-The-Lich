package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import ultra.lich.minionactions.LichMinionMove;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SummonerPower;

import java.util.ArrayList;

public class SkeletonSentinel extends AbstractControlableLichMinion{

    private static String NAME = "Skeleton Sentinel";
    private static String ID = "TheLich:SkeletonSentinel";

    private Runnable defenseAction = () -> {
        addToBot(new AnimateShakeAction(this, 1.0f,0.1f));
        addToBot(new GainBlockAction(this,this, this.getTotalDefense()));
        this.didAction = true;
    };

    private Runnable frailAction = () -> {
        addToBot(new AnimateSlowAttackAction(this));
        AbstractMonster target = this.getTarget();
        this.applyPowerAsPartOfAttack(target);
        addToBot(new ApplyPowerAction(target, this, new FrailPower(target,this.getTotalAttack(),false)));
        this.didAction = true;
    };

    public SkeletonSentinel(AbstractCreature summoner, int hp, int defense, int frail) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/SkeletonSentinel.atlas","img/minions/SkeletonSentinel.json",1.2f), "animtion0", summoner,frail,defense);
        addMoves();
    }

    @Override
    public ArrayList<LichMinionMove> setMoves() {
        ArrayList<LichMinionMove> returnList = new ArrayList<>();
        returnList.add(new LichMinionMove("Defend",this, ImageMaster.loadImage(ImageLibrary.DEFEND_MINION_MOVE_ICON), "Gain defense", this.defenseAction));
        returnList.add(new LichMinionMove("Apply Frail",this, ImageMaster.loadImage(ImageLibrary.FRAIL_MINION_MOVE_ICON), "Deals its attack as frail", this.frailAction));
        return returnList;
    }

    @Override
    public void noTurnTaken() {
        this.defenseAction.run();
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new PutridPower(this)));
    }

}
