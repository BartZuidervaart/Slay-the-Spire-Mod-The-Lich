package ultra.lich.player;

import basemod.abstracts.CustomEnergyOrb;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimatedEnergyOrb extends CustomEnergyOrb {

    public static final Logger LOGGER = LogManager.getLogger(AnimatedEnergyOrb.class.getName());

    public SpineAnimation animation;
    public AnimationState state;
    protected TextureAtlas atlas;
    protected Skeleton skeleton;
    public static SkeletonMeshRenderer sr;
    protected boolean hasEnergy;
    protected AnimationState.TrackEntry e;
    public static String ATLAS_PATH;
    public static String JSON_PATH;
    public static String PASSIVE_ANIMATION;
    public static String ACTIVE_ANIMATION;

    public AnimatedEnergyOrb(String atlasPath, String jsonPath, String passiveAnimation, String activeAnimation, String[] orbTextures, String orbVfxPath) {
        super(orbTextures, orbVfxPath, null);
        ATLAS_PATH = atlasPath;
        JSON_PATH = jsonPath;
        PASSIVE_ANIMATION = passiveAnimation;
        ACTIVE_ANIMATION = activeAnimation;

        this.initiateAnimationEssentials();
    }

    @Override
    public void renderOrb(SpriteBatch spriteBatch, boolean enabled, float current_x, float current_y) {
        sr.setPremultipliedAlpha(false);
        this.state.update(Gdx.graphics.getDeltaTime());
        this.state.apply(this.skeleton);
        this.skeleton.updateWorldTransform();
        this.skeleton.setPosition(current_x, current_y-100);
        this.skeleton.setColor(Color.WHITE);
        spriteBatch.end();
        CardCrawlGame.psb.begin();
        sr.draw(CardCrawlGame.psb, this.skeleton);
        CardCrawlGame.psb.end();
        spriteBatch.begin();
        sr.setPremultipliedAlpha(true);
    }

    @Override
    public void updateOrb(int i) {
        if(!hasEnergy && i > 0){
            hasEnergy = true;
            this.initiateAnimationEssentials(); //seems needlessly inefficient, but it won't run without.
            this.e = this.state.setAnimation(0, ACTIVE_ANIMATION, true); //start animation
        }
        if(hasEnergy && i <= 0) {
            hasEnergy = false;
            this.initiateAnimationEssentials(); //seems needlessly inefficient, but it won't run without.
            this.e = this.state.setAnimation(0, PASSIVE_ANIMATION, true); //start animation
        }
    }

    protected void initiateAnimationEssentials(){
        this.atlas = new TextureAtlas(Gdx.files.internal(ATLAS_PATH));
        SkeletonJson json = new SkeletonJson(atlas);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(JSON_PATH));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.state = new AnimationState(new AnimationStateData(skeletonData));
        this.animation = new SpineAnimation(ATLAS_PATH, JSON_PATH, 1f);

        sr = new SkeletonMeshRenderer();
        sr.setPremultipliedAlpha(true);
    }
}
