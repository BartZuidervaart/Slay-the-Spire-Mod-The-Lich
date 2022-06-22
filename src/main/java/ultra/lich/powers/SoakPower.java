package ultra.lich.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.images.ImageLibrary;

public class SoakPower extends AbstractLichPower {

    public static final String DESCRIPTION =  "On death, it soaks #b%o remaining damage.";

    public static final String POWER_ID = "TheLich:SoakPower";

    public SoakPower(AbstractCreature owner, int amount){
        super();
        this.name = "Soak";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.SOAK_POWER_ICON),0,0,32,32);
        this.region128 = this.region48;
        this.canGoNegative = true;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(target == this.owner && power instanceof SoakPower){
            this.updateDescription();
        }
    }

    @Override
    public void updateDescription(){
        this.description = String.format(DESCRIPTION,this.amount);
    }
}
