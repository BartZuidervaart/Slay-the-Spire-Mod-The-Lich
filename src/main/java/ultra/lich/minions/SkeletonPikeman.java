package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import ultra.lich.minionactions.LichMinionMove;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SummonerPower;

import java.util.ArrayList;

public class SkeletonPikeman extends AbstractControlableLichMinion{

    private static String NAME = "Skeleton Pikeman";
    private static String ID = "TheLich:SkeletonPikeman";

    private Runnable attackAction = () -> {
        AbstractMonster target = this.getTarget();
        this.applyPowerAsPartOfAttack(target);
        DamageInfo info = new DamageInfo(this, this.getTotalAttack(), DamageInfo.DamageType.NORMAL);
        info.applyPowers(this,target);
        addToBot(new DamageAction(target, info));
    };

    private Runnable vulnerableAction = () -> {
        AbstractMonster target = this.getTarget();
        addToBot(new ApplyPowerAction(target, this, new VulnerablePower(target,this.getTotalDefense(),false)));
    };

    public SkeletonPikeman(SummonerPower caster, int hp, int attack, int vulnerable) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/SkeletonPike.atlas","img/minions/SkeletonPike.json",1.2f), "animtion0", caster,attack,vulnerable);

        addMoves();
    }

    @Override
    public ArrayList<LichMinionMove> setMoves() {
        ArrayList<LichMinionMove> returnList = new ArrayList<>();
        returnList.add(new LichMinionMove("Attack",this, ImageMaster.loadImage(ImageLibrary.ATTACK_MINION_MOVE_ICON), "Deal damage", this.attackAction));
        returnList.add(new LichMinionMove("Apply Vulnerable",this, ImageMaster.loadImage(ImageLibrary.VULNERABLE_MINION_MOVE_ICON), "Deals its defense as vulnerable", this.vulnerableAction));
        return returnList;
    }

    @Override
    public void noTurnTaken() {
        this.attackAction.run();
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new PutridPower(this)));
    }

}
