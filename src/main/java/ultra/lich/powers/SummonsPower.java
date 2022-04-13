package ultra.lich.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.images.ImageLibrary;

public class SummonsPower extends AbstractPower {

    public static final String DESCRIPTION =  "Has minions. Damage that would normally hit the summoner is passed on to minions instead. If the damage dealt to a minion is greater than its remaining health, the remaining damage will be passed on to another minion or eventually the summoner.";

    public SummonsPower(AbstractCreature owner, int amount){
        super();
        this.name = "Summons";
        this.ID = "SummonsPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.img = new Texture(ImageLibrary.DEATH_KNIGHT_POWER_ICON);
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }


}
