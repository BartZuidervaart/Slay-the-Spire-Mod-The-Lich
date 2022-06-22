package ultra.lich.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ultra.lich.images.ImageLibrary;

public class CeremonialDaggerRelic extends AbstractLichRelic {

    public static final String ID = "TheLich:Ceremonial_dagger";
    public static final String DESCRIPTIONS = "After every 3 sacrifices. Gain 5 block.";

    public static final int TARGET_NUMBER = 3;
    public static final int BLOCK_AMOUNT = 5;

    public CeremonialDaggerRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.CEREMONIAL_KNIFE),
                RelicTier.COMMON, LandingSound.MAGICAL);
        this.counter = 0;
    }

    public AbstractRelic makeCopy() {
        return new CeremonialDaggerRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }

    @Override
    public void onSacrifice(AbstractMonster monster){
        super.onSacrifice(monster);
        this.counter ++;
        if(this.counter == TARGET_NUMBER){
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMOUNT));
        }
    }
}