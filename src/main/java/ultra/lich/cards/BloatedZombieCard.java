package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.BloatedZombieMinion;
import ultra.lich.powers.SummonerPower;

public class BloatedZombieCard extends AbstractLichCard {

    public static final String ID = "TheLich:BloatedZombieCard";
    public static final String NAME = "Bloated Zombie";
    public static final String DESCRIPTION = "Summons a bloated zombie. Stats 0/0/5. Soak 5. Fleshrot. Explodes 3.";
    public static final String UPDATE_DESCRIPTION = "Summons a bloated zombie. Stats 0/0/10. Soak 10. Fleshrot. Explodes 5.";
    private static final int COST = 1;

    public BloatedZombieCard() {
        super(ID, NAME, ImageLibrary.BLOATED_ZOMBIE, COST, DESCRIPTION, UPDATE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);

        tags.add(LichCardEnum.SUMMON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        BloatedZombieMinion minion = this.upgraded ? new BloatedZombieMinion(p, 10, 0, 0, 10, 5) : new BloatedZombieMinion(p, 5, 0, 0, 5, 3);
        addToBot(new SummonMinionAction(p, minion));
        this.addToBot(new MakeTempCardInHandAction(new FleshRot(), 1));
    }

    public AbstractCard makeCopy() {
        return new BloatedZombieCard();
    }

}