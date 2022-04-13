package ultra.lich.keywords;

import basemod.BaseMod;

public class Keywords {

    public static void getKeywords(){

        String[] summonSickness = {"Summon sickness", "summon sickness", "summoning sickness", "Summoning sickness"};

        BaseMod.addKeyword(summonSickness, "Target takes damage at the end of its turn.");

        String[] soak = {"Soak", "soak", "soaking", "Soaking"};

        BaseMod.addKeyword(soak, "When this creature dies, it soaks some of the remaining damage.");

        String[] summon = {"Minion", "minion"};

        BaseMod.addKeyword(summon, "A summoned creature. Minions have their own actions. Damage that would normally hit the summoner is passed on to minions instead. If the damage dealt to a minion is greater than its remaining health, the remaining damage will be passed on to another minion or eventually the summoner.");

        String[] stats = {"Stats", "stats"};

        BaseMod.addKeyword(stats, "A summary of statistics of a summoned creature. The order of the statistics are: Attack / Defense / Health");

        String[] controllable = {"Controllable", "controllable"};

        BaseMod.addKeyword(controllable, "The actions of this creature are controllable by the summoner.");
    }

}
