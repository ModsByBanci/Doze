package by.banci.doze;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = "doze")
public class Registry {

    public static final Player.BedSleepingProblem OUTSIDE = new Player.BedSleepingProblem(Component.translatable("doze.block.minecraft.bed.outside"));
    public static final Player.BedSleepingProblem TOO_DARK = new Player.BedSleepingProblem(Component.translatable("doze.block.minecraft.bed.too_dark"));
    public static final Player.BedSleepingProblem DIMENSION_NETHER = new Player.BedSleepingProblem(Component.translatable("doze.block.minecraft.bed.dimension_nether"));
    public static final Player.BedSleepingProblem DIMENSION_END = new Player.BedSleepingProblem(Component.translatable("doze.block.minecraft.bed.dimension_end"));
    public static final Player.BedSleepingProblem DIMENSION_MOD = new Player.BedSleepingProblem(Component.translatable("doze.block.minecraft.bed.dimension_mod"));

}
