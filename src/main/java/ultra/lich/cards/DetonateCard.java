package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SummonerPower;

public class DetonateCard extends AbstractLichCard {

    public static final String ID = "TheLich:DetonateCard";
    public static final	String NAME = "Detonate";
    public static final	String DESCRIPTION = "Sacrifice all minions. Deal damage to all enemies equal to 10 times the amount of minions lost.";
    public static final String UPGRADE_DESCRIPTION = "Sacrifice all minions. Deal damage to all enemies equal to 15 times the amount of minions lost.";
    private static final int COST = 3;

    public DetonateCard() {
        super(ID, NAME, ImageLibrary.DETONATE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public AbstractCard makeCopy() {
        return new DetonateCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);

            this.damage = this.upgraded ? 15 : 10;
            damage = damage * caster.minions.monsters.size();
            addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));

            //kill them all
            caster.minions.monsters.forEach(monster -> addToBot(new SacrificeMinionAction(caster,monster)));
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