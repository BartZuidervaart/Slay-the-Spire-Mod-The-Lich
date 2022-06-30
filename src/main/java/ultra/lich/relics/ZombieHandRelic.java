package ultra.lich.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.ZombieMinion;
import ultra.lich.powers.SummonerPower;

public class ZombieHandRelic extends AbstractLichRelic {

    public static final String ID = "TheLich:Zombie_hand";
    public static final String DESCRIPTIONS = "At the start of a battle. Summon a zombie.";

    public ZombieHandRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.ZOMBIE_HAND),
                RelicTier.COMMON, LandingSound.FLAT);
    }

    public AbstractRelic makeCopy() {
        return new ZombieHandRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }

    @Override
    public void atBattleStart(){
        super.atBattleStart();
        addToBot(new SummonMinionAction(AbstractDungeon.player,new ZombieMinion(AbstractDungeon.player)));
    }
}
