package ultra.lich.cards.status;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.AbstractLichPower;
import ultra.lich.powers.SummonerPower;
import ultra.lich.relics.AbstractLichRelic;

public class FleshRot extends CustomCard {
    public static final String NAME = "Flesh Rot";
    public static final String ID = "FleshRot";
    private static final String DESCRIPTION = "Unplayable. At the end of your turn, take 2 poison. Exhaust.";
    private static final String UPGRADE_DESCRIPTION = "Unplayable. At the end of your turn, take 4 poison. Exhaust.";

    public FleshRot() {
        super(ID, NAME, ImageLibrary.FLESHROT, -2, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
        this.magicNumber = 2;
        this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            this.addToBot(new ApplyPowerAction(p, p, new PoisonPower(p, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
            p.powers.forEach(power -> {
                if(power instanceof AbstractLichPower){
                    ((AbstractLichPower)power).obtainedFleshRotPoison(this.magicNumber);
                }
            });
            p.relics.forEach(relic -> {
                if(relic instanceof AbstractLichRelic){
                    ((AbstractLichRelic)relic).obtainedFleshRotPoison(this.magicNumber);
                }
            });
            if(p.hasPower(SummonerPower.POWER_ID)){
                SummonerPower caster = (SummonerPower)p.getPower(SummonerPower.POWER_ID);
                caster.minions.monsters.forEach(monster -> {
                    monster.powers.forEach(power -> {
                        if(power instanceof AbstractLichPower){
                            ((AbstractLichPower)power).obtainedFleshRotPoison(this.magicNumber);
                        }
                    });
                });
            }
        }

    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    public AbstractCard makeCopy() {
        return new FleshRot();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }
}
