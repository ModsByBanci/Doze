package by.banci.doze;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue SLEEP_OUTSIDE;
    public static final ModConfigSpec.IntValue MINIMUM_LIGHT;
    public static final ModConfigSpec.IntValue HEALTH_PER_HOUR;
    public static final ModConfigSpec.IntValue HUNGER_COST;
    public static final ModConfigSpec.IntValue CLEAR_EFFECTS;
    public static final ModConfigSpec.IntValue TICK_HOUR;

    static {
        SLEEP_OUTSIDE = BUILDER
                .comment("Whether players are allowed to sleep outside.")
                .translation("doze.configuration.sleepOutside")
                .define("sleepOutside", false);

        MINIMUM_LIGHT = BUILDER
                .comment("Minimum light level required for sleep. Set to 0 to disable.")
                .translation("doze.configuration.minimumLight")
                .defineInRange("minimumLight", 7, 0, 15);

        HEALTH_PER_HOUR = BUILDER
                .comment("Amount players will heal per hour slept. Set to 0 to disable.")
                .translation("doze.configuration.healthPerHour")
                .defineInRange("healthPerHour", 1, 0, 3);

        CLEAR_EFFECTS = BUILDER
                .comment("Hours of sleep required to clear potion effects. Set to 0 to disable.")
                .translation("doze.configuration.clearEffects")
                .defineInRange("clearEffects", 6, 0, 24);

        HUNGER_COST = BUILDER
                .comment("Cost of hunger per hour healed during sleep. Set to 0 to disable.")
                .translation("doze.configuration.hungerCost")
                .defineInRange("hungerCost", 1, 0, 3);

        BUILDER.comment("Don't mess with these unless you know what you're doing!")
                .push("advanced");

        TICK_HOUR = BUILDER
                .comment("When using mods that alter time, this setting should match the relative length of one tick hour.")
                .translation("doze.configuration.tickHour")
                .defineInRange("tickHour", 1000, 0, Integer.MAX_VALUE);

        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();
}