package ultra.lich.minions;

import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import ultra.lich.minionactions.LichMinionMove;
import ultra.lich.minionactions.LichMinionMoveGroup;
import ultra.lich.powers.SummonerPower;

import java.util.ArrayList;

public abstract class AbstractControlableLichMinion extends AbstractLichMinion{

    protected boolean didAction = false;

    public AbstractControlableLichMinion(String name, String id, int maxHealth, SpineAnimation animation, String animationName, AbstractCreature summoner, int baseAttack, int baseDefense) {
        super(name, id, maxHealth, animation, animationName, summoner, baseAttack, baseDefense);
        this.moves = new LichMinionMoveGroup(this,this.drawX, this.drawY);
        this.addMoves();
    }

    public AbstractControlableLichMinion(String name, String id, int maxHealth, String imgUrl, AbstractCreature summoner, int baseAttack, int baseDefense) {
        super(name, id, maxHealth, imgUrl, summoner, baseAttack, baseDefense);
        this.moves = new LichMinionMoveGroup(this,this.drawX, this.drawY);
        this.addMoves();
    }

    public AbstractControlableLichMinion(String name, String id, int maxHealth, String imgUrl, AbstractCreature summoner, Texture[] attackIntents) {
        super(name, id, maxHealth, imgUrl, summoner, attackIntents);
        this.moves = new LichMinionMoveGroup(this,this.drawX, this.drawY);
        this.addMoves();
    }

    @Override
    public void takeTurn(){
        if(!didAction){
            if(this.moves.getMoves().size() > 0){
                this.noTurnTaken();
            }
        }
    }

    public void addMove(LichMinionMove move){
        this.moves.addMove(move);
    }

    public void addMoves(){
        this.moves.clearMoves();
        this.setMoves().forEach(move -> this.addMove(move));
    }

    public abstract ArrayList<LichMinionMove> setMoves();

    public abstract void noTurnTaken();

    @Override
    public void applyStartOfTurnPowers(){
        this.didAction = false;
        super.applyStartOfTurnPowers();
    }
}
