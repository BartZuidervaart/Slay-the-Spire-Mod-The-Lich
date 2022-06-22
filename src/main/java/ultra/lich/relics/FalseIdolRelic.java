package ultra.lich.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.images.ImageLibrary;

public class FalseIdolRelic extends AbstractLichRelic {

    public static final String ID = "TheLich:False_idol";
    public static final String DESCRIPTIONS = "At the start of your turn gain [E] . When you sacrifice a minion draw one fleshrot.";

    public FalseIdolRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.FALSE_IDOL),
                RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public AbstractRelic makeCopy() {
        return new FalseIdolRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }

    @Override
    public void onSacrifice(AbstractMonster monster){
        super.onSacrifice(monster);
        this.flash();
        addToBot(new MakeTempCardInHandAction(new FleshRot(),1));
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
}