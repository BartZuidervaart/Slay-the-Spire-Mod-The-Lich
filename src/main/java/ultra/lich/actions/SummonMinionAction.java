package ultra.lich.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.player.LichClass;

public class SummonMinionAction extends AbstractGameAction {

    LichClass caster;
    AbstractLichMinion minion;

    public SummonMinionAction(LichClass caster, AbstractLichMinion minion) {
        this.caster = caster;
        this.minion = minion;
    }

    public void update() {
        this.caster.addMinion(minion);
        isDone = true;
    }
}
