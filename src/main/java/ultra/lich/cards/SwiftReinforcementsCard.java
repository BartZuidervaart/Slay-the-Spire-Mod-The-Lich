package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.FlameSkull;
import ultra.lich.powers.SummonerPower;

public class SwiftReinforcementsCard extends AbstractLichCard {

    public static final String ID = "TheLich:Swift Reinforcements";
    public static final String NAME = "Swift Reinforcements";
    public static final String DESCRIPTION = "Summons 2 flame skulls. Stats 3/1/3. Summoning sickness 1. Soak 5. Explodes.";
    public static final String UPGRADE_DESCRIPTION = "Summons 3 flame skulls. Stats 3/1/3. Summoning sickness 1. Soak 5. Explodes.";
    private static final int COST = 1;

    public SwiftReinforcementsCard() {
        super(ID, NAME, ImageLibrary.SWIFT_REINFORCEMENTS, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new SwiftReinforcementsCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        for (int i = 0; i < (this.upgraded ? 3 : 2); i++) {
            FlameSkull minion = new FlameSkull(p, 3, 3, 1, 5, -1, 1);
            addToBot(new SummonMinionAction(p, minion));
        }
    }
}
