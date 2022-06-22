package ultra.lich.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import ultra.lich.minions.AbstractLichMinion;

public class OrbPower extends AbstractLichPower {

    public static final String DESCRIPTION =  "On death, gain #b%o [E] next turn.";

    public static final String POWER_ID = "TheLich:OrbPower";

    public OrbPower(AbstractCreature owner, int amount){
        super();
        this.name = "Orb";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.loadRegion("phantasmal");
    }

    @Override
    public void updateDescription(){
        this.description = String.format(DESCRIPTION,this.amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(target == this.owner && power instanceof OrbPower){
            this.updateDescription();
        }
    }

    @Override
    public void onDeath(){
        if(this.owner instanceof AbstractLichMinion){
            AbstractLichMinion minion = (AbstractLichMinion)this.owner;
            this.addToBot(new ApplyPowerAction(minion.getCaster(), minion, new EnergizedPower(minion.getCaster(), this.amount), 1));
        }
    }
}
