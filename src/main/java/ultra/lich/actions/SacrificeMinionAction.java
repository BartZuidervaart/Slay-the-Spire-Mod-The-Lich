package ultra.lich.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.powers.SummonerPower;

public class SacrificeMinionAction extends AbstractGameAction {
    SummonerPower caster;
    AbstractMonster minion;

    public SacrificeMinionAction(SummonerPower caster, AbstractMonster minion) {
        this.caster = caster;
        this.minion = minion;
    }

    public void update() {
        caster.sacrifice(minion);
        caster.flushDeadMinions();
        isDone = true;
    }
}
