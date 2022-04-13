package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.monsters.MinionMove;

public class TestMinion extends AbstractLichMinion {

    private static String NAME = "Testing Minion";
    private static String ID = "TestingMinion";
    private AbstractMonster target;

    public TestMinion(AbstractPlayerWithMinions caster) {
        super(NAME, ID, 20, new SpineAnimation("img/Lich.atlas","img/Lich.json",5f), "animtion0", caster);
        //addMoves();
    }

    private void addMoves(){
        this.moves.addMove(new MinionMove("Attack", this, new Texture("img/512/card_template_orb.png"), "Deal 5 damage", () -> {
            target = AbstractDungeon.getRandomMonster();
            DamageInfo info = new DamageInfo(this,5,DamageInfo.DamageType.NORMAL);
            info.applyPowers(this, target); // <--- This lets powers effect minions attacks
            AbstractDungeon.actionManager.addToBottom(new DamageAction(target, info));
        }));
        this.moves.addMove(new MinionMove("Defend", this, new Texture("img/512/card_template_orb.png"),"Gain 5 block", () -> {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this,this, 5));
        }));
    }

    public void endTurnActions() {
        float random_roll = (float)Math.random();
        if(random_roll < 0.5f){
            //attack
            target = AbstractDungeon.getRandomMonster();
            DamageInfo info = new DamageInfo(this,5,DamageInfo.DamageType.NORMAL);
            info.applyPowers(this, target); // <--- This lets powers effect minions attacks
            AbstractDungeon.actionManager.addToBottom(new DamageAction(target, info));
        } else {
            //defend caster
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction((AbstractCreature)getCaster(),this, 5));
        }
    }
}
