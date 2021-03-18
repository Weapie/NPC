package de.weapie.npc.customNPC;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.SpawnCustomizer;
import com.github.juliarn.npc.profile.Profile;
import de.weapie.npc.Main;
import lombok.*;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Collections;
import java.util.UUID;

/**
 * Created: 17.03.2021
 * Developed with love
 *
 * @author Weapie (Jerrit Fritzsche)
 */

@Getter
@Setter
public class CustomNPC implements CustomNPCImpl {

    /**
     * Variable of NPC displayname
     */
    private String displayName = null;

    /**
     * Variable of NPC uuid
     */
    private UUID uuid = null;

    /**
     * Variable of NPC skin value
     */
    private String value = null;

    /**
     * Variable of NPC skin signature
     */
    private String signature = null;

    /**
     * Variable of NPC location
     */
    private Location location = null;

    /**
     * Variable of NPC imitatePlayers
     */
    private boolean imitatePlayers = false;

    /**
     * Variable of NPC lookAtPlayers
     */
    private boolean lookAtPlayers = false;

    /**
     * Variable of NPC spawnCustomizer
     */
    private SpawnCustomizer spawnCustomizer = null;

    /**
     * Variable of NPC interact
     */
    private CustomNPCInteractImpl npcInteract = null;

    /**
     * Variable of created NPC
     */
    private NPC createdNPC = null;

    /**
     * Variable of NPC line distance
     */
    private static double infoLineDistance = 0.3D;

    /**
     * Constructor of simple NPC
     *
     * @param displayName from NPC
     * @param uuid from NPC
     * @param location from NPC
     */
    public CustomNPC(String displayName, UUID uuid, Location location) {
        new CustomNPC(displayName, uuid, null, null, location, false, false, null, null);
    }

    /**
     * Constructor of complex NPC
     *
     * @param displayName from NPC
     * @param uuid from NPC
     * @param value from NPC skin
     * @param signature from NPC skin
     * @param location from NPC
     * @param imitatePlayers from NPC
     * @param lookAtPlayers from NPC
     * @param spawnCustomizer from NPC
     * @param npcInteract from NPC
     */
    public CustomNPC(String displayName, UUID uuid, String value, String signature, Location location, boolean imitatePlayers, boolean lookAtPlayers, SpawnCustomizer spawnCustomizer, CustomNPCInteractImpl npcInteract) {
        this.displayName = displayName;
        this.uuid = uuid;
        this.value = value;
        this.signature = signature;
        this.location = location;
        this.imitatePlayers = imitatePlayers;
        this.lookAtPlayers = lookAtPlayers;
        this.spawnCustomizer = spawnCustomizer;
        this.npcInteract = npcInteract;
    }

    /**
     * Spawn new NPC with NPC-Lib
     */
    @Override
    public void spawn() {
        if(this.displayName == null || this.uuid == null || this.location == null) {
            throw new NullPointerException("Please fill all required variables!");
        }

        NPC.Builder npcBuilder = new NPC.Builder(
                new Profile(
                        this.uuid,
                        this.displayName,
                        null
                )
        );

        if(this.value != null && this.signature != null) {
            npcBuilder = new NPC.Builder(
                    new Profile(
                            this.uuid,
                            this.displayName,
                            Collections.singletonList(new Profile.Property("textures", this.value, this.signature))
                    )
            );
        }
        npcBuilder.location(this.location);
        npcBuilder.imitatePlayer(this.imitatePlayers);
        npcBuilder.lookAtPlayer(this.lookAtPlayers);

        if(this.spawnCustomizer != null) {
            npcBuilder.spawnCustomizer(this.spawnCustomizer);
        }

        createdNPC = npcBuilder.build(Main.getInstance().getNpcPool());
        Main.getInstance().getNpcList().add(this);
    }

    /**
     * Despawn an existing NPC
     */
    @Override
    public void despawn() {
        location.getWorld()
                .getNearbyEntities(location, infoLineDistance + 0.1D, infoLineDistance + 0.1D, infoLineDistance + 0.1D)
                .stream()
                .filter(entity -> entity instanceof ArmorStand)
                .findFirst()
                .ifPresent(Entity::remove);

        Main.getInstance().getNpcPool().removeNPC(this.createdNPC.getEntityId());
        Main.getInstance().getNpcList().remove(this);
    }

    /**
     * Set Info line on top of the npc displayname
     *
     * @param npc to get the location
     * @param text on top of the npc
     */
    public static void setInfoTextLine(NPC npc, String text) {
        Location location = npc.getLocation();

        if(location.getWorld() == null || !location.getChunk().isLoaded()) {
            return;
        }

        ArmorStand armorStand = (ArmorStand) location.getWorld()
                .getNearbyEntities(location, infoLineDistance + 0.1D, infoLineDistance + 0.1D, infoLineDistance + 0.1D)
                .stream()
                .filter(entity -> entity instanceof ArmorStand)
                .findFirst()
                .orElse(null);

        if(armorStand == null) {
            armorStand = (ArmorStand) location.getWorld().spawnEntity(location.add(0, infoLineDistance, 0), EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCanPickupItems(false);
            armorStand.setCustomNameVisible(true);
        }

        armorStand.setCustomName(text);
    }

}
