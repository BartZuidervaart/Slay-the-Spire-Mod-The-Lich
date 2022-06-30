package ultra.lich.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.powers.SummonerPower;

public class SummonMinionAction extends AbstractGameAction {

    AbstractCreature summoner;
    AbstractLichMinion minion;

    public SummonMinionAction(AbstractCreature summoner, AbstractLichMinion minion) {
        this.summoner = summoner;
        this.minion = minion;
    }

    public void update() {
        if(this.summoner.hasPower(SummonerPower.POWER_ID)){
              SummonerPower caster = (SummonerPower) this.summoner.getPower(SummonerPower.POWER_ID);
              caster.addMinion(minion);
        } else {
            addToBot(new ApplyPowerAction(this.summoner,this.summoner,new SummonerPower(this.summoner,2))); //2 is the standard max for non summoner classes
            addToBot(new SummonMinionAction(summoner,minion)); //try it again but with the summoner power this time.
        }

        isDone = true;
    }
}
