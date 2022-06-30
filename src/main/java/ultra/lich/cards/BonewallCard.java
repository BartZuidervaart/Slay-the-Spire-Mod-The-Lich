package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.BoneWallMinion;
import ultra.lich.powers.SummonerPower;

public class BonewallCard extends AbstractLichCard {

    public static final String ID = "TheLich:BonewallCard";
    public static final String NAME = "Bone wall";
    //TODO: work out how to adjust this reading when the card is updated
    public static final String DESCRIPTION = "Summons a bone wall with stats 3/8/20. Instead of attacking, applies amount of thorns. 10 Soak. Putrid. Exhaust.";
    public static final String UPDATE_DESCRIPTION = "Summons a bone wall with stats 5/12/25. Instead of attacking, applies amount of thorns. 15 Soak. Putrid. Exhaust.";
    private static final int COST = 3;

    public BonewallCard() {
        super(ID, NAME, ImageLibrary.BONE_WALL, COST, DESCRIPTION, UPDATE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.exhaust = true;
        tags.add(LichCardEnum.SUMMON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        BoneWallMinion minion = this.upgraded ? new BoneWallMinion(p, 25, 5, 12, 15, 1) : new BoneWallMinion(p, 20, 3, 8, 10, 1);
        addToBot(new SummonMinionAction(p, minion));
    }

    public AbstractCard makeCopy() {
        return new BonewallCard();
    }

}
