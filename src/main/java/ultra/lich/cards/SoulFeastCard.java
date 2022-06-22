package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SummonerPower;

public class SoulFeastCard extends AbstractLichCard {

    public static final String ID = "TheLich:SoulFeastCard";
    public static final	String NAME = "Soul Feast";
    public static final	String DESCRIPTION = "Tribute 1. Heal 4 hp. Exhaust.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 1. Heal 7 hp. Exhaust." ;
    private static final int COST = 1;

    public SoulFeastCard() {
        super(ID, NAME, ImageLibrary.SOUL_FEAST, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
        this.exhaust = true;
    }

    public AbstractCard makeCopy() {
        return new SoulFeastCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);

        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower)p.getPower(SummonerPower.POWER_ID);
            addToBot(new SacrificeMinionAction(caster,target));
        }

        int healAmount = this.upgraded ? 7 : 4;
        p.heal(healAmount, true);
    }
}