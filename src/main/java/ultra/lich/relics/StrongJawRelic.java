package ultra.lich.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ultra.lich.cards.status.CannibalizeCard;
import ultra.lich.images.ImageLibrary;

public class StrongJawRelic extends AbstractLichRelic {

    public static final String ID = "TheLich:StrongJawRelic";
    public static final String DESCRIPTIONS = "Adds a cannibalize card at the start of every turn.";

    public StrongJawRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.STRONG_JAW),
                RelicTier.COMMON, LandingSound.HEAVY);

    }

    public AbstractRelic makeCopy() {
        return new StrongJawRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }

    @Override
    public void atTurnStart(){
        super.atTurnStart();
        this.addToBot(new MakeTempCardInHandAction(new CannibalizeCard(),1));
    }
}