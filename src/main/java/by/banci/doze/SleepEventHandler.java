package by.banci.doze;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;

import static by.banci.doze.Registry.*;

@EventBusSubscriber(modid = "doze")
public class SleepEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void attemptSleep(CanPlayerSleepEvent event) {
        Player player = event.getEntity();
        Level level = event.getLevel();

        if (level.isClientSide()
            || (player.isSecondaryUseActive()
                && !(player.getMainHandItem().isEmpty()))
            || !level.getBlockState(event.getPos()).is(BlockTags.BEDS)
            || (Config.BEDS_EXPLODE.get()
                && level.environmentAttributes().getValue(EnvironmentAttributes.BED_RULE, event.getPos()).explodes())
        ) return;

        player.getPersistentData().putInt("doze.bedTime", (int) level.getDayTime());

        // Dimension Check
        if (!Config.BEDS_EXPLODE.get()
            && (level.dimension() == Level.NETHER)) {
            event.setProblem(DIMENSION_NETHER);
            return;
        } else if (!Config.BEDS_EXPLODE.get()
            && (level.dimension() == Level.END)) {
            event.setProblem(DIMENSION_END);
            return;
        } else if (!Config.BEDS_EXPLODE.get()
            && level.environmentAttributes().getValue(EnvironmentAttributes.BED_RULE, event.getPos()).explodes()) {
            event.setProblem(DIMENSION_MOD);
            return;
        }

        // Roof Check
        if (!Config.SLEEP_OUTSIDE.get()
            && (level.getBlockState(event.getPos()).getBlock() instanceof BedBlock)) {
            int roofCount = 0;
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    for (int y = 1; y < level.getMaxY() - event.getPos().getY(); y++) {
                        BlockState state = level.getBlockState(event.getPos().offset(x, y, z));
                        if (state.isAir()) continue;
                        if (state.getBlock() instanceof LeavesBlock && state.hasProperty(BlockStateProperties.PERSISTENT) && !state.getValue(BlockStateProperties.PERSISTENT)) {
                            continue;
                        }
                        roofCount++;
                        break;
                    }
                }
            }
            if (roofCount <= 5) {
                event.setProblem(OUTSIDE);
                return;
            }
        }

        // Light Check
        if ((level.getMaxLocalRawBrightness(event.getPos()) <= Config.MINIMUM_LIGHT.get())
            && level.getBlockState(event.getPos()).getBlock() instanceof BedBlock) {
            event.setProblem(TOO_DARK);
        }
    }
}

