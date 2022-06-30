package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.DeadMarkedPower;
import ultra.lich.powers.SummonerPower;

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
        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);

            int damage = 5;
            if(this.upgraded){
                damage = 7;
            }
            damage = damage * caster.minions.monsters.size();
            addToBot(new DamageAction(abstractMonster,
                    new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            addToBot(new ApplyPowerAction(abstractMonster, p, new DeadMarkedPower(abstractMonster, caster),1));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(abstractPlayer.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower) abstractPlayer.getPower(SummonerPower.POWER_ID);
            if(caster.hasMinions()){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
