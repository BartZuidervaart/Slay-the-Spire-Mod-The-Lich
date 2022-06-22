package ultra.lich.powers;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.images.ImageLibrary;

public class AttackPower extends AbstractLichPower {

    public String DESCRIPTION = "Inflicts #b%o damage at the end of its turn.";

    public static final String POWER_ID = "TheLich:AttackPower";

    private int baseAmount;

    private int addAmount;

    public AttackPower(AbstractCreature owner, int baseAmount,int addAmount) {
        super();
        this.name = "Attack";
        this.ID = POWER_ID;
        this.owner = owner;
        this.baseAmount = baseAmount;
        this.addAmount = addAmount;
        this.amount = baseAmount+addAmount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.ATTACK_ICON),0,0,32,32);
        this.region128 = this.region48;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(target == this.owner && power instanceof AttackPower){
            this.updateDescription();
        }
    }

    public void setBaseAmount(int amount){
        this.baseAmount = amount;
        this.amount = this.baseAmount + this.addAmount;
    }

    public void setAddAmount(int amount){
        this.addAmount = amount;
        this.amount = this.baseAmount + this.addAmount;
        this.owner.powers.forEach(power -> power.onApplyPower(this,this.owner,this.owner));
    }

    public int getBaseAmount(){
        return this.baseAmount;
    }

    public int getAddAmount(){
        return this.addAmount;
    }

    @Override
    public void updateDescription(){
        this.description = String.format(DESCRIPTION,this.amount);
    }
}