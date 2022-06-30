package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.ClawMinion;
import ultra.lich.powers.SummonerPower;

public class ClawCard extends AbstractLichCard {

    public static final String ID = "TheLich:ClawCard";
    public static final String NAME = "Claw";
    public static final String DESCRIPTION = "Summons a claw. Stats 3/0/3. Summoning sickness 1. Revenant. Increase the attack and health of ALL Claw cards by 2 this combat.";
    public static final String UPGRADE_DESCRIPTION = "Summons a claw. Stats 3/0/6. Summoning sickness 1. Revenant. Increase the attack and health of ALL Claw cards by 2 this combat.";
    private static final int COST = 0;

    private int baseHealth = 3;
    private int upgradedBaseHealth = 6;
    private int baseDamage = 3;
    private int additionalDamage = 2;
    public int timesUsed = 0;

    public ClawCard() {
        super(ID, NAME, ImageLibrary.CLAW, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new ClawCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int totalDamage = baseDamage + (additionalDamage * timesUsed);
        int totalHealth = this.upgraded ? upgradedBaseHealth + (additionalDamage * timesUsed) : baseHealth + (additionalDamage * timesUsed);

        ClawMinion minion = this.upgraded ? new ClawMinion(p, totalHealth, totalDamage, 0, 1) : new ClawMinion(p, totalHealth, totalDamage, 0, 1);
        minion.attackTarget = abstractMonster;
        addToBot(new SummonMinionAction(p, minion));
        this.updateAllCards();
    }

    public void upgradeAttack() {
        this.timesUsed++;
        int totalDamage = baseDamage + (additionalDamage * timesUsed);
        int totalHealth = this.upgraded ? upgradedBaseHealth + (additionalDamage * timesUsed) : baseHealth + (additionalDamage * timesUsed);
        String description = "Summons a claw. Stats " + totalDamage + "/0/" + totalHealth + ". Summoning sickness 1. Revenant. Increase the attack and health of ALL Claw cards by 2 this combat.";
        this.rawDescription = description;
        this.initializeDescription();
    }

    private void updateAllCards() {
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof ClawCard) {
                ((ClawCard) c).upgradeAttack();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof ClawCard) {
                ((ClawCard) c).upgradeAttack();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ClawCard) {
                ((ClawCard) c).upgradeAttack();
            }
        }
    }
}
