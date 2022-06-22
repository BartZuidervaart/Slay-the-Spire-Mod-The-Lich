package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SummonerPower;

public class KnowledgeFromTheGraveCard extends AbstractLichCard {

    public static final Logger LOGGER = LogManager.getLogger(KnowledgeFromTheGraveCard.class.getName());

    public static final String ID = "TheLich:KnowledgeFromTheGraveCard";
    public static final	String NAME = "Grave robbery";
    public static final	String DESCRIPTION = "Tribute 1. Return 1 card from your discard pile to your hand.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 1. Return 2 cards from your discard pile to your hand." ;
    private static final int COST = 0;

    public KnowledgeFromTheGraveCard() {
        super(ID, NAME, ImageLibrary.KNOWLEDGE_FROM_THE_GRAVE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.COMMON, MinionTargeting.MINION);
    }

    public AbstractCard makeCopy() {
        return new KnowledgeFromTheGraveCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractMonster target = MinionTargeting.getTarget(this);

        if(p.hasPower(SummonerPower.POWER_ID)){
            SummonerPower caster = (SummonerPower)p.getPower(SummonerPower.POWER_ID);
            addToBot(new SacrificeMinionAction(caster,target));
        }


        int drawAmount = 1;
        if(upgraded){
            drawAmount = 2;
        }

        this.addToBot(new BetterDiscardPileToHandAction(drawAmount));
    }
}
