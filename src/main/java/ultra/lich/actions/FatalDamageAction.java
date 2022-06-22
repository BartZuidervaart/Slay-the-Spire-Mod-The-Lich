package ultra.lich.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.Iterator;
import java.util.UUID;

public class FatalDamageAction extends AbstractGameAction {

    private Runnable runnable;

    public FatalDamageAction(AbstractCreature target, Runnable runnable) {
        this.runnable = runnable;
        this.target = target;
    }

    public void update() {
        if (this.target != null){
            if((this.target.isDying || this.target.currentHealth <= 0 || this.target.isDead)){
                this.runnable.run();
            }
        }
        isDone = true;
    }
}
