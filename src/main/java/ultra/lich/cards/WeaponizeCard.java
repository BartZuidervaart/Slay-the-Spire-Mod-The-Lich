package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.AttackPower;

public class WeaponizeCard extends AbstractLichCard {

    public static final String ID = "TheLich:WeaponizeCard";
    public static final	String NAME = "Weaponize";
    public static final	String DESCRIPTION = "Adds 4 attack to a minion.";
    public static final String UPGRADE_DESCRIPTION = "Adds 7 attack to a minion." ;
    private static final int COST = 1;

    public WeaponizeCard() {
        super(ID, NAME, ImageLibrary.WEAPONIZE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.UNCOMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new WeaponizeCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);
        int attackAmount = 4;
        if (upgraded) {
            attackAmount = 7;
        }

        if (target.hasPower(AttackPower.POWER_ID)) {
            AttackPower attack = (AttackPower) target.getPower(AttackPower.POWER_ID);
            attack.setAddAmount(attack.getAddAmount() + attackAmount);
        }

    }
}