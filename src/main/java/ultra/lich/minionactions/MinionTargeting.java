package ultra.lich.minionactions;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.TargetingHandler;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;

public class MinionTargeting extends TargetingHandler<AbstractMonster> {

    @SpireEnum
    public static AbstractCard.CardTarget MINION;

    protected AbstractMonster hovered = null;

    public static AbstractMonster getTarget(AbstractCard card){
        return CustomTargeting.getCardTarget(card);
    }

    @Override
    public void updateHovered() {
        hovered = null;
        if(AbstractDungeon.player instanceof AbstractPlayerWithMinions){
            AbstractPlayerWithMinions summoner = (AbstractPlayerWithMinions)AbstractDungeon.player;
            if(summoner.hasMinions()){
                summoner.getMinions().monsters.forEach(monster -> {
                    if(monster.hb.hovered){
                        this.hovered = monster;
                    }
                });
            }
        }
    }

    @Override
    public AbstractMonster getHovered() {
        return hovered;
    }

    @Override
    public void clearHovered() {
        hovered = null;
    }

    @Override
    public boolean hasTarget() {
        return hovered != null;
    }


}
