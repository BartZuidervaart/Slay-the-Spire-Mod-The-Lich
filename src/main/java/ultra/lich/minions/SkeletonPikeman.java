package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import ultra.lich.minionactions.LichMinionMove;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SummonerPower;

import java.util.ArrayList;

public class SkeletonPikeman extends AbstractControlableLichMinion{

    private static String NAME = "Skeleton Pikeman";
    private static String ID = "TheLich:SkeletonPikeman";

    private Runnable attackAction = () -> {
        addToBot(new AnimateSlowAttackAction(this));
        AbstractMonster target = this.getTarget();
        this.applyPowerAsPartOfAttack(target);
        DamageInfo info = new DamageInfo(this, this.getTotalAttack(), DamageInfo.DamageType.NORMAL);
        info.applyPowers(this,target);
        addToBot(new DamageAction(target, info, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.didAction = true;
    };

    private Runnable vulnerableAction = () -> {
        addToBot(new AnimateSlowAttackAction(this));
        AbstractMonster target = this.getTarget();
        addToBot(new ApplyPowerAction(target, this, new VulnerablePower(target,this.getTotalDefense(),false)));
        this.didAction = true;
    };

    public SkeletonPikeman(AbstractCreature summoner, int hp, int attack, int vulnerable) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/SkeletonPike.atlas","img/minions/SkeletonPike.json",1.2f), "animtion0", summoner,attack,vulnerable);

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
