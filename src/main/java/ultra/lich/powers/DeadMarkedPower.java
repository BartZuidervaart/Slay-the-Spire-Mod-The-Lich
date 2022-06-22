package ultra.lich.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.player.LichClass;

public class DeadMarkedPower extends AbstractLichPower {

    public String DESCRIPTION =  "A priority target for #yMinions.";

    public LichClass summoner;

    public static final String POWER_ID = "TheLich:DeadMarkedPower";

    public static final Logger LOGGER = LogManager.getLogger(MinionPower.class.getName());

    public DeadMarkedPower(AbstractCreature owner, LichClass summoner){
        super();
        this.name = "Dead Marked";
        this.ID = POWER_ID;
        this.owner = owner;
        this.summoner = summoner;
        this.amount = -1;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.loadRegion("lockon");
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }
}