package by.banci.doze;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue MINIMUM_LIGHT;
    public static final ModConfigSpec.BooleanValue SLEEP_OUTSIDE;
    public static final ModConfigSpec.BooleanValue BEDS_EXPLODE;
    public static final ModConfigSpec.IntValue HEALTH_PER_HOUR;
    public static final ModConfigSpec.IntValue HUNGER_COST;
    public static final ModConfigSpec.IntValue TICK_HOUR;

    static {
        SLEEP_OUTSIDE = BUILDER
                .comment("Toggles whether players can sleep outside.")
                .translation("doze.configuration.sleepOutside")
                .define("sleepOutside", false);

        MINIMUM_LIGHT = BUILDER
                .comment("The minimum ambient light level required for sleep. Set to 0 to disable.")
                .translation("doze.configuration.minimumLight")
                .defineInRange("minimumLight", 7, 0, 15);

        BEDS_EXPLODE = BUILDER
                .comment("Toggles whether beds explode in dimensions like the Nether.")
                .translation("doze.configuration.bedsExplode")
                .define("bedsExplode", true);

        BUILDER.comment("Don't mess with these unless you know what you're doing!")
                .push("advanced");

        HEALTH_PER_HOUR = BUILDER
                .comment("The amount players will heal per hour slept.")
                .translation("doze.configuration.healtPerHour")
                .defineInRange("healthPerHour", 1, 0, 20);

        HUNGER_COST = BUILDER
                .comment("The cost of hunger per health regained during sleep. Set to 0 to disable.")
                .translation("doze.configuration.hungerCost")
                .defineInRange("hungerCost", 1, 0, 20);

        TICK_HOUR = BUILDER
                .comment("If you use a mod that alters time, make sure this setting matches the length (in ticks) of a day.")
                .translation("doze.configuration.tickHour")
                .defineInRange("tickHour", 1000, 0, Integer.MAX_VALUE);

        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();
}