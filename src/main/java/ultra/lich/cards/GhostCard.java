package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.GhostMinion;
import ultra.lich.powers.SummonerPower;

public class GhostCard extends AbstractLichCard {

    public static final String ID = "TheLich:GhostCard";
    public static final	String NAME = "Ghost";
    public static final	String DESCRIPTION = "Summons a ghost with stats 1/3/10. Applies vulnerable instead of attack. Summons an Orb every turn with stats 1/1/defense and summoning sickness 1. Orb. Ethereal.";
    public static final	String UPDATE_DESCRIPTION = "Summons a ghost with stats 1/5/15. Applies vulnerable instead of attack. Summons an Orb every turn with stats 1/1/defense and summoning sickness 1. Orb 2. Ethereal.";
    private static final int COST = 1;

    public GhostCard() {
        super(ID, NAME, ImageLibrary.GHOST, COST, DESCRIPTION,UPDATE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.isEthereal = true;
        tags.add(LichCardEnum.SUMMON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (p.hasPower(SummonerPower.POWER_ID)) {
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);
            GhostMinion minion = this.upgraded ? new GhostMinion(caster, 15, 1, 5,2) : new GhostMinion(caster, 10, 1, 3,1);
            addToBot(new SummonMinionAction(caster,minion));
        }
    }

    public AbstractCard makeCopy() {
        return new GhostCard();
    }

}