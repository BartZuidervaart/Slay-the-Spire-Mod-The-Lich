package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.player.LichClass;
import ultra.lich.powers.*;
import ultra.lich.relics.AbstractLichRelic;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class AbstractLichMinion extends AbstractFriendlyMonster {

    public static final Logger LOGGER = LogManager.getLogger(AbstractLichMinion.class.getName());

    private LichClass caster;
    private int baseAttack;
    private int baseDefense;

    public AbstractMonster attackTarget; //the minion will prefer to attack this target

    public AbstractLichMinion(String name, String id, int maxHealth, SpineAnimation animation, String animationName, LichClass caster, int baseAttack, int baseDefense){
        this(name, id, maxHealth, "img/minions/testminion.png", caster, baseAttack, baseDefense);

        this.loadAnimation(animation.atlasUrl,animation.skeletonUrl, animation.scale);
        this.state.setAnimation(0, animationName, true); //start animation
    }

    public AbstractLichMinion(String name, String id, int maxHealth, String imgUrl, LichClass caster, int baseAttack, int baseDefense) {
        super(name, id, maxHealth, 0.0F, 0.0F, 150.0F, 130.0F, imgUrl, 0f, 0f);
        this.caster = caster;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.correctStartingPosition(caster);
    }

    public AbstractLichMinion(String name, String id, int maxHealth, String imgUrl, LichClass caster, Texture[] attackIntents) {
        super(name, id, maxHealth, 0.0F, 0.0F, 150.0F, 130.0F, imgUrl, 0f, 0f,attackIntents);
        this.caster = caster;
        this.correctStartingPosition(caster);
    }

    private void correctStartingPosition(LichClass caster){
        this.drawX = caster.drawX + caster.hb_w + 100;
        this.drawY = caster.hb_y + caster.hb_h;
    }

    @Override
    public void die(){
        super.die();
        this.powers.forEach(power -> {
            power.onDeath(); //flush onDeath() because it does not activate on its own.
            if(power instanceof AbstractLichPower){
                ((AbstractLichPower)power).minionDies(this);
            }
        });
        this.caster.getMinions().monsters.forEach(monster -> {
            monster.powers.forEach(power -> {
                if(power instanceof AbstractLichPower){
                    ((AbstractLichPower)power).minionDies(this);
                }
            });
        });
        this.caster.relics.forEach(relic -> {
            if(relic instanceof AbstractLichRelic){
                ((AbstractLichRelic)relic).minionDies(this);
            }
        });
    }

    @Override
    public void damage(DamageInfo info){
        super.damage(info);
        this.powers.forEach(power -> power.atDamageReceive(info.output, info.type));
    }

    @Override
    public void render(SpriteBatch sb){
        sr.setPremultipliedAlpha(false);
        super.render(sb);
        sr.setPremultipliedAlpha(true);
    }

    public LichClass getCaster(){
        return caster;
    }

    @Override
    public void takeTurn(){
        super.takeTurn();
        this.autoAction();
    }

    @Override
    public void applyStartOfTurnPowers(){
        super.applyStartOfTurnPowers();
    }

    public void attack(AbstractMonster target){
        this.attack(target, getTotalAttack());
    }

    public void attack(AbstractMonster target, int damage){
        applyPowerAsPartOfAttack(target);
        DamageInfo info = new DamageInfo(this,damage,DamageInfo.DamageType.NORMAL);
        info.applyPowers(this.getCaster(), target); // <--- This lets the casters powers effect minions attacks
        addToBot(new DamageAction(target, info));
    }

    public void applyPowerAsPartOfAttack(AbstractMonster target){
        if(this.hasPower("Poison")){
            addToBot(new ApplyPowerAction(target,this,new PoisonPower(target,this, this.getPower("Poison").amount)));
        }
    }

    public void defend(AbstractMonster target){
        this.defend(target,getTotalDefense());
    }

    public void defend(AbstractMonster target, int armor){
        addToBot(new GainBlockAction(target,this, armor));
    }

    protected void autoAction(){
        this.autoAction(getTarget(),this);
    }

    protected void autoAction(AbstractMonster attackTarget, AbstractMonster defenseTarget){
        if(getTotalAttack() > 0){
            this.attack(attackTarget);
        }
        if(getTotalDefense() > 0){
            this.defend(defenseTarget);
        }
    }

    protected AbstractMonster getTarget(){
        ArrayList<AbstractMonster> DeadMarkedMonsters = new ArrayList<>(AbstractDungeon.getMonsters().monsters.stream().filter(monster -> monster.hasPower(DeadMarkedPower.POWER_ID)).collect(Collectors.toList()));
        if(attackTarget != null && !attackTarget.isDead){
            return attackTarget;
        } else if(DeadMarkedMonsters.size() > 0){
            return DeadMarkedMonsters.get((int)Math.floor(Math.random()*DeadMarkedMonsters.size()));
        } else {
            return AbstractDungeon.getRandomMonster();
        }
    }

    public int getTotalAttack(){
        int attack = 0;
        if(this.hasPower(AttackPower.POWER_ID)){
            attack = this.getPower(AttackPower.POWER_ID).amount;
        }
        return attack;
    }

    public int getTotalDefense(){
        int defense = 0;
        if(this.hasPower(DefensePower.POWER_ID)){
            defense = this.getPower(DefensePower.POWER_ID).amount;
        }
        return defense;
    }

    public void applyStartPowers(){
        addToBot(new ApplyPowerAction(this, this, new MinionPower(this,caster),1));
        addToBot(new ApplyPowerAction(this, this, new AttackPower(this, baseAttack, 0),1));
        addToBot(new ApplyPowerAction(this, this, new DefensePower(this, baseDefense, 0),1));
    }

    public void setAlpha(float alpha){
        Color color = tint.color;
        color.a = alpha;
        skeleton.setColor(color);
    }

}
