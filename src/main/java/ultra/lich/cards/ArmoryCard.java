package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.DefensePower;

public class ArmoryCard extends AbstractLichCard {

    public static final String ID = "TheLich:ArmoryCard";
    public static final	String NAME = "Armory";
    public static final	String DESCRIPTION = "Adds 4 Defense to a Minion.";
    public static final String UPGRADE_DESCRIPTION = "Adds 7 Defense to a Minion." ;
    private static final int COST = 1;

    public ArmoryCard() {
        super(ID, NAME, ImageLibrary.ARMORY, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.UNCOMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new ArmoryCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        int defenseAmount = 4;
        if(upgraded){
            defenseAmount = 7;
        }

        if(target.hasPower(DefensePower.POWER_ID)){
            DefensePower defense = (DefensePower)target.getPower(DefensePower.POWER_ID);
            defense.setAddAmount(defense.getAddAmount()+defenseAmount);
        }
    }
}