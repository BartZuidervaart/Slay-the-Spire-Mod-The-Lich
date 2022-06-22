package ultra.lich.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.images.ImageLibrary;

public class SummonSicknessPower extends AbstractPower {

    public static final Logger LOGGER = LogManager.getLogger(SummonSicknessPower.class.getName());

    public static final String DESCRIPTION =  "Target takes #b%o damage at the end of its turn.";

    public static final String POWER_ID = "TheLich:SummonSicknessPower";

    public boolean fatal = false;

    public SummonSicknessPower(AbstractCreature owner, int amount){
        super();
        this.name = "Summon Sickness";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = true;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.SUMMON_SICKNESS_POWER_ICON),0,0,32,32);
        this.region128 = this.region48;
    }

    @Override
    public void updateDescription(){
        this.description = String.format(DESCRIPTION,this.amount > 0 ? this.amount : 1 );
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(target == this.owner && power instanceof SummonSicknessPower){
            this.updateDescription();
        }
    }


    @Override
    public void atStartOfTurn(){
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if(!this.owner.isDead && this.owner.currentHealth - this.amount <= 0){
                this.fatal = true;
            }
            this.flashWithoutSound();
            this.addToBot(new LoseHPAction(this.owner, this.owner, this.amount, AbstractGameAction.AttackEffect.POISON));
        }

        super.atStartOfTurn();
    }

}
