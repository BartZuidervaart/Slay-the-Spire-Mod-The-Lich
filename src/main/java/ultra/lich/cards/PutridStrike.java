package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.ClawMinion;
import ultra.lich.player.LichClass;

public class PutridStrike extends AbstractLichCard {
    public static final String ID = "TheLich:PutridStrike";
    public static final String NAME = "Putrid Strike";
    public static final String DESCRIPTION = "Deal !D! damage. Apply 3 poison. Summons a claw. Stats 5/0/2. Summoning sickness 1. Fleshrot.";
    public static final String UPGRADE_DESCRIPTION = "Deal !D! damage. Apply 5 poison. Summons a claw. Stats 5/0/2. Summoning sickness 1. Fleshrot.";
    private static final int COST = 1;

    private static int DAMAGE = 4;
    private static int UPGRADE_DAMAGE = 3;

    public PutridStrike()  {
        super(ID, NAME, ImageLibrary.PUTRID_STRIKE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.ENEMY);
        tags.add(LichCardEnum.SUMMON);
        this.baseDamage = DAMAGE;
        this.tags.add(CardTags.STRIKE);
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
        int damage = this.upgraded ? 7 : 4;
        int poison = this.upgraded ? 5 : 3;
        addToBot(new DamageAction(m,
                new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, poison)));

        if (p instanceof LichClass) {
            LichClass caster = (LichClass) p;
            addToBot(new SummonMinionAction(caster,new ClawMinion(caster, 2,5,0,1)));
        }
        this.addToBot(new MakeTempCardInHandAction(new FleshRot(),1));
    }

    public AbstractCard makeCopy() {
        return new PutridStrike();
    }
}
