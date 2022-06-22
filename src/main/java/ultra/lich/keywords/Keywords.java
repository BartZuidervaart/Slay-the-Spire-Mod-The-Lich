package ultra.lich.keywords;

import basemod.BaseMod;

public class Keywords {

    public static void getKeywords(){

        String[] summonSickness = {"summoning", "sickness", "Summoning"};

        BaseMod.addKeyword("Summoning sickness",summonSickness, "Target takes damage at the end of its turn.");

        String[] soak = {"Soak", "soak", "soaking", "Soaking"};

        BaseMod.addKeyword("Soak",soak, "When this creature dies, it soaks some of the remaining damage.");

        String[] summon = {"Minion", "minion"};

        BaseMod.addKeyword("Minion",summon, "Summoned creature with #yStats. Damage is redirected towards a random #yMinion. Excess damage carries on.");

        String[] stats = {"Stats", "stats"};

        BaseMod.addKeyword("Stats",stats, "#yAttack / #yDefense / #yHealth");

        String[] controllable = {"Controllable", "controllable"};

        BaseMod.addKeyword("Controllable",controllable, "Chose what action this #yMinion must perform.");

        String[] intent = {"Intent", "intent", "intents", "Intents"};

        BaseMod.addKeyword(intent, "Describes the actions the minion will likely take.");

        String[] explode = {"Explodes", "explodes", "explode", "Explode"};

        BaseMod.addKeyword(explode, "On death, dealing 3 times its attack damage plus this power amount to all enemies.");

        String[] revenant = {"revenant", "Revenant"};

        BaseMod.addKeyword(revenant, "Will focus on a chosen creature until it is dead.");

        String[] sacrifice = {"sacrifice", "Sacrifice", "sacrificed", "Sacrificed"};

        BaseMod.addKeyword(sacrifice, "Kill a #yMinion.");

        String[] summoner = {"summoner", "Summoner"};

        BaseMod.addKeyword(summoner, "The owner of minions.");

        String[] tribute = {"tribute", "Tribute"};

        BaseMod.addKeyword(tribute, "#ySacrifice a #yMinion as part of the cost.");

        String[] deadMarked = {"mark", "marked", "Mark", "Marked"};

        BaseMod.addKeyword(deadMarked, "Targets that are dead marked are favored by #yMinions");

        String[] fleshRot = {"fleshrot", "Fleshrot"};

        BaseMod.addKeyword(fleshRot, "Adds a status card that inflicts #yPoison at the end of your turn.");

        String[] putrid = {"putrid", "Putrid"};

        BaseMod.addKeyword(putrid, "Adds a #yFleshrot at the start of every turn.");

        String[] priority = {"priority", "Priority"};

        BaseMod.addKeyword("Priority",priority, "#yMinions can gain priority for redirected damage.");

        String[] orb = {"orb", "Orb", "Orbs", "orbs"};

        BaseMod.addKeyword(orb, "At death, gives [E] next turn.");

        String[] cannibal = {"cannibal", "Cannibal"};

        BaseMod.addKeyword("Cannibal",cannibal, "Adds a cannibalize card in your hand every turn. Cannibalize sacrifices a minion for the other minions to heal.");

        String[] attack = {"attack", "Attack"};

        BaseMod.addKeyword("Attack",attack, "Value often used to inflict damage but can be used for other actions.");

        String[] defense = {"defense", "Defense"};

        BaseMod.addKeyword("Defense",defense, "Value often used to apply block but can be used for other actions.");
    }

}
