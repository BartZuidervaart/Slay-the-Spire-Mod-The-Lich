package ultra.lich.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.images.ImageLibrary;

public class SummonSicknessPower extends AbstractPower {

    public static final String DESCRIPTION =  "Target takes damage at the end of its turn.";

    public SummonSicknessPower(AbstractCreature owner, int amount){
        super();
        this.name = "Summon Sickness";
        this.ID = "SummonSicknessPower";
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = true;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.img = new Texture(ImageLibrary.SUMMON_SICKNESS_POWER_ICON);
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        this.flash();
        owner.damage(new DamageInfo(owner,amount, DamageInfo.DamageType.HP_LOSS));
        super.atEndOfTurn(isPlayer);
    }

}
