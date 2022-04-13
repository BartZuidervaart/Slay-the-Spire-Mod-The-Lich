package ultra.lich;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ultra.lich.cards.BoneArmory;
import ultra.lich.cards.DefendWhite;
import ultra.lich.cards.StrikeWhite;
import ultra.lich.eNums.LichCardColorEnum;
import ultra.lich.eNums.LichEnum;
import ultra.lich.images.ImageLibrary;
import ultra.lich.player.LichClass;
import ultra.lich.relics.VampireAmulet;

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
                LichCardColorEnum.LICH_COLOR,
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
        Texture badgeTexture = new Texture("img/LichBadge.png");
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }

    @Override
    public void receiveEditCharacters(){
        BaseMod.addCharacter(new LichClass(LichEnum.LICH_CLASS), ImageLibrary.NECROMANCER_BUTTON, ImageLibrary.NECROMANCER_PORTRAIT, LichEnum.LICH_CLASS);
    }

    @Override
    public void receiveEditCards(){
        BaseMod.addCard(new DefendWhite());
        BaseMod.addCard(new StrikeWhite());
        BaseMod.addCard(new BoneArmory());
    }

    @Override
    public void receiveEditRelics() {
        RelicLibrary.add(new VampireAmulet());
    }

    @Override
    public void receiveEditKeywords() {

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
