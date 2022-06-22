package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.ZombieMinion;
import ultra.lich.powers.SummonerPower;

public class ZombieCard extends AbstractLichCard {

    public static final String ID = "TheLich:Zombie_card";
    public static final String NAME = "Zombie";
    public static final String DESCRIPTION = "Summons a zombie. Stats 0/0/5. Soak 5.";
    public static final String UPDATE_DESCRIPTION = "Summons a zombie. Stats 0/0/10. Soak 5.";
    private static final int COST = 1;

    public ZombieCard() {
        super(ID, NAME, ImageLibrary.ZOMBIE, COST, DESCRIPTION, UPDATE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.tags.add(CardTags.STARTER_DEFEND);
        tags.add(LichCardEnum.SUMMON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(SummonerPower.POWER_ID)) {
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);
            ZombieMinion minion = this.upgraded ? new ZombieMinion(caster, 10, 5) : new ZombieMinion(caster, 5, 5);
            addToBot(new SummonMinionAction(caster, minion));
        }
    }

    public AbstractCard makeCopy() {
        return new ZombieCard();
    }

}
