package ultra.lich.player;

import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.characters.CustomCharSelectInfo;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.TheLich;
import ultra.lich.cards.DefendWhite;
import ultra.lich.eNums.LichCardColorEnum;
import ultra.lich.eNums.LichEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.powers.SummonsPower;

import java.util.ArrayList;

public class LichClass extends AbstractPlayerWithMinions {

    public static final Logger LOGGER = LogManager.getLogger(LichClass.class.getName());

    private static final int ENERGY_PER_TURN = 3;

    private static final String NAME = "The Lich";

    public static final SpineAnimation ANIMATION = new SpineAnimation("img/Lich.atlas","img/Lich.json", 3f);

    public LichClass(PlayerClass setClass){
        super(NAME, setClass, ImageLibrary.ORB_TEXTURES,"img/char/necromancer/orb/vfx.png",ANIMATION);

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

        AnimationState.TrackEntry e = this.state.setAnimation(0, "animtion0", true);
    }

    @Override
    public void renderPlayerImage(SpriteBatch sb){
        sr.setPremultipliedAlpha(false);
        super.renderPlayerImage(sb);
        sr.setPremultipliedAlpha(true);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> startingDeck = new ArrayList<>();
        startingDeck.add("Defend_W");
        startingDeck.add("Defend_W");
        startingDeck.add("Defend_W");
        startingDeck.add("Defend_W");
        startingDeck.add("Defend_W");
        startingDeck.add("Bone_Armory");
        startingDeck.add("Bone_Armory");
        startingDeck.add("Bone_Armory");
        startingDeck.add("Bone_Armory");
        startingDeck.add("Bone_Armory");
        startingDeck.add("Strike_W");
        startingDeck.add("Strike_W");
        startingDeck.add("Strike_W");
        startingDeck.add("Strike_W");
        startingDeck.add("Strike_W");
        startingDeck.add("Strike_W");
        return startingDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add("Vampire_Amulet");
        UnlockTracker.markRelicAsSeen("Vampire_Amulet");
        return startingRelics;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return getInfo();
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return LichClass.NAME;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return LichCardColorEnum.LICH_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return TheLich.NECROCOLOR;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefendWhite();
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
    public CustomCharSelectInfo getInfo() {
        return new CustomCharSelectInfo(LichClass.NAME, "An old man doomed to walk the spire dead and alone.", 30, 30, 0, 4, 99, 5, this, getStartingRelics(), getStartingDeck(),false);
    }

    @Override
    public void damage(DamageInfo info){
        //Let minions take damage first
        if(this.hasMinions()){
            LOGGER.info("attack minion");
            this.getMinions().getRandomMonster().damage(info);
        } else {
            LOGGER.info("attack player");
            super.damage(info);
        }
    }

    @Override
    public void applyStartOfCombatPreDrawLogic(){
        drawX = 200f;
        super.applyStartOfCombatPreDrawLogic();
    }

    @Override
    public boolean addMinion(AbstractFriendlyMonster minion){
        if(this.hasMinions() && this.getAmountOfMinions() >= this.getMaxMinions()){
           // this.getMinions().monsters.get(0).die(); //replace oldest with newest
        }
        boolean returnValue = super.addMinion(minion);
        this.summonsChanges();
        return returnValue;
    }

    @Override
    public boolean removeMinion(AbstractFriendlyMonster minion){
        boolean returnValue = super.removeMinion(minion);
        this.summonsChanges();
        LOGGER.info("subtracting minions result is now:"+this.getAmountOfMinions());
        return returnValue;
    }

    public int getAmountOfMinions(){
        if(this.hasMinions()){
            return this.getMinions().monsters.size();
        } else {
            return 0;
        }
    }

    public void summonsChanges(){
        //collection of changes that happen when minions are added or removed
        this.updateSummons();
        this.updateSummonsPositions();
    }

    public void updateSummons(){
        if(this.hasMinions() && this.hasPower("SummonsPower")) {
            this.getPower("SummonsPower").amount = this.getAmountOfMinions();
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SummonsPower(this, this.getAmountOfMinions()),1));
        }
    }


    public void updateSummonsPositions(){
        float startX = this.drawX + this.hb_w + 100;
        float oddY = this.hb_y + this.hb_h + (this.hb_h/2) - 50;
        float evenY = this.hb_y + this.hb_h - 50;

        for(int i = 0; i < this.getAmountOfMinions(); i++){
            AbstractMonster monster = this.getMinions().monsters.get(i);
            monster.drawX = (float)(startX + (monster.hb.width * Math.floor(i/2)));
            if(i % 2 == 0){
                monster.drawY = oddY;
            } else {
                monster.drawY = evenY;
            }
        }
    }
}
