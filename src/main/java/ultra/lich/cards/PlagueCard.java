package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import ultra.lich.images.ImageLibrary;
import ultra.lich.player.LichClass;

public class PlagueCard extends AbstractLichCard {

    public static final String ID = "TheLich:PlagueCard";
    public static final	String NAME = "Plague";
    public static final	String DESCRIPTION = "Lose all poison applied to you and add it to your minions. Poisoned minion attacks apply poison to their target. Exhaust.";
    private static final int COST = 2;

    public PlagueCard() {
        super(ID, NAME, ImageLibrary.PLAGUE, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.exhaust = true;
    }

    public AbstractCard makeCopy() {
        return new PlagueCard();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(1);
        }
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if(p.hasPower("Poison")){
            PoisonPower poison = (PoisonPower)p.getPower("Poison");
            int poisonAmount = poison.amount;
            this.addToBot(new RemoveSpecificPowerAction(p, p, "Poison"));
            if(p instanceof LichClass){
                LichClass summoner = (LichClass)p;
                summoner.getMinions().monsters.forEach(monster -> this.addToBot(new ApplyPowerAction(monster,summoner, new PoisonPower(monster,summoner,poisonAmount))));
            }
        }
    }
}

