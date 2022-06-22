package ultra.lich.minionactions;

import com.badlogic.gdx.graphics.Texture;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import kobting.friendlyminions.monsters.MinionMove;

public class LichMinionMove extends MinionMove {

    public static final int MOVE_BUTTON_WIDTH = 72;
    public static final int MOVE_BUTTON_HEIGHT = 72;

    public LichMinionMove(String ID, AbstractFriendlyMonster owner, Texture moveImage, String moveDescription, Runnable moveActions) {
        super(ID,owner,moveImage,moveDescription,moveActions);
        this.hb_w = MOVE_BUTTON_WIDTH;
        this.hb_h = MOVE_BUTTON_HEIGHT;
    }
}
