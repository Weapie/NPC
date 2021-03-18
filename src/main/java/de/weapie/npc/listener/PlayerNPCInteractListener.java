package de.weapie.npc.listener;

import com.github.juliarn.npc.event.PlayerNPCInteractEvent;
import de.weapie.npc.Main;
import de.weapie.npc.customNPC.CustomNPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerNPCInteractListener implements Listener {

    /**
     * Handle event listener to interact with NPCs
     *
     * @param event of PlayerNPCInteractEvent from NPC-Lib
     */
    @EventHandler
    public void handleNPCInteract(PlayerNPCInteractEvent event) {
        for (CustomNPC customNPC : Main.getInstance().getNpcList()) {
            if(event.getNPC() == customNPC.getCreatedNPC()) {
                customNPC.getNpcInteract().onInteractAtNPC(event);
                break;
            }
        }
    }

}
