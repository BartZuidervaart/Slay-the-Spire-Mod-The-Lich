package ultra.lich.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinionPower extends AbstractLichPower {

    public String DESCRIPTION =  "Damage is redirected towards a random #yMinion. Excess damage carries on.";

    public SummonerPower summoner;

    public static final Logger LOGGER = LogManager.getLogger(MinionPower.class.getName());

    public static final String POWER_ID = "TheLich:MinionPower";

    public MinionPower(AbstractCreature owner, SummonerPower summoner){
        super();
        this.name = "Minion";
        this.ID = POWER_ID;
        this.owner = owner;
        this.summoner = summoner;
        this.amount = -1;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.loadRegion("minion");
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount){
        LOGGER.info(this.owner + " took "+damageAmount+" damage.");
        float healthRemaining = (owner.currentBlock + owner.currentHealth) - damageAmount;
        //deal remaining damage to other minions or to caster.
        if(healthRemaining < 0){
            float tremble = Math.abs(healthRemaining);

            //If the creature has soak. Soak some of the remaining damage.
            //This soak can become negative as well.
            if(owner.hasPower(SoakPower.POWER_ID)){
                tremble -= owner.getPower(SoakPower.POWER_ID).amount;
            }

            this.owner.isDead = true; //kill this minion, else the summoner will redirect its damage to this dead minion again.

            if(tremble > 0){
                AbstractMonster target = summoner.getPriorityTarget();
                LOGGER.info(this.owner + " got killed with "+tremble+" damage remaining. Sending it to "+target);
                if(target != null){
                    target.damage(new DamageInfo(info.owner,(int)tremble, DamageInfo.DamageType.NORMAL));
                } else {
                    summoner.owner.damage(new DamageInfo(summoner.owner,(int)tremble,DamageInfo.DamageType.NORMAL));
                }
            }
        }
        return damageAmount;
    }

}
