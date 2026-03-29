package by.banci.doze;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;

@EventBusSubscriber(modid = Doze.MODID)
public class SleepRegenHandler {

    @SubscribeEvent
    public static void onWakeUp(PlayerWakeUpEvent event) {
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        if (level.isClientSide()) return;

        long hoursSlept = ((level.getDayTime() - player.getPersistentData().getLong("doze.bedTime").orElse(0L)) / Config.TICK_HOUR.get());

        for (long i = 1; i <= hoursSlept; i++) {
            if ((player.getHealth() == player.getMaxHealth())
            || (player.getFoodData().getFoodLevel() < 6)
            ) return;

            player.heal(Config.HEALTH_PER_HOUR.get());
            player.getFoodData().addExhaustion(Config.HUNGER_COST.get());
        }
    }
}