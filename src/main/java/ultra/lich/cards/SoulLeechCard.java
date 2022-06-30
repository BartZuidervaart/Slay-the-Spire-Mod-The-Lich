package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.FatalDamageAction;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.OrbMinion;
import ultra.lich.powers.SummonerPower;

public class SoulLeechCard extends AbstractLichCard {
    public static final String ID = "TheLich:SoulLeechCard";
    public static final String NAME = "Soul Leech";
    public static final String DESCRIPTION = "Deal !D! damage. If Fatal, summons an orb. Stats 3/0/3. Orb. Summon sickness 1.";
    public static final String UPGRADE_DESCRIPTION = "Deal !D! damage. If Fatal, summons an orb. Stats 3/0/6. Orb 2. Summon sickness 1.";
    private static final int COST = 2;

    private static int DAMAGE = 12;
    private static int UPGRADE_DAMAGE = 2;

    public SoulLeechCard()  {
        super(ID, NAME, ImageLibrary.SOUL_LEECH, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.ENEMY);
        tags.add(LichCardEnum.SUMMON);
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
        int damage = this.upgraded ? 14 : 12;
            addToBot(new DamageAction(m,new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.LIGHTNING));
            addToBot(new FatalDamageAction(m, summonMinion));
    }

    private Runnable summonMinion = () -> {
        OrbMinion minion = this.upgraded ? new OrbMinion(AbstractDungeon.player, 6, 3, 0, 2, 1) : new OrbMinion(AbstractDungeon.player, 3, 3, 0, 1, 1);
        addToBot(new SummonMinionAction(AbstractDungeon.player,minion));
    };

    public AbstractCard makeCopy() {
        return new SoulLeechCard();
    }
}
