package ultra.lich.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.AbstractControlableLichMinion;
import ultra.lich.minions.AbstractLichMinion;
import ultra.lich.relics.AbstractLichRelic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

public class SummonerPower extends AbstractLichPower {

    public static final Logger LOGGER = LogManager.getLogger(SummonerPower.class.getName());

    public static final String DESCRIPTION =  "Can have #b4 #yMinions. Summoning more #yMinions will #ySacrifice the first #yMinions.";

    public static final String POWER_ID = "TheLich:SummonerPower";

    public MonsterGroup minions;
    private AbstractFriendlyMonster[] p_minions;

    public SummonerPower(AbstractCreature owner, int amount){
        super();
        this.name = "Summoner";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.SUMMONER_POWER_ICON_32),0,0,32,32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ImageLibrary.SUMMONER_POWER_ICON),0,0,128,128);
        this.clearMinions();
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTION;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.minions.monsters.forEach(monster -> {
            if (monster instanceof AbstractFriendlyMonster) {
                if (!((AbstractFriendlyMonster) monster).hasTakenTurn()) {
                    monster.takeTurn();
                }
            }
        });
        this.flushDeadMinions();
        super.atEndOfTurn(isPlayer);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        this.minions.monsters.forEach((minion) -> {
            minion.applyStartOfTurnPostDrawPowers();
        });
        this.flushDeadMinions();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        this.minions.monsters.forEach((minion) -> {
            minion.applyStartOfTurnPowers();
        });
        this.minions.monsters.forEach((minion) -> {
            minion.loseBlock();
        });
        this.flushDeadMinions();
    }

    @Override
    public void duringTurn(){
        super.duringTurn();
        this.minions.monsters.forEach((minion) -> {
            minion.applyTurnPowers();
        });
        this.flushDeadMinions();
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer){
        this.minions.monsters.forEach((minion) -> {
            minion.applyEndOfTurnTriggers();
        });
        this.minions.monsters.forEach((minion) -> {
            minion.powers.forEach((power) -> {
                power.atEndOfRound();
            });
        });
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        this.flushDeadMinions();

        if (AbstractDungeon.getCurrRoom() != null) {
            switch(AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    this.minions.update();
            }
        }

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

        this.minions.monsters.forEach((minion) -> {
            minion.updatePowers();
        });
    }

    public void flushDeadMinions() {
        Iterator<AbstractMonster> minions = this.minions.monsters.iterator();

        while (minions.hasNext()) {
            if (minions.next().isDead) {
                minions.remove();
                this.summonsChanges();
            }
        }
    }

    public void sacrifice(AbstractMonster abstractMonster) {
        LOGGER.info("sacrificed " + abstractMonster.name + " for our lord and savior the spire");
        this.owner.powers.forEach(power -> {
            if (power instanceof AbstractLichPower) {
                ((AbstractLichPower) power).onSacrifice(abstractMonster);
            }
        });
        this.minions.monsters.forEach(monster -> {
            monster.powers.forEach(power -> {
                if (power instanceof AbstractLichPower) {
                    ((AbstractLichPower) power).onSacrifice(abstractMonster);
                }
            });
        });
        if(this.owner instanceof AbstractPlayer){
            AbstractPlayer player = (AbstractPlayer)this.owner;
            player.relics.forEach(relic -> {
                if (relic instanceof AbstractLichRelic) {
                    ((AbstractLichRelic) relic).onSacrifice(abstractMonster);
                }
            });
        }
        abstractMonster.die();
        abstractMonster.isDead = true;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        LOGGER.info(this.owner + " took " + damageAmount + " damage.");
        AbstractMonster target = this.getPriorityTarget();
        if (target != null && damageAmount > 0) {
            LOGGER.info("" + this.owner + damageAmount + " damage goes to " + target + " instead");
            target.damage(info);
            return 0;
        } else {
            return damageAmount;
        }
    }

    public void addMinion(AbstractFriendlyMonster minion) {
        this.flash();
        if (this.hasMinions() && this.getAmountOfMinions() >= this.amount) {
            this.sacrifice(this.minions.monsters.get(0)); //replace oldest with newest
            this.flushDeadMinions();
        }

        minion.init();
        minion.usePreBattleAction();
        minion.showHealthBar();
        this.minions.add(minion);

        this.summonsChanges();

        if (minion instanceof AbstractLichMinion) {
            //can not apply powers in the constructor. The lich class will rearrange the minions locations. Apply powers after.
            ((AbstractLichMinion) minion).applyStartPowers();
        }
        this.owner.powers.forEach(power -> {
            if (power instanceof AbstractLichPower) {
                ((AbstractLichPower) power).addedMinion(minion);
            }
        });

        if(this.owner instanceof AbstractPlayer){
            AbstractPlayer player = (AbstractPlayer)this.owner;
            player.relics.forEach(relic -> {
                if (relic instanceof AbstractLichRelic) {
                    ((AbstractLichRelic) relic).addedMinion(minion);
                }
            });
        }
        this.minions.monsters.forEach(monster -> {
            monster.powers.forEach(power -> {
                if (power instanceof AbstractLichPower) {
                    ((AbstractLichPower) power).addedMinion(minion);
                }
            });
        });
    }

    public boolean hasMinions() {
        return this.minions.monsters.stream().filter(monster -> !monster.isDead).toArray().length > 0;
    }

    public int getAmountOfMinions() {
        if (this.hasMinions()) {
            return this.minions.monsters.stream().filter(monster -> !monster.isDead).toArray().length;
        } else {
            return 0;
        }
    }

    public void summonsChanges() {
        //collection of changes that happen when minions are added or removed
        this.updateSummonsPositions();
    }


    public void updateSummonsPositions() {
        float startX = this.owner.drawX + this.owner.hb_w + 100;
        float oddY = this.owner.hb_y + this.owner.hb_h + (this.owner.hb_h / 2);
        float evenY = this.owner.hb_y + this.owner.hb_h;

        for (int i = 0; i < this.getAmountOfMinions(); i++) {
            AbstractMonster monster = this.minions.monsters.get(i);
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

    public AbstractMonster getPriorityTarget() {
        ArrayList<AbstractMonster> allMonsters = this.minions.monsters;
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

    public void clearMinions() {
        this.p_minions = new AbstractFriendlyMonster[this.amount];
        this.minions = new MonsterGroup(this.p_minions);
        this.minions.monsters.removeIf(Objects::isNull);
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb,x,y,c);

        //Render minions
        if (AbstractDungeon.getCurrRoom() != null) {
            switch(AbstractDungeon.getCurrRoom().phase) {
                case COMBAT:
                    this.minions.render(sb);
            }
        }
    }

    @Override
    public void onVictory(){
        super.onVictory();
        this.clearMinions();
    }
}
