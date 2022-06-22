package ultra.lich.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.images.ImageLibrary;

public class LowPriorityTarget extends AbstractLichPower {

    public String DESCRIPTION =  "When damage is redirected, this receives damage last.";

    public SummonerPower summoner;

    public static final Logger LOGGER = LogManager.getLogger(MinionPower.class.getName());

    public static final String POWER_ID = "TheLich:LowPriorityPower";

    public LowPriorityTarget(AbstractCreature owner, SummonerPower summoner){
        super();
        this.name = "Low Priority";
        this.ID = POWER_ID;
        this.owner = owner;
        this.summoner = summoner;
        this.amount = -1;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.LOW_PRIORITY_ICON),0,0,32,32);
        this.region128 = this.region48;
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }

}
