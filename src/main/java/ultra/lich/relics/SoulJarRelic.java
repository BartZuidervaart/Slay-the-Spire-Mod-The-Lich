package ultra.lich.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ultra.lich.images.ImageLibrary;

public class SoulJarRelic extends AbstractLichRelic {

    public static final String ID = "TheLich:Soul_Jar_Relic";
    public static final String DESCRIPTIONS = "Whenever you would die, heal to full health instead.(use once)";

    public SoulJarRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.SOUL_JAR),
                AbstractRelic.RelicTier.STARTER, LandingSound.CLINK);
    }

    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
        }

    }

    @Override
    public int onLoseHpLast(int damageAmount) {
        if(this.counter > -2) {
            if (AbstractDungeon.player.currentHealth - damageAmount <= 0) {
                damageAmount = Math.abs(AbstractDungeon.player.currentHealth - damageAmount); //remaining damage
                this.flash();
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                int healAmt = AbstractDungeon.player.maxHealth;
                if (healAmt < 1) {
                    healAmt = 1;
                }

                AbstractDungeon.player.heal(healAmt, true);
                this.setCounter(-2);
            }
        }
        return damageAmount;
    }

    public AbstractRelic makeCopy() {
        return new SoulJarRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }
}
