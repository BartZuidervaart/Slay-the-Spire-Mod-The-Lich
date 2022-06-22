package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.GhoulMinion;
import ultra.lich.powers.SummonerPower;

public class GhoulCard extends AbstractLichCard {
    public static final String ID = "TheLich:GhoulCard";
    public static final String NAME = "Ghoul Strike";
    public static final String DESCRIPTION = "Deal !D! damage. Summons a ghoul. Stats 10/0/10. Applies both attack and defend to inflict damage. Cannibal. Summon sickness 3.";
    private static final int COST = 3;

    private static int DAMAGE = 10;
    private static int UPGRADE_DAMAGE = 5;

    public GhoulCard()  {
        super(ID, NAME, ImageLibrary.GHOUL, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardTarget.ENEMY);

        tags.add(LichCardEnum.SUMMON);
        this.tags.add(CardTags.STRIKE);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            this.upgradeDamage(UPGRADE_DAMAGE);
        }
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.SLASH_HEAVY));

        if (p.hasPower(SummonerPower.POWER_ID)) {
            SummonerPower caster = (SummonerPower) p.getPower(SummonerPower.POWER_ID);
            GhoulMinion minion = this.upgraded ? new GhoulMinion(caster,15,10,0, 3) : new GhoulMinion(caster,10,10,0, 3);
            addToBot(new SummonMinionAction(caster,minion));
        }
    }

    public AbstractCard makeCopy() {
        return new GhoulCard();
    }
}
