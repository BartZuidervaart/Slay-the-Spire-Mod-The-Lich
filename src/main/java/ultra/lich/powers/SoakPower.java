package ultra.lich.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.images.ImageLibrary;

public class SoakPower extends AbstractPower {

    public static final String DESCRIPTION =  "When this creature dies, it soaks some of the remaining damage.";

    public SoakPower(AbstractCreature owner, int amount){
        super();
        this.name = "Soak";
        this.ID = "SoakPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.img = new Texture(ImageLibrary.NEGATIVE_SHIELD_POWER_ICON);
        this.canGoNegative = true;
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }
}
