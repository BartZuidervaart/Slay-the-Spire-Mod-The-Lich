package ultra.lich.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ultra.lich.images.ImageLibrary;
import ultra.lich.player.LichClass;

public class StaffOfContaminationRelic extends AbstractLichRelic {

    public static final String ID = "TheLich:StaffOfContaminationRelic";
    public static final String DESCRIPTIONS = "When you gain poison from fleshrot. Your minions gain 1 poison as well. Poisoned minion attacks apply poison to their target.";

    public StaffOfContaminationRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.STAFF_OF_CONTAMINATION),
                RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public AbstractRelic makeCopy() {
        return new StaffOfContaminationRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }

    @Override
    public void obtainedFleshRotPoison(int amount){
        super.obtainedFleshRotPoison(amount);
        AbstractPlayer player = AbstractDungeon.player;
        if(player instanceof LichClass){
            ((LichClass) player).getMinions().monsters.forEach(monster -> {
                this.addToBot(new ApplyPowerAction(monster, player, new PoisonPower(monster, player, 1), 1, AbstractGameAction.AttackEffect.POISON));
            });
        }
    }
}