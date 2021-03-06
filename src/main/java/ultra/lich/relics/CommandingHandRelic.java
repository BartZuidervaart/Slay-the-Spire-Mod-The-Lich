package ultra.lich.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.HighPriorityTarget;
import ultra.lich.powers.SummonerPower;

public class CommandingHandRelic extends AbstractLichRelic {

    public static final String ID = "TheLich:CommandingHandRelic";
    public static final String DESCRIPTIONS = "When you summon a zombie, it gains high priority.";

    public CommandingHandRelic() {
        super(ID, ImageMaster.loadImage(ImageLibrary.COMMANDING_HAND),
                RelicTier.UNCOMMON, LandingSound.FLAT);

    }

    public AbstractRelic makeCopy() {
        return new CommandingHandRelic();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS;
    }

    @Override
    public void addedMinion(AbstractFriendlyMonster minion){
        String name = minion.name;
        if(name.contains("Zombie") || name.contains("zombie")){
            if(AbstractDungeon.player.hasPower(SummonerPower.POWER_ID)){
                SummonerPower caster = (SummonerPower)AbstractDungeon.player.getPower(SummonerPower.POWER_ID);
                addToBot(new ApplyPowerAction(minion, caster.owner, new HighPriorityTarget(minion, caster)));
            }
        }
    };
}
