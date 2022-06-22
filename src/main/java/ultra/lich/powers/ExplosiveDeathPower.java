package ultra.lich.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.actions.RunnableGameAction;
import ultra.lich.minions.AbstractLichMinion;

public class ExplosiveDeathPower extends AbstractLichPower {

    public static final Logger LOGGER = LogManager.getLogger(ExplosiveDeathPower.class.getName());

    public static final String DESCRIPTION =  "At death, dealing 3 times its #yAttack as #b%o damage to all enemies.";
    public static final String DESCRIPTION_WITH_AMOUNT =  "At death, dealing 3 times its #yAttack plus this power amount as #b%o damage to all enemies.";

    public static final String POWER_ID = "TheLich:ExplosiveDeathPower";

    boolean used = false;

    public ExplosiveDeathPower(AbstractLichMinion owner){
        this(owner,-1);
    }

    public ExplosiveDeathPower(AbstractLichMinion owner, int amount){
        this.name = "Explosive death";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.loadRegion("cExplosion");
        addToBot(new RunnableGameAction(() -> {
            this.updateDescription();
        }));
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(target == this.owner && power instanceof AttackPower){
            this.updateDescription();
        }
    }

    @Override
    public void onDeath(){
        super.onDeath();
        if (!used) {
            if (this.owner instanceof AbstractLichMinion) {
                AbstractLichMinion minion = (AbstractLichMinion) this.owner;
                int attack = 0;
                if (this.amount > 0) {
                    attack += this.amount;
                }
                if (minion.hasPower(AttackPower.POWER_ID)) {
                    attack += minion.getPower(AttackPower.POWER_ID).amount * 3;
                }
                addToBot(new DamageAllEnemiesAction(minion.getCaster(), attack, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
                this.used = true;
            }
        }
    }

    @Override
    public void updateDescription(){
        int damageAmount = 0;
        if(this.owner.hasPower(AttackPower.POWER_ID)){
            damageAmount += this.owner.getPower(AttackPower.POWER_ID).amount;
        }
        if(this.amount > 0){
            damageAmount += this.amount;
        }
        if(amount > 0){
            this.description = String.format(DESCRIPTION_WITH_AMOUNT,damageAmount);
        } else {
            this.description = String.format(DESCRIPTION,damageAmount);
        }
    }
}
