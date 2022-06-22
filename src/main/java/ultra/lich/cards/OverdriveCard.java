package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.powers.AttackPower;
import ultra.lich.powers.SummonSicknessPower;
import ultra.lich.powers.SummonerPower;

public class OverdriveCard extends AbstractLichCard {

    public static final String ID = "TheLich:Overdrive";
    public static final	String NAME = "Overdrive";
    public static final	String DESCRIPTION = "All minions gain 3 Attack but also 3 Summoning sickness";
    public static final String UPGRADE_DESCRIPTION = "All minions gain 5 Attack but also 3 Summoning sickness" ;
    private static final int COST = 2;

    public OverdriveCard() {
        super(ID, NAME, ImageLibrary.BLITZKRIEG, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.POWER,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
    }

    public AbstractCard makeCopy() {
        return new OverdriveCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);
            caster.minions.monsters.forEach(
                    monster -> {
                        if(monster instanceof AbstractLichMinion){
                            if(this.upgraded){
                                if(monster.hasPower(AttackPower.POWER_ID)){
                                    AttackPower attack = (AttackPower)monster.getPower(AttackPower.POWER_ID);
                                    attack.setAddAmount(attack.getAddAmount() + 5);
                                }
                            } else {
                                if(monster.hasPower(AttackPower.POWER_ID)){
                                    AttackPower attack = (AttackPower)monster.getPower(AttackPower.POWER_ID);
                                    attack.setAddAmount(attack.getAddAmount() + 3);
                                }
                            }
                            if(monster.hasPower(SummonSicknessPower.POWER_ID)){
                                AbstractPower sickness = monster.getPower(SummonSicknessPower.POWER_ID);
                                sickness.amount = sickness.amount + 3;
                            } else {
                                addToBot(new ApplyPowerAction(monster, caster.owner, new SummonSicknessPower(monster, 3),1)); //slowly dies
                            }
                        }
                    }
            );
        }
    }
}
