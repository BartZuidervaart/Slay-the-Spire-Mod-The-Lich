package ultra.lich.minionactions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.monsters.MinionMove;
import kobting.friendlyminions.monsters.MinionMoveGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class LichMinionMoveGroup extends MinionMoveGroup {

    public AbstractMonster owner;

    public static final Logger LOGGER = LogManager.getLogger(LichMinionMoveGroup.class.getName());

    public LichMinionMoveGroup(AbstractMonster owner, float xStart, float yStart) {
        super(xStart, yStart);
        this.owner = owner;
    }

    @Override
    public void updatePositions() {
        if(this.owner != null){
            this.xStart = this.owner.drawX;
            this.yStart = this.owner.drawY;
        }

        float fullLength = (this.getMoves().size() * LichMinionMove.MOVE_BUTTON_WIDTH * Settings.scale);
        this.xStart = xStart - (fullLength/2);


        int currentIndex = 0;

        for(Iterator var2 = this.getMoves().iterator(); var2.hasNext(); ++currentIndex) {
            LichMinionMove move = (LichMinionMove)var2.next();
            move.getHitbox().x = this.xStart + LichMinionMove.MOVE_BUTTON_WIDTH * ((float)currentIndex * Settings.scale);
            move.getHitbox().y = this.yStart - (LichMinionMove.MOVE_BUTTON_HEIGHT * Settings.scale)/2;
            move.getHitbox().width = LichMinionMove.MOVE_BUTTON_WIDTH * Settings.scale;
            move.getHitbox().height = LichMinionMove.MOVE_BUTTON_HEIGHT * Settings.scale;
            move.setX(this.xStart + LichMinionMove.MOVE_BUTTON_WIDTH * ((float)currentIndex * Settings.scale));
            move.setY(this.yStart + LichMinionMove.MOVE_BUTTON_HEIGHT * Settings.scale);
        }
    }

    @Override
    protected void drawMoveImage(MinionMove move, SpriteBatch sb, Texture moveImage, int currentIndex) {
        sb.draw(moveImage, move.getHitbox().x, move.getHitbox().y, 0, 0, LichMinionMove.MOVE_BUTTON_WIDTH, LichMinionMove.MOVE_BUTTON_HEIGHT, 1f, 1f, 0.0F, 0, 0, moveImage.getWidth(), moveImage.getHeight(), false, false);
    }
}
