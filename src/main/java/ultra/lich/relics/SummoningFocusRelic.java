package ultra.lich.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.unique.SetupAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SummonSicknessPower;

public class SummoningFocusRelic extends AbstractLichRelic {

    public static final Logger LOGGER = LogManager.getLogger(SummoningFocusRelic.class.getName());

    public static final String ID = "TheLich:SummoningFocusRelic";
    public static final String DESCRIPTIONS = "When a summoned minion starts with summoning sickness, it starts with poison instead. Poisoned minions apply poison during their attacks.";

    public SummoningFocusRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.SUMMONING_FOCUS),
                RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public AbstractRelic makeCopy() {
        return new SummoningFocusRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }

    @Override
    public void addedMinion(AbstractFriendlyMonster minion){
        super.addedMinion(minion);

        addToBot(new FindAndReplacePower(minion));

    }
}

//TODO: looks like an odd way to do this. Isn't there an runnable action or something similar?
class FindAndReplacePower extends AbstractGameAction {

    private AbstractFriendlyMonster minion;

    public FindAndReplacePower(AbstractFriendlyMonster minion){
        this.minion = minion;
    }

    @Override
    public void update() {
        if(minion.hasPower(SummonSicknessPower.POWER_ID)){
            int amount = minion.getPower(SummonSicknessPower.POWER_ID).amount;
            addToBot(new RemoveSpecificPowerAction(minion, AbstractDungeon.player, SummonSicknessPower.POWER_ID));
            addToBot(new ApplyPowerAction(minion, AbstractDungeon.player, new PoisonPower(minion,AbstractDungeon.player,amount)));
        }
        this.isDone = true;
    }
}