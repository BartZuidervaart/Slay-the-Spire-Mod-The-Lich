package ultra.lich;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.cards.*;
import ultra.lich.cards.status.CannibalizeCard;
import ultra.lich.cards.status.FleshRot;
import ultra.lich.eNums.LichCardEnum;
import ultra.lich.eNums.LichEnum;
import ultra.lich.minionactions.MinionTargeting;
import ultra.lich.images.ImageLibrary;
import ultra.lich.keywords.Keywords;
import ultra.lich.player.LichClass;
import ultra.lich.relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class TheLich implements PostInitializeSubscriber, EditCharactersSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber {

    public static final Logger LOGGER = LogManager.getLogger(TheLich.class.getName());

    public static final String MODNAME = "The Lich";
    public static final String AUTHOR = "An awful person";
    public static final String DESCRIPTION = "v1.0.0 dipping my toes in the water";

    public static final Color NECROCOLOR = CardHelper.getColor(0f, 150f, 136f);



    public TheLich(){
        // TODO: make the mod
        BaseMod.addColor(
                LichCardEnum.LICH_COLOR,
                NECROCOLOR,
                NECROCOLOR,
                NECROCOLOR,
                NECROCOLOR,
                NECROCOLOR,
                NECROCOLOR,
                NECROCOLOR,
                ImageLibrary.ATTACK_TEMPLATE, // String attackBg,
                ImageLibrary.SKILL_TEMPLATE, //String skillBg,
                ImageLibrary.POWER_TEMPLATE, //String powerBg,
                ImageLibrary.ENERGY_ORB_TEMPLATE, //String energyOrb,
                ImageLibrary.ATTACK_TEMPLATE_PORTRAIT, //String attackBgPortrait,
                ImageLibrary.SKILL_TEMPLATE_PORTRAIT, //String skillBgPortrait,
                ImageLibrary.POWER_TEMPLATE_PORTRAIT, //String powerBgPortrait,
                ImageLibrary.ENERGY_ORB_TEMPLATE_PORTRAIT //String energyOrbPortrait
                );
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        LOGGER.info("Initiating 'The Lich'...");
        new TheLich();
        LOGGER.info("done.");
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = ImageMaster.loadImage("img/LichBadge.png");
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;

        CustomTargeting.registerCustomTargeting(MinionTargeting.MINION, new MinionTargeting());
    }

    @Override
    public void receiveEditCharacters(){
        BaseMod.addCharacter(new LichClass(LichEnum.LICH_CLASS), ImageLibrary.LICH_BUTTON, ImageLibrary.NECROMANCER_PORTRAIT, LichEnum.LICH_CLASS);
    }

    @Override
    public void receiveEditCards(){
        BaseMod.addCard(new ZombieCard());
        BaseMod.addCard(new ClawStrike());
        BaseMod.addCard(new Resilience());
        BaseMod.addCard(new ShelteredCard());
        BaseMod.addCard(new FrontlineCard());
        BaseMod.addCard(new AggressiveCard());
        BaseMod.addCard(new WraithCard());
        BaseMod.addCard(new GravediggerCard());
        BaseMod.addCard(new LegionCard());
        BaseMod.addCard(new SwiftReinforcementsCard());
        BaseMod.addCard(new OverdriveCard());
        BaseMod.addCard(new FlameSkullCard());
        BaseMod.addCard(new ClawCard());
        BaseMod.addCard(new SkeletonSoldierCard());
        BaseMod.addCard(new SkeletonPikemanCard());
        BaseMod.addCard(new MarkDeadCard());
        BaseMod.addCard(new FleshRot());
        BaseMod.addCard(new PlagueCard());
        BaseMod.addCard(new SkeletonSentinelCard());
        BaseMod.addCard(new RitualSacrificeCard());
        BaseMod.addCard(new EmergencyReservesCard());
        BaseMod.addCard(new WeaponizeCard());
        BaseMod.addCard(new ArmoryCard());
        BaseMod.addCard(new ProtectionSpellCard());
        BaseMod.addCard(new KnowledgeFromTheGraveCard());
        BaseMod.addCard(new LastRemorseCard());
        BaseMod.addCard(new PestilenceCard());
        BaseMod.addCard(new CannibalizeCard());
        BaseMod.addCard(new BloodwellCard());
        BaseMod.addCard(new VigorCard());
        BaseMod.addCard(new SpecterCard());
        BaseMod.addCard(new GhostCard());
        BaseMod.addCard(new InfestationCard());
        BaseMod.addCard(new PutridStrike());
        BaseMod.addCard(new DetonateCard());
        BaseMod.addCard(new WispCard());
        BaseMod.addCard(new BonewallCard());
        BaseMod.addCard(new BoneHorrorCard());
        BaseMod.addCard(new BloatedZombieCard());
        BaseMod.addCard(new GhoulCard());
        BaseMod.addCard(new SoulFeastCard());
        BaseMod.addCard(new WightCard());
        BaseMod.addCard(new HordeCard());
        BaseMod.addCard(new SoulLeechCard());
        BaseMod.addCard(new ElixirCard());
    }

    @Override
    public void receiveEditRelics() {
        RelicLibrary.add(new SoulJarRelic());
        RelicLibrary.add(new ZombieHandRelic());
        RelicLibrary.add(new CeremonialDaggerRelic());
        RelicLibrary.add(new FalseIdolRelic());
        RelicLibrary.add(new CommandingHandRelic());
        RelicLibrary.add(new StaffOfContaminationRelic());
        RelicLibrary.add(new StrongJawRelic());
        RelicLibrary.add(new SummoningFocusRelic());
    }

    @Override
    public void receiveEditKeywords() {
        Keywords.getKeywords();
    }

    @Override
    public void receiveEditStrings() {
        // RelicStrings
        String relicStrings = Gdx.files.internal("localization/NecroMod-RelicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // CardStrings
        String cardStrings = Gdx.files.internal("localization/NecroMod-CardStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
    }
}
