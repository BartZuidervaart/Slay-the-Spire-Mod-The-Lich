package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.GravediggerMinion;
import ultra.lich.powers.SummonerPower;

public class GravediggerCard extends AbstractLichCard {

    public static final String ID = "TheLich:Gravedigger_card";
    public static final	String NAME = "Gravedigger";
    //TODO: work out how to adjust this reading when the card is updated
    public static final	String DESCRIPTION = "Discard 2 cards. Draw 1 card. Summons a gravedigger. Stats 1/0/10. Draws additional cards instead of attack.";
    public static final String UPGRADE_DESCRIPTION = "Discard 2 cards. Draw 1 card. Summons a gravedigger. Stats 2/5/10. Draws additional cards instead of attack." ;
    private static final int COST = 1;

    public GravediggerCard() {
        super(ID, NAME, ImageLibrary.GRAVEDIGGER, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        tags.add(LichCardEnum.SUMMON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, 2, false));
        addToBot(new DrawCardAction(p, 1));
        GravediggerMinion minion = this.upgraded ? new GravediggerMinion(p, 10, 2, 5) : new GravediggerMinion(p, 10, 1, 0);
        addToBot(new SummonMinionAction(p,minion));
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        return abstractPlayer.hand.size() >= 2;
    }

    public AbstractCard makeCopy() {
        return new GravediggerCard();
    }

}
