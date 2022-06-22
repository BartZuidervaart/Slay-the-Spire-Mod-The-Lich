package ultra.lich.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.images.ImageLibrary;

public class LastRemorsePower extends AbstractLichPower {

    public static final String DESCRIPTION =  "If a #yMinion is #ySacrificed, dealing #b%o damage to all enemies.";

    public static final String POWER_ID = "TheLich:LastRemorsePower";

    public LastRemorsePower(AbstractCreature owner, int amount){
        this.name = "Last Remorse";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.EXPLOSION_ICON_32),0,0,32,32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.EXPLOSION_ICON),0,0,128,128);
    }

    @Override
    public void updateDescription(){
        this.description = String.format(DESCRIPTION,this.amount);
    }

    @Override
    public void onSacrifice(AbstractMonster monster){
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.amount, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
    };
}
