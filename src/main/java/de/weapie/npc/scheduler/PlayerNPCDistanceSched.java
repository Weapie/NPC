package de.weapie.npc.scheduler;

import de.weapie.npc.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerNPCDistanceSched {

    /**
     * Start scheduler to kick of player from npc
     */
    public static void startDistanceScheduler(double distance, double multiply, double yHeight) {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(), () -> {
            Main.getInstance().getNpcList().forEach(customNPC -> {
                customNPC.getLocation().getWorld().getNearbyEntities(customNPC.getLocation(), distance, distance, distance).stream().filter(entity -> entity instanceof Player).forEach(entity -> {
                    if(entity.getLocation().distance(customNPC.getLocation()) <= distance) {
                        entity.setVelocity(customNPC.getCreatedNPC().getLocation().getDirection().multiply(multiply).setY(yHeight));
                    }
                });
            });
        }, 2, 2);
    }

}
