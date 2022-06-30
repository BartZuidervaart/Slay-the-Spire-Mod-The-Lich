package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.FlameSkull;
import ultra.lich.powers.SummonerPower;

public class FlameSkullCard extends AbstractLichCard {

    public static final String ID = "TheLich:FlameSkullCard";
    public static final	String NAME = "Flame skull";
    public static final	String DESCRIPTION = "Summons a flame skull. Stats 3/1/3. Summoning sickness 1. Soak 3. Revenant. Explodes.";
    public static final String UPGRADE_DESCRIPTION = "Summons a flame skull. Stats 6/1/3. Summoning sickness 1. Soak 6. Revenant. Explodes." ;
    private static final int COST = 0;

    public FlameSkullCard() {
        super(ID, NAME, ImageLibrary.FLAMESKULL, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new FlameSkullCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        FlameSkull minion = this.upgraded ? new FlameSkull(p, 3, 6, 1, 6, -1, 1) : new FlameSkull(p, 3, 3, 1, 3, -1, 1);
        minion.attackTarget = abstractMonster;
        addToBot(new SummonMinionAction(p, minion));
    }
}
