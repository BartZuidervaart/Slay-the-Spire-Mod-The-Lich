package ultra.lich.player;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import kobting.friendlyminions.characters.CustomCharSelectInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.TheLich;
import ultra.lich.cards.AggressiveCard;
import ultra.lich.cards.ClawStrike;
import ultra.lich.cards.Resilience;
import ultra.lich.cards.ZombieCard;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.eNums.LichEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SummonerPower;
import ultra.lich.relics.SoulJarRelic;

import java.util.ArrayList;

public class LichClass extends CustomPlayer {

    public static final Logger LOGGER = LogManager.getLogger(LichClass.class.getName());

    private static final int ENERGY_PER_TURN = 3;

    private static final String NAME = "The Lich";

    public static final SpineAnimation ANIMATION = new SpineAnimation("img/Lich.atlas", "img/Lich.json", 3f);

    public LichClass(PlayerClass setClass) {
        super(NAME, setClass,  new AnimatedEnergyOrb(ImageLibrary.ORB_ATLAS,ImageLibrary.ORB_JSON, ImageLibrary.ORB_ANIMATION_PASSIVE,ImageLibrary.ORB_ANIMATION_ACTIVE,ImageLibrary.ORB_TEXTURES,"img/char/necromancer/orb/vfx.png"), ANIMATION);

        initializeClass(null,
                ImageLibrary.NECROMANCER_SHOULDER_2,
                ImageLibrary.NECROMANCER_SHOULDER_1,
                ImageLibrary.NECROMANCER_CORPSE,
                getLoadout(),
                20.0F,
                -10.0F,
                220.0F,
                290.0F,
                new EnergyManager(ENERGY_PER_TURN)
        );

        this.state.setAnimation(0, "animtion0", true); //start animation

    }

    @Override
    public void renderPlayerImage(SpriteBatch sb) {
        sr.setPremultipliedAlpha(false);
        super.renderPlayerImage(sb);
        sr.setPremultipliedAlpha(true);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> startingDeck = new ArrayList<>();
        startingDeck.add(ZombieCard.ID);
        startingDeck.add(ZombieCard.ID);
        startingDeck.add(ZombieCard.ID);
        startingDeck.add(Resilience.ID);
        startingDeck.add(Resilience.ID);
        startingDeck.add(AggressiveCard.ID);
        startingDeck.add(AggressiveCard.ID);
        startingDeck.add(ClawStrike.ID);
        startingDeck.add(ClawStrike.ID);
        startingDeck.add(ClawStrike.ID);
        return startingDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add(SoulJarRelic.ID);
        UnlockTracker.markRelicAsSeen(SoulJarRelic.ID);
        return startingRelics;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CustomCharSelectInfo(LichClass.NAME, "An old man doomed to walk the spire dead and alone.", 30, 30, 0, 4, 99, 5, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return LichClass.NAME;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return LichCardEnum.LICH_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return TheLich.NECROCOLOR;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new ZombieCard();
    }

    @Override
    public Color getCardTrailColor() {
        return TheLich.NECROCOLOR;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAME;
    }

    @Override
    public AbstractPlayer newInstance() {
        return new LichClass(LichEnum.LICH_CLASS);
    }

    @Override
    public String getSpireHeartText() {
        return "NL You ready your Weapon...";
    }

    @Override
    public Color getSlashAttackColor() {
        return TheLich.NECROCOLOR;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us,~ ~oh Mighty Warrior,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }

    @Override
    public void applyStartOfCombatPreDrawLogic() {
        drawX = 200f;
        super.applyStartOfCombatPreDrawLogic();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SummonerPower(this,4)));
    }



    @Override
    public void update() {
        super.update();

//        this.minions.monsters.forEach(monster -> {
//            if (this.minions.hoveredMonster != monster && this.minions.hoveredMonster != null) {
//                //change alpha
//                monster.hbAlpha = 0.2f;
//                monster.reticleAlpha = 0.2f;
//                if(monster instanceof AbstractLichMinion){
//                    ((AbstractLichMinion) monster).setAlpha(0.2f);
//                }
//            } else {
//                monster.hbAlpha = 1f;
//                monster.reticleAlpha = 1f;
//                if(monster instanceof AbstractLichMinion){
//                    ((AbstractLichMinion) monster).setAlpha(1f);
//                }
//            }
//        });
    }

}
