package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import ultra.lich.images.ImageLibrary;
import ultra.lich.player.LichClass;
import ultra.lich.powers.DeadMarkedPower;

public class LegionCard extends AbstractLichCard {

    public static final String ID = "TheLich:LegionCard";
    public static final	String NAME = "Legion";
    public static final	String DESCRIPTION = "Deal 5 damage for every minion you have. Apply Dead Marked.";
    public static final String UPGRADE_DESCRIPTION = "Deal 7 damage for every minion you have. Apply Dead Marked." ;
    private static final int COST = 1;

    public LegionCard() {
        super(ID, NAME, ImageLibrary.LEGION, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
    }

    public AbstractCard makeCopy() {
        return new LegionCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if(p instanceof LichClass){
            LichClass player = (LichClass) p;

            int damage = 5;
            if(this.upgraded){
                damage = 7;
            }
            damage = damage * player.getMinions().monsters.size();
            addToBot(new DamageAction(abstractMonster,
                    new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            addToBot(new ApplyPowerAction(abstractMonster, p, new DeadMarkedPower(abstractMonster, player),1));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(abstractPlayer instanceof AbstractPlayerWithMinions){
            AbstractPlayerWithMinions player = (AbstractPlayerWithMinions) abstractPlayer;
            if(player.hasMinions()){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
