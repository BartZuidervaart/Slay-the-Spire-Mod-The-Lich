package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.cards.status.CannibalizeCard;
import ultra.lich.powers.SummonSicknessPower;
import ultra.lich.powers.SummonerPower;

public class GhoulMinion  extends AbstractLichMinion {

    private static String NAME = "Ghoul";
    private static String ID = "TheLich:GhoulMinion";

    private int summonSickness;

    public GhoulMinion(AbstractCreature summoner, int hp, int attack, int defense, int summonSickness) {
        super(NAME, ID, hp, new SpineAnimation("img/minions/Ghoul.atlas", "img/minions/Ghoul.json", 0.7f), "animtion0", summoner, attack, defense);
        this.summonSickness = summonSickness;
        this.baseAttackEffect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
    }

    @Override
    public void attack(AbstractMonster target){
        this.attack(target, getTotalAttack()+getTotalDefense());
    }

    @Override
    public void defend(AbstractMonster target){
        //ghouls don't do defense
    }

    @Override
    public void applyStartOfTurnPowers(){
        this.addToBot(new MakeTempCardInHandAction(new CannibalizeCard(),1));
    }

    @Override
    public void applyStartPowers(){
        super.applyStartPowers();

        addToBot(new ApplyPowerAction(this, this, new SummonSicknessPower(this, summonSickness)));
    }
}
