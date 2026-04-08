package by.banci.doze;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;

@EventBusSubscriber(modid = Doze.MODID)
public class WakeHandler {

    @SubscribeEvent
    public static void onWake(PlayerWakeUpEvent event) {
        Player player = event.getEntity();
        Level level = event.getEntity().level();

        if (level.isClientSide()) return;

        long hoursSlept = ((level.getDefaultClockTime() - player.getPersistentData().getLong("doze.bedTime").orElse(0L)) / Config.TICK_HOUR.get());

        if (Config.CLEAR_EFFECTS.get() !=0
                && hoursSlept >= Config.CLEAR_EFFECTS.get()) {

            player.removeAllEffects();

        }

        for (long i = 1; i <= hoursSlept; i++) {
            if ((player.getHealth() != player.getMaxHealth())
                    && (player.getFoodData().getFoodLevel() > 6)) {

                player.heal(Config.HEALTH_PER_HOUR.get());
                player.getFoodData().addExhaustion(Config.HUNGER_COST.get());

            } else return;
        }
    }
}