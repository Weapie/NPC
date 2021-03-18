package de.weapie.npc;

import com.github.juliarn.npc.NPCPool;
import de.weapie.npc.customNPC.CustomNPC;
import de.weapie.npc.listener.PlayerNPCInteractListener;
import de.weapie.npc.test.TestNPC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Created: 17.03.2021
 * Developed with love
 *
 * @author Weapie (Jerrit Fritzsche)
 */

public class Main extends JavaPlugin {

    /**
     * Variable of instance
     */
    @Getter
    private static Main instance;

    /**
     * Variable of npcPool that stores the NPCs
     */
    @Getter
    private NPCPool npcPool;

    /**
     * Variable of npcList that stores the custom NPCs
     */
    @Getter
    private List<CustomNPC> npcList = new ArrayList<>();

    /**
     * Plugin enable method
     */
    @Override
    public void onEnable() {
        /* Set static variable instance to this */
        instance = this;

        /* Remove all existing armorstand from the World called "world" */
        Bukkit.getWorld("world").getEntities().stream().filter(entity -> entity instanceof ArmorStand).forEach(entity -> entity.remove());

        /* Init the npcPool from NPC-Lib */
        this.npcPool = new NPCPool(this);

        /* Perform method to spawn NPCs */
        TestNPC.testNPCs();

        /* Register listener to interact with NPCs */
        Bukkit.getPluginManager().registerEvents(new PlayerNPCInteractListener(), this);
    }

}
