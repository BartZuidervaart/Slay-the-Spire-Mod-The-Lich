package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.WraithMinion;
import ultra.lich.player.LichClass;

public class WraithCard extends AbstractLichCard {

    public static final String ID = "TheLich:Wraith_card";
    public static final	String NAME = "Wraith";
    //TODO: work out how to adjust this reading when the card is updated
    public static final	String DESCRIPTION = "Summons a wraith with stats 2/1/20. Applies weak instead of attack and negative strength instead of defense. Putrid. Exhaust.";
    public static final	String UPDATE_DESCRIPTION = "Summons a wraith with stats 4/2/25. Applies weak instead of attack and negative strength instead of defense. Putrid. Exhaust.";
    private static final int COST = 3;

    public WraithCard() {
        super(ID, NAME, ImageLibrary.WRAITH, COST, DESCRIPTION,UPDATE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.exhaust = true;
        tags.add(LichCardEnum.SUMMON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (p instanceof LichClass) {
            LichClass caster = (LichClass) p;
            WraithMinion minion = this.upgraded ? new WraithMinion(caster, 25, 4, 2) : new WraithMinion(caster, 20, 2, 1);
            addToBot(new SummonMinionAction(caster, minion));
        }
    }

    public AbstractCard makeCopy() {
        return new WraithCard();
    }

}
