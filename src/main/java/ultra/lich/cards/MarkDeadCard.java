package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.DeadMarkedPower;
import ultra.lich.powers.SummonerPower;

public class MarkDeadCard extends AbstractLichCard {

    public static final String ID = "TheLich:MarkDeadCard";
    public static final	String NAME = "Mark Dead";
    public static final	String DESCRIPTION = "Deal !D! damage. Apply dead marked. Fleshrot.";
    public static final String UPGRADE_DESCRIPTION = "Deal !D! damage. Apply dead marked." ;
    private static final int COST = 0;

    private static int DAMAGE = 4;
    private static int UPGRADE_DAMAGE = 3;

    public MarkDeadCard() {
        super(ID, NAME, ImageLibrary.DEAD_MARKED, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
    }

    public AbstractCard makeCopy() {
        return new MarkDeadCard();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            this.upgradeDamage(UPGRADE_DAMAGE);
        }
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        super.use(p,abstractMonster);

        if(!this.upgraded) {
            this.addToBot(new MakeTempCardInHandAction(new FleshRot(), 1));
        }

        addToBot(new DamageAction(abstractMonster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower)p.getPower(SummonerPower.POWER_ID);
            addToBot(new ApplyPowerAction(abstractMonster, p, new DeadMarkedPower(abstractMonster, caster)));
        }
    }
}
