package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import ultra.lich.powers.SoakPower;
import ultra.lich.powers.SummonSicknessPower;

public class FlameSkull extends AbstractLichMinion{

    private static String NAME = "Flame Skull Minion";
    private static String ID = "FlameSkull";
    private AbstractMonster target;

    public FlameSkull(AbstractPlayerWithMinions caster) {
        super(NAME, ID, 10, new SpineAnimation("img/minions/flameskull.atlas","img/minions/flameskull.json",1.2f), "animtion0", caster);
        //addMoves();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SummonSicknessPower(this, 1),1)); //slowly dies
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SoakPower(this, 5),1));

    }

    @Override
    public void applyEndOfTurnTriggers() {
        //attack
        target = AbstractDungeon.getRandomMonster();
        DamageInfo info = new DamageInfo(this,1,DamageInfo.DamageType.NORMAL);
        info.applyPowers(this, target); // <--- This lets powers effect minions attacks
        AbstractDungeon.actionManager.addToBottom(new DamageAction(target, info));

        //defend caster
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this,this, 1));

        this.intent = AbstractMonster.Intent.ATTACK_DEFEND;

        super.applyEndOfTurnTriggers();
    }
}
