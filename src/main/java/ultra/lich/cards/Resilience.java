package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.powers.DefensePower;
import ultra.lich.powers.SummonerPower;

public class Resilience extends AbstractLichCard {

    public static final String ID = "TheLich:Resilience";
    public static final	String NAME = "Resilience";
    public static final	String DESCRIPTION = "All current minions gain 1 Defense to their stats.";
    public static final String UPGRADE_DESCRIPTION = "All current minions gain 2 Defense to their stats" ;
    private static final int COST = 1;

    public Resilience() {
        super(ID, NAME, ImageLibrary.RESILIENCE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
    }

    public AbstractCard makeCopy() {
        return new Resilience();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);
            caster.minions.monsters.forEach(
                    monster -> {
                        if(monster instanceof AbstractLichMinion){
                            if(this.upgraded){
                                if(monster.hasPower(DefensePower.POWER_ID)){
                                    DefensePower defense = (DefensePower)monster.getPower(DefensePower.POWER_ID);
                                    defense.setAddAmount(defense.getAddAmount() + 2);
                                }
                            } else {
                                if(monster.hasPower(DefensePower.POWER_ID)){
                                    DefensePower defense = (DefensePower)monster.getPower(DefensePower.POWER_ID);
                                    defense.setAddAmount(defense.getAddAmount() + 1);
                                }
                            }
                        }
                    }
            );
        }
    }
}
