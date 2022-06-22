package ultra.lich.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.images.ImageLibrary;

public class PutridPower extends AbstractLichPower {

    public static final String DESCRIPTION =  "At the start of your turn, adds #b%o fleshrot to your hand.";

    public static final String POWER_ID = "TheLich:PutridPower";

    public PutridPower(AbstractCreature owner){
        this(owner, -1);
    }

    public PutridPower(AbstractCreature owner, int amount){
        super();
        this.name = "Putrid";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.loadRegion("fumes");

    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(target == this.owner && power instanceof PutridPower){
            this.updateDescription();
        }
    }

    @Override
    public void updateDescription(){
        this.description = String.format(DESCRIPTION,this.amount > 0 ? this.amount : 1 );
    }

    @Override
    public void atStartOfTurn(){
        super.atStartOfTurn();
        int amount = 1;
        if(this.amount > 0){
            amount = this.amount;
        }
        this.addToBot(new MakeTempCardInHandAction(new FleshRot(),amount));
    }
}