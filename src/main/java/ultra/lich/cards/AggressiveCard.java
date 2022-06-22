package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.player.LichClass;
import ultra.lich.powers.AttackPower;

public class AggressiveCard extends AbstractLichCard {

    public static final String ID = "TheLich:AggressiveCard";
    public static final	String NAME = "Aggressive";
    public static final	String DESCRIPTION = "All current Minions gain 1 Attack to their stats.";
    public static final String UPGRADE_DESCRIPTION = "All current Minions gain 2 Attack to their stats" ;
    private static final int COST = 1;

    public AggressiveCard() {
        super(ID, NAME, ImageLibrary.AGGRESSIVE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
    }

    public AbstractCard makeCopy() {
        return new AggressiveCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (p instanceof LichClass) {
            LichClass caster = (LichClass) p;
            caster.getMinions().monsters.forEach(
                    monster -> {
                        if (monster instanceof AbstractLichMinion) {
                            int addAmount = this.upgraded ? 2 : 1;
                            if (monster.hasPower(AttackPower.POWER_ID)) {
                                AttackPower attack = (AttackPower) monster.getPower(AttackPower.POWER_ID);
                                attack.setAddAmount(attack.getAddAmount() + addAmount);
                            }
                        }
                    }
            );
        }
    }
}
