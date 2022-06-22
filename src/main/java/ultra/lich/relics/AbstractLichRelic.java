package ultra.lich.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import ultra.lich.minions.AbstractLichMinion;

public abstract class AbstractLichRelic extends CustomRelic {
    public AbstractLichRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }

    public AbstractLichRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx) {
        super(id, texture, outline, tier, sfx);
    }

    public AbstractLichRelic(String id, String imgName, RelicTier tier, LandingSound sfx) {
        super(id, imgName, tier, sfx);
    }

    public void onSacrifice(AbstractMonster monster){ }

    public void obtainedFleshRotPoison(int amount){}

    public void addedMinion(AbstractFriendlyMonster minion){}

    public void minionDies(AbstractFriendlyMonster minion){}
}
