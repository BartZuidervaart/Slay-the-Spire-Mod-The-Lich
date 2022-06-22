package ultra.lich.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.powers.SummonerPower;

public class SummonMinionAction extends AbstractGameAction {

    SummonerPower caster;
    AbstractLichMinion minion;

    public SummonMinionAction(SummonerPower caster, AbstractLichMinion minion) {
        this.caster = caster;
        this.minion = minion;
    }

    public void update() {
        this.caster.addMinion(minion);
        isDone = true;
    }
}
