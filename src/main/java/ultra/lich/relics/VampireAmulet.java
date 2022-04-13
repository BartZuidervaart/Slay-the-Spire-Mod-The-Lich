package ultra.lich.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import ultra.lich.images.ImageLibrary;

public class VampireAmulet extends CustomRelic {

    private static final String ID = "Vampire_Amulet";
    public static final String[] DESCRIPTIONS = new String[] {
            "Whenever you kill an enemy : Heal 2 HP. "
    };
    //private static final int HP_TO_HEAL;

    public VampireAmulet() {
        super(ID, new Texture(ImageLibrary.VAMPIRE_AMULET_RELIC),
                RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
