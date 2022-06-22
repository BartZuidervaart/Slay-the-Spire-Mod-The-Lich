package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.images.ImageLibrary;

public class InfestationCard extends AbstractLichCard {
    public static final String ID = "TheLich:InfestationCard";
    public static final String NAME = "Infestation";
    public static final String DESCRIPTION = "Lose all poison. Deal damage and apply poison equal to the amount of poison lost. Fleshrot.";
    private static final int COST = 1;

    public InfestationCard()  {
        super(ID, NAME, ImageLibrary.INFESTATION, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.ENEMY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(PoisonPower.POWER_ID)){
            PoisonPower poison = (PoisonPower)p.getPower(PoisonPower.POWER_ID);
            int damageAmount = poison.amount;
            addToBot(new RemoveSpecificPowerAction(p,p,PoisonPower.POWER_ID));
            addToBot(new DamageAction(m,new DamageInfo(p,damageAmount, DamageInfo.DamageType.NORMAL)));
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, damageAmount)));
        }
        this.addToBot(new MakeTempCardInHandAction(new FleshRot(),1));
    }

    public AbstractCard makeCopy() {
        return new InfestationCard();
    }

    @Override
    public void upgrade() {
        this.upgradeBaseCost(0);
        super.upgrade();
    }
}
