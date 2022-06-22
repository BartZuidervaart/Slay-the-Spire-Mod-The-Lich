package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.cards.status.CannibalizeCard;
import ultra.lich.minionactions.LichMinionMove;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.PutridPower;
import ultra.lich.powers.SummonerPower;

import java.util.ArrayList;

public class BoneHorrorMinion extends AbstractControlableLichMinion{

    private static String NAME = "Bone horror";
    private static String ID = "TheLich:BoneHorrorMinion";

    private int putrid;

    private Runnable attackAction = () -> {
        AbstractMonster target = this.getTarget();
        this.applyPowerAsPartOfAttack(target);
        DamageInfo info = new DamageInfo(this, this.getTotalAttack(), DamageInfo.DamageType.NORMAL);
        info.applyPowers(this,target);
        addToBot(new DamageAction(target, info));
    };

    private Runnable defenseAction = () -> {
        addToBot(new GainBlockAction(this,this, this.getTotalDefense()));
    };

    public BoneHorrorMinion(SummonerPower caster, int hp, int attack, int defense, int putrid) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Skeleton_sword_shield.atlas","img/minions/Skeleton_sword_shield.json",1.2f), "animtion0", caster,attack,defense);
        addMoves();
        this.putrid = putrid;
    }

    @Override
    public ArrayList<LichMinionMove> setMoves() {
        ArrayList<LichMinionMove> returnList = new ArrayList<>();
        returnList.add(new LichMinionMove("Defend", this, ImageMaster.loadImage(ImageLibrary.DEFEND_MINION_MOVE_ICON),"Gain block", this.defenseAction));
        returnList.add(new LichMinionMove("Attack",this, ImageMaster.loadImage(ImageLibrary.ATTACK_MINION_MOVE_ICON), "Deal damage", this.attackAction));
        return returnList;
    }

    @Override
    public void noTurnTaken() {
        this.defenseAction.run();
    }

    @Override
    public void applyStartOfTurnPowers(){
        this.addToBot(new MakeTempCardInHandAction(new CannibalizeCard(),1));
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();
        addToBot(new ApplyPowerAction(this, this, new PutridPower(this, putrid),1));
    }
}
