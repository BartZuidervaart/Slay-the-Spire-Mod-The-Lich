package ultra.lich.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.player.LichClass;

public class SacrificeMinionAction extends AbstractGameAction {
    LichClass caster;
    AbstractMonster minion;

    public SacrificeMinionAction(LichClass caster, AbstractMonster minion) {
        this.caster = caster;
        this.minion = minion;
    }

    public void update() {
        caster.sacrifice(minion);
        caster.flushDeadMinions();
        isDone = true;
    }
}
