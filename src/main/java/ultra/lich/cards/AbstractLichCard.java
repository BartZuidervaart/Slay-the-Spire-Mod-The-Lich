package ultra.lich.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ultra.lich.actions.SacrificeMinionAction;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.powers.SummonerPower;

import java.util.ArrayList;

public abstract class AbstractLichCard extends CustomCard {

    public int sacrifice = 0;

    public String upgradeDescription;

    public AbstractLichCard(String id, String name, String img, int cost, String rawDescrition, String upgradeDescription, CardType type, CardRarity rarity, CardTarget target){
        super(id,name, img, cost, rawDescrition, type, LichCardEnum.LICH_COLOR, rarity, target);
        this.upgradeDescription = upgradeDescription;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = this.upgradeDescription;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer var1, AbstractMonster var2) {
        sacrifice(var1, null, true);
    }

    @Override
    public boolean canUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean canUse = super.canUse(abstractPlayer,abstractMonster);
        if(abstractPlayer.hasPower(SummonerPower.POWER_ID)){
            if(!allowedToSacrifice((SummonerPower)abstractPlayer.getPower(SummonerPower.POWER_ID))){
                canUse = false;
            }
        }
        return canUse;
    }

    protected void sacrifice(AbstractPlayer abstractPlayer, ArrayList<AbstractMonster> abstractMonsters, boolean pickLastMinions){
        if(this.sacrifice > 0){
            if (abstractPlayer.hasPower(SummonerPower.POWER_ID)) {
                SummonerPower caster = (SummonerPower) abstractPlayer.getPower(SummonerPower.POWER_ID);
                if(abstractMonsters != null){
                    abstractMonsters.forEach(monster -> addToBot(new SacrificeMinionAction(caster, monster)));
                } else if (pickLastMinions){
                    int lastMonster = caster.minions.monsters.size()-1;
                    for(int i = lastMonster; i > lastMonster-this.sacrifice; i--){
                        addToBot(new SacrificeMinionAction(caster,caster.minions.monsters.get(i)));
                    }
                } else {
                    for(int i = 0; i < this.sacrifice; i++){
                        addToBot(new SacrificeMinionAction(caster,caster.minions.monsters.get(i)));
                    }
                }
            }
        }
    }

    protected boolean allowedToSacrifice(SummonerPower caster){
        if(this.sacrifice > 0) {
            if (caster.hasMinions() && caster.getAmountOfMinions() >= this.sacrifice) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

}
