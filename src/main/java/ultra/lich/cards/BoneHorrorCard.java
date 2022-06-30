package ultra.lich.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SummonMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.minions.BoneHorrorMinion;
import ultra.lich.powers.SummonerPower;

public class BoneHorrorCard extends AbstractLichCard {

    public static final String ID = "TheLich:BoneHorrorCard";
    public static final	String NAME = "Bone Horror";
    public static final	String DESCRIPTION = "Tribute 4. Summons a bone horror with stats 20/20/40. Controllable. Cannibal. 3 Putrid.";
    public static final String UPGRADE_DESCRIPTION = "Tribute 3. Summons a bone horror with stats 20/20/40. Controllable. Cannibal. 3 Putrid." ;
    private static final int COST = 3;

    public BoneHorrorCard() {
        super(ID, NAME, ImageLibrary.SKELETON_SOLDIER, COST, DESCRIPTION, UPGRADE_DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCard.CardRarity.RARE, CardTarget.SELF);
        this.sacrifice = 4;
        tags.add(LichCardEnum.SUMMON);
    }

    public AbstractCard makeCopy() {
        return new BoneHorrorCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        super.use(p,abstractMonster);
        addToBot(new SummonMinionAction(p, new BoneHorrorMinion(p,40,20,20,3)));
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.sacrifice = 3;
    }



}