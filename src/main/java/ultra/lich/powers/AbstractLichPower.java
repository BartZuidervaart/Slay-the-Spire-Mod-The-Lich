package ultra.lich.powers;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import ultra.lich.minions.AbstractLichMinion;

public abstract class AbstractLichPower extends AbstractPower {

    public void onSacrifice(AbstractMonster monster){};

    public void obtainedFleshRotPoison(int amount){}

    public void addedMinion(AbstractFriendlyMonster minion){}

    public void minionDies(AbstractFriendlyMonster minion){}
}
