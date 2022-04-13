package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;

public abstract class AbstractLichMinion extends AbstractFriendlyMonster {

    private AbstractPlayerWithMinions caster;

    public AbstractLichMinion(String name, String id, int maxHealth, SpineAnimation animation, String animationName, AbstractPlayerWithMinions caster){
        this(name, id, maxHealth, "img/minions/testminion.png", caster);
        this.loadAnimation(animation.atlasUrl,animation.skeletonUrl, animation.scale);
        AnimationState.TrackEntry e = this.state.setAnimation(0, animationName, true);
    }

    public AbstractLichMinion(String name, String id, int maxHealth, String imgUrl, AbstractPlayerWithMinions caster) {
        super(name, id, maxHealth, -8.0F, 10.0F, 230.0F, 240.0F, imgUrl, 0f, 0f);
        this.caster = caster;
        this.correctStartingPosition(caster);
    }

    public AbstractLichMinion(String name, String id, int maxHealth, String imgUrl, AbstractPlayerWithMinions caster, Texture[] attackIntents) {
        super(name, id, maxHealth, -8.0F, 10.0F, 230.0F, 240.0F, imgUrl, 0f, 0f,attackIntents);
        this.caster = caster;
        this.correctStartingPosition(caster);
    }

    private void correctStartingPosition(AbstractPlayerWithMinions caster){

        this.drawX = caster.drawX + caster.hb_w + 100;
        this.drawY = caster.hb_y + caster.hb_h;
    }

    @Override
    public void render(SpriteBatch sb){
        sr.setPremultipliedAlpha(false);
        super.render(sb);
        sr.setPremultipliedAlpha(true);
    }

    @Override
    public void damage(DamageInfo info){
        int healthRemaining = (this.currentBlock + this.currentHealth) - info.output;
        super.damage(info);
        //deal remaining damage to other minions or to caster.
        if(healthRemaining < 0){
            int tremble = Math.abs(healthRemaining);

            //If the creature has soak. Soak some of the remaining damage.
            //This soak can become negative as well.
            if(this.hasPower("SoakPower")){
                tremble -= this.getPower("SoakPower").amount;
            }

            info.output = tremble;
            if(caster.hasMinions()){
                this.caster.getMinions().getRandomMonster().damage(info);
            } else {
                caster.damage(info);
            }
        }
    }

    public AbstractPlayerWithMinions getCaster(){
        return caster;
    }
}
