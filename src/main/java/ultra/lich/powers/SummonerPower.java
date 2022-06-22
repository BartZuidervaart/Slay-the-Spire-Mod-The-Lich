package ultra.lich.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.player.LichClass;

public class SummonerPower extends AbstractLichPower {

    public static final Logger LOGGER = LogManager.getLogger(SummonerPower.class.getName());

    public static final String DESCRIPTION =  "Can have #b4 #yMinions. Summoning more #yMinions will #ySacrifice the first #yMinions.";

    public static final String POWER_ID = "TheLich:SummonerPower";

    public SummonerPower(AbstractCreature owner){
        super();
        this.name = "Summoner";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.loadRegion("book");
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount){
        LOGGER.info(this.owner + " took "+damageAmount+" damage.");
        if(this.owner instanceof LichClass){
            LichClass summoner = (LichClass) this.owner;
            AbstractMonster target = summoner.getPriorityTarget();
            if(target != null && damageAmount > 0){
                LOGGER.info(""+this.owner + damageAmount + " damage goes to " + target + " instead");
                target.damage(info);
                return 0;
            } else {
                return damageAmount;
            }
        } else {
            return damageAmount;
        }
    }


}
