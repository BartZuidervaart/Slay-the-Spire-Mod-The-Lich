package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SoakPower;

public class EmergencyReservesCard extends AbstractLichCard {

    public static final String ID = "TheLich:EmergencyReservesCard";
    public static final	String NAME = "Emergency Reserves";
    public static final	String DESCRIPTION = "Adds 5 soak to a Minion.";
    public static final String UPGRADE_DESCRIPTION = "Adds 10 soak to a Minion." ;
    private static final int COST = 0;

    public EmergencyReservesCard() {
        super(ID, NAME, ImageLibrary.EMERGENCY_RESERVES, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new EmergencyReservesCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        int soakAmount = 5;
        if(upgraded){
            soakAmount = 10;
        }

        this.addToBot(new ApplyPowerAction(target,p, new SoakPower(target, soakAmount)));
    }
}