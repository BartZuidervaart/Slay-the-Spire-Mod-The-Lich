package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.ZombieMinion;
import ultra.lich.powers.SummonerPower;

public class HordeCard extends AbstractLichCard {

    public static final String ID = "TheLich:HordeCard";
    public static final String NAME = "Horde";
    public static final String DESCRIPTION = "Summons 4 zombies. Stats 3/0/5. Soak 5.";
    public static final String UPDATE_DESCRIPTION = "Summons 4 zombies. Stats 3/0/10. Soak 5.";
    private static final int COST = 3;

    public HordeCard() {
        super(ID, NAME, ImageLibrary.HORDE, COST, DESCRIPTION, UPDATE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        tags.add(LichCardEnum.SUMMON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            for(int i = 0; i < 4; i++){
                ZombieMinion minion = this.upgraded ?
                        new ZombieMinion(p, 10,3,0, 5) :
                        new ZombieMinion(p, 5,3,0, 5);
                addToBot(new SummonMinionAction(p,minion));
            }
    }

    public AbstractCard makeCopy() {
        return new HordeCard();
    }

}