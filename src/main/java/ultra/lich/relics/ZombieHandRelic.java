package ultra.lich.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.ZombieMinion;
import ultra.lich.player.LichClass;

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
        if(AbstractDungeon.player instanceof LichClass){
            LichClass summoner = (LichClass)AbstractDungeon.player;
            summoner.addMinion(new ZombieMinion(summoner));
        }
    }
}
