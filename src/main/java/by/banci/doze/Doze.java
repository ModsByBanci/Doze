package by.banci.doze;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;


@Mod(Doze.MODID)
public class Doze {

    public static final String MODID = "doze";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Doze(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    // Custom Sleeping Problems
    public static final Player.BedSleepingProblem OUTSIDE = new Player.BedSleepingProblem(Component.translatable("block.doze.bed.outside"));
    public static final Player.BedSleepingProblem TOO_DARK = new Player.BedSleepingProblem(Component.translatable("block.doze.bed.too_dark"));
    public static final Player.BedSleepingProblem DIMENSION_NETHER = new Player.BedSleepingProblem(Component.translatable("block.doze.bed.dimension_nether"));
    public static final Player.BedSleepingProblem DIMENSION_END = new Player.BedSleepingProblem(Component.translatable("block.doze.bed.dimension_end"));
    public static final Player.BedSleepingProblem DIMENSION_MOD = new Player.BedSleepingProblem(Component.translatable("block.doze.bed.dimension_mod"));

}
