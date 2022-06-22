package ultra.lich.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.player.LichClass;

public class RitualSacrificeCard extends AbstractLichCard {

    public static final String ID = "TheLich:RitualSacrificeCard";
    public static final	String NAME = "Ritual Sacrifice";
    public static final	String DESCRIPTION = "Sacrifice your last minion. Deal 3 times its Attack damage and gain 3 times its Defense as block.";
    public static final String UPGRADE_DESCRIPTION = "Sacrifice your last minion. Deal 4 times its attack damage and gain 4 times its defense as block." ;
    private static final int COST = 2;

    public RitualSacrificeCard() {
        super(ID, NAME, ImageLibrary.RITUAL_SACRIFICE, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.sacrifice = 1;
    }

    public AbstractCard makeCopy() {
        return new RitualSacrificeCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int multiplier = 3;
        if(upgraded){
         multiplier = 4;
        }
        int damage = 0;
        int block = 0;
        if(p instanceof LichClass){
            LichClass caster = (LichClass)p;
            int lastMonster = caster.getMinions().monsters.size()-1;
            AbstractMonster monster = caster.getMinions().monsters.get(lastMonster);
            if(monster instanceof AbstractLichMinion){
                AbstractLichMinion minion = (AbstractLichMinion) monster;
                damage = minion.getTotalAttack();
                block = minion.getTotalDefense();
            }
        }
        //Add block
        addToBot(new GainBlockAction(AbstractDungeon.player,AbstractDungeon.player, block * multiplier));

        //Deal damage
        DamageInfo info = new DamageInfo(AbstractDungeon.player, damage * multiplier, DamageInfo.DamageType.NORMAL);
        info.applyPowers(AbstractDungeon.player,abstractMonster);
        addToBot(new DamageAction(abstractMonster, info, AbstractGameAction.AttackEffect.LIGHTNING));
        super.use(p,abstractMonster);
    }

}
