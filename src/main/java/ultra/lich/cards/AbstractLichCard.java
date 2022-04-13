package ultra.lich.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.eNums.LichCardColorEnum;

public abstract class AbstractLichCard extends CustomCard {

    public AbstractLichCard(String id, String name, String img, int cost, String rawDescrition, CardType type, CardRarity rarity, CardTarget target){
        super(id,name, img, cost, rawDescrition, type, LichCardColorEnum.LICH_COLOR, rarity, target);
    }

}
