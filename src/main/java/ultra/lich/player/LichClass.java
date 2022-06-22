package ultra.lich.player;

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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.characters.CustomCharSelectInfo;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
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
import ultra.lich.minions.AbstractControlableLichMinion;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.powers.AbstractLichPower;
import ultra.lich.powers.HighPriorityTarget;
import ultra.lich.powers.LowPriorityTarget;
import ultra.lich.powers.SummonerPower;
import ultra.lich.relics.AbstractLichRelic;
import ultra.lich.relics.SoulJarRelic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class LichClass extends AbstractPlayerWithMinions {

    public static final Logger LOGGER = LogManager.getLogger(LichClass.class.getName());

    private static final int ENERGY_PER_TURN = 3;

    private static final String NAME = "The Lich";

    public static final SpineAnimation ANIMATION = new SpineAnimation("img/Lich.atlas", "img/Lich.json", 3f);

    public LichClass(PlayerClass setClass) {
        super(NAME, setClass, ImageLibrary.ORB_TEXTURES, "img/char/necromancer/orb/vfx.png", ANIMATION);
        this.energyOrb = new AnimatedEnergyOrb(ImageLibrary.ORB_ATLAS,ImageLibrary.ORB_JSON, ImageLibrary.ORB_ANIMATION_PASSIVE,ImageLibrary.ORB_ANIMATION_ACTIVE,ImageLibrary.ORB_TEXTURES,"img/char/necromancer/orb/vfx.png");

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
        return getInfo();
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
    public CustomCharSelectInfo getInfo() {
        return new CustomCharSelectInfo(LichClass.NAME, "An old man doomed to walk the spire dead and alone.", 30, 30, 0, 4, 99, 5, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public void applyStartOfCombatPreDrawLogic() {
        drawX = 200f;
        super.applyStartOfCombatPreDrawLogic();
    }

    @Override
    public boolean addMinion(AbstractFriendlyMonster minion) {
        if (this.hasMinions() && this.getAmountOfMinions() >= this.getMaxMinions()) {
            this.sacrifice(this.getMinions().monsters.get(0)); //replace oldest with newest
            this.flushDeadMinions();
        }
        boolean returnValue = super.addMinion(minion);
        this.summonsChanges();
        if (minion instanceof AbstractLichMinion) {
            //can not apply powers in the constructor. The lich class will rearrange the minions locations. Apply powers after.
            ((AbstractLichMinion) minion).applyStartPowers();
        }
        this.powers.forEach(power -> {
            if (power instanceof AbstractLichPower) {
                ((AbstractLichPower) power).addedMinion(minion);
            }
        });
        this.relics.forEach(relic -> {
            if (relic instanceof AbstractLichRelic) {
                ((AbstractLichRelic) relic).addedMinion(minion);
            }
        });
        this.getMinions().monsters.forEach(monster -> {
            monster.powers.forEach(power -> {
                if (power instanceof AbstractLichPower) {
                    ((AbstractLichPower) power).addedMinion(minion);
                }
            });
        });
        return returnValue;
    }

    @Override
    public boolean hasMinions() {
        return this.getMinions().monsters.stream().filter(monster -> !monster.isDead).toArray().length > 0;
    }

    //After STSFriendlyMinions is updated
    /*
    @Override
    public void removeMinion(Iterator<AbstractMonster> minions){
        super.removeMinion(minions);
        this.summonsChanges();
    }
    */

    @Override
    public boolean removeMinion(AbstractFriendlyMonster minion) {
        //boolean returnValue = super.removeMinion(minion);
        return false; // do not remove minion
        //return returnValue;
    }

    public int getAmountOfMinions() {
        if (this.hasMinions()) {
            return this.getMinions().monsters.stream().filter(monster -> !monster.isDead).toArray().length;
        } else {
            return 0;
        }
    }

    public void summonsChanges() {
        //collection of changes that happen when minions are added or removed
        this.updateSummonsPositions();
    }


    public void updateSummonsPositions() {
        float startX = this.drawX + this.hb_w + 100;
        float oddY = this.hb_y + this.hb_h + (this.hb_h / 2);
        float evenY = this.hb_y + this.hb_h;

        for (int i = 0; i < this.getAmountOfMinions(); i++) {
            AbstractMonster monster = this.getMinions().monsters.get(i);
            monster.drawX = (float) (startX + ((monster.hb.width + 60f) * Math.floor(i / 2)));
            if (i % 2 == 0) {
                monster.drawY = oddY;
            } else {
                monster.drawY = evenY;
            }

            //if the monster has minion moves, update their button positions.
            if (monster instanceof AbstractControlableLichMinion) {
                AbstractControlableLichMinion controllableMinion = (AbstractControlableLichMinion) monster;
                controllableMinion.getMoves().updatePositions();
            }
        }
    }

    //sub optimal but atleast no crashes

    @Override
    public void applyEndOfTurnTriggers() {
        this.getMinions().monsters.forEach(monster -> {
            if (monster instanceof AbstractFriendlyMonster) {
                if (!((AbstractFriendlyMonster) monster).hasTakenTurn()) {
                    monster.takeTurn();
                }
            }
        });
        this.flushDeadMinions();
        super.applyEndOfTurnTriggers();
    }

    @Override
    public void applyStartOfTurnPostDrawPowers() {
        super.applyStartOfTurnPostDrawPowers();
        this.flushDeadMinions();
    }

    @Override
    public void applyStartOfTurnPowers() {
        if (!this.hasPower("SummonerPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SummonerPower(this), 1));
        }
        super.applyStartOfTurnPowers();
        this.flushDeadMinions();
    }

    @Override
    public void applyTurnPowers() {
        super.applyTurnPowers();
        this.flushDeadMinions();
    }

    @Override
    public void updatePowers() {
        super.updatePowers();
        this.flushDeadMinions();
    }

    @Override
    public void update() {
        super.update();

        this.minions.monsters.forEach(monster -> {
            if (this.minions.hoveredMonster != monster && this.minions.hoveredMonster != null) {
                //change alpha
                monster.hbAlpha = 0.2f;
                monster.reticleAlpha = 0.2f;
                if(monster instanceof AbstractLichMinion){
                    ((AbstractLichMinion) monster).setAlpha(0.2f);
                }
            } else {
                monster.hbAlpha = 1f;
                monster.reticleAlpha = 1f;
                if(monster instanceof AbstractLichMinion){
                    ((AbstractLichMinion) monster).setAlpha(1f);
                }
            }
        });
    }

    public void flushDeadMinions() {
        Iterator<AbstractMonster> minions = this.getMinions().monsters.iterator();

        while (minions.hasNext()) {
            if (minions.next().isDead) {
                minions.remove();
                this.summonsChanges();
            }
        }
    }

    public void sacrifice(AbstractMonster abstractMonster) {
        LOGGER.info("sacrificed " + abstractMonster.name + " for our lord and savior the spire");
        this.powers.forEach(power -> {
            if (power instanceof AbstractLichPower) {
                ((AbstractLichPower) power).onSacrifice(abstractMonster);
            }
        });
        this.getMinions().monsters.forEach(monster -> {
            monster.powers.forEach(power -> {
                if (power instanceof AbstractLichPower) {
                    ((AbstractLichPower) power).onSacrifice(abstractMonster);
                }
            });
        });
        this.relics.forEach(relic -> {
            if (relic instanceof AbstractLichRelic) {
                ((AbstractLichRelic) relic).onSacrifice(abstractMonster);
            }
        });
        abstractMonster.die();
    }

    public AbstractMonster getPriorityTarget() {
        ArrayList<AbstractMonster> allMonsters = this.getMinions().monsters;
        ArrayList<AbstractMonster> livingMonsters = new ArrayList<>(allMonsters.stream().filter(monster -> !monster.isDead).collect(Collectors.toList()));
        ArrayList<AbstractMonster> highPriorityMonsters = new ArrayList<>(livingMonsters.stream().filter(monster -> monster.hasPower(HighPriorityTarget.POWER_ID)).collect(Collectors.toList()));
        ArrayList<AbstractMonster> lowPriorityMonsters = new ArrayList<>(livingMonsters.stream().filter(monster -> monster.hasPower(LowPriorityTarget.POWER_ID)).collect(Collectors.toList()));
        ArrayList<AbstractMonster> noPriorityMonsters = new ArrayList<>(livingMonsters.stream().filter(monster -> !monster.hasPower(LowPriorityTarget.POWER_ID) && !monster.hasPower(HighPriorityTarget.POWER_ID)).collect(Collectors.toList()));
        if (highPriorityMonsters.size() > 0) {
            return highPriorityMonsters.get((int) Math.floor(Math.random() * highPriorityMonsters.size()));
        } else if (noPriorityMonsters.size() > 0) {
            return noPriorityMonsters.get((int) Math.floor(Math.random() * noPriorityMonsters.size()));
        } else if (lowPriorityMonsters.size() > 0) {
            return lowPriorityMonsters.get((int) Math.floor(Math.random() * lowPriorityMonsters.size()));
        } else {
            return null;
        }
    }

}
