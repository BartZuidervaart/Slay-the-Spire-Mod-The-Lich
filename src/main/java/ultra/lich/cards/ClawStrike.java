package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.ClawMinion;
import ultra.lich.player.LichClass;

public class ClawStrike extends AbstractLichCard {
    public static final String ID = "TheLich:Claw_Strike";
    public static final String NAME = "Strike";
    public static final String DESCRIPTION = "Deal !D! damage. Summons a claw. Stats 3/0/2. Summoning sickness 1.";
    private static final int COST = 1;

    private static int DAMAGE = 4;
    private static int UPGRADE_DAMAGE = 3;

    public ClawStrike()  {
        super(ID, NAME, ImageLibrary.CLAW_STRIKE, COST, DESCRIPTION, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.BASIC,
                AbstractCard.CardTarget.ENEMY);
        tags.add(LichCardEnum.SUMMON);
        this.tags.add(CardTags.STARTER_STRIKE);
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
            addToBot(new DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

            if (p instanceof LichClass) {
                LichClass caster = (LichClass) p;
                addToBot(new SummonMinionAction(caster, new ClawMinion(caster)));
            }
    }

    public AbstractCard makeCopy() {
        return new ClawStrike();
    }
}
