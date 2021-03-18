package de.weapie.npc.test;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.github.juliarn.npc.modifier.AnimationModifier;
import com.github.juliarn.npc.modifier.MetadataModifier;
import de.weapie.npc.customNPC.CustomNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created: 17.03.2021
 * Developed with love
 *
 * @author Weapie (Jerrit Fritzsche)
 */

public class TestNPC {

    /**
     * Test method to spawn new NPCs
     */
    public static void testNPCs() {
        new CustomNPC(
                "§3Test",
                UUID.fromString("3449de08-11d5-4632-ac78-1f16fd488969"),
                "ewogICJ0aW1lc3RhbXAiIDogMTYxNTkyNTQwOTQ1NSwKICAicHJvZmlsZUlkIiA6ICIyM2YxYTU5ZjQ2OWI0M2RkYmRiNTM3YmZlYzEwNDcxZiIsCiAgInByb2ZpbGVOYW1lIiA6ICIyODA3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I4MDJjZjZjMjhkMDhjNTIyM2M1NjA4MmEyMDJjODg1NmI0NGVjMzRhZDQyY2NmZTc5NmQ0OGM1YjI0NTYyZTQiCiAgICB9CiAgfQp9",
                "bkPKW0T9zneLcraREwSYEx0AtO0MPxhjB20xZ6NjjWj5oOUOD7fCqlw1uAC2c8KG1Zbu/15UOeSkrHAfp9fiTHKBYH/LAU/NIvwlGhgTdSEoEEjTuXw8H1WbGU8DFLwwkLy/I64MbeSiCpc0ngasCD6wcfRVc44h8a5AzXnvyISPYVsB88MtuZSlLvU8rlygpo26cftwraYjBHDzH5A7zZkWTIUhbkNO1nDNXC2fwoJ5Kpp1R4wIeC0NlJnrQMDfxwmZj7OulTg6nBCQba62PTS7QQyzjpEiZmvNtlp0T1Z0KdaXLEEsbQmDVPGUF2+khB3Mmol87fuhTaZ01Nq148uNS2ZeZy8ysoYCdR8o/sHz8Bv1dAFDwq/m6PkOekL6ucOjNqQO12Ibycq2h6hJfuWpFIFrX7ePL37ewmedX2CxyyzHs2zn4MA2Lc1LqJKaO3G4sUF9YycPtS8nN+yeqzMSq7/mL14/BX6fZdci9QfTDH2maKVqm1ZtEDeK+FRPtmh64Wxv6hniUE6Nalbxy5LI9ntH7OBiD1RkqDeLBKOZ93kZE5emeE+wAmOJng34utoWyKHEh30l3xWcgW2L4fYjJyJOotcwqS7XzPA1tAzXFzPcImHLVBtXvT1/S9ZhLNMtbtSXfCqQN5VR3N6IXQeahCr+vnzHOljkbEvp7Ew=",
                new Location(Bukkit.getWorld("world"), 100, 70, 100),
                true,
                true,
                (npc, player) -> {
                    CustomNPC.setInfoTextLine(npc, "§7Das ist ein Test");
                    npc.equipment().queue(EnumWrappers.ItemSlot.MAINHAND, new ItemStack(Material.WATER_BUCKET)).send(player);
                    npc.metadata().queue(MetadataModifier.EntityMetadata.SKIN_LAYERS, true).send(player);
                },
                event -> {
                    if(event.getAction() == EnumWrappers.EntityUseAction.ATTACK) {
                        event.getPlayer().sendMessage(event.getNPC().getGameProfile().getName() + " §8> §cAUA!");
                        event.getNPC().animation().queue(AnimationModifier.EntityAnimation.TAKE_DAMAGE).send(event.getPlayer());
                    }
                }
        ).spawn();

        new CustomNPC(
                "§4§lWeapie",
                UUID.fromString("6cc7dd5b-16ea-4383-9cb6-987e127f0b56"),
                "ewogICJ0aW1lc3RhbXAiIDogMTYxNTk5Mjc3NDMyMCwKICAicHJvZmlsZUlkIiA6ICI2Y2M3ZGQ1YjE2ZWE0MzgzOWNiNjk4N2UxMjdmMGI1NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJXZWFwaWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzBiOTUyNTY2MjZiOTI1NTgwZTQyMGRhNzVhMTEyOTgwMjY2ZDNjZGQ4NDY5MDk4YzAyZTViYTQzMzZhMDE0IgogICAgfQogIH0KfQ==",
                "ZLfgFIJvp4Zmg3vkkjcB7GtayaEbbznKl1QYXgOtKgMRxc/4TzzjwggqIjWyewmT515E0bZPZC7zknzjI2u/cuOhaB+cDrNLUs8Zii0SZ1Q2SSsqFCQr5JRikSlixCOzt7PMbt97nbjIE4a0GR2NKA6flxCyLmJYkamKHaAY3OGcW5/1j8Ez6xE7bHpsdIPZ6On82l6QZAPCHoC64c7nMK4cO0cR4MV8MC/1BGsLAqRaTIPE5zma9N+Tk/aOzwCEQ5KIC91GTm8R9vzFOP8tjvgWmASNGGrS9sbcdLaR6Qwb8k6bcaidYnp+3M1YQ4dw+45bsASdBT2DtuRGRmhTWjDIXfioGl09kutlgcG8YEdwGZ/Ong+qgyQTrn57AM//V+ke/1AERHoVu+lUxjh+uP6x9oGOLoHqXhlwJJbR2YJr2E6sxhJHYi0lQLdamLk6O/pj28h14rtg3tHMcf+7I/14f7RMy/7mQVQ5IE7Z63itdR4bHp7OXJFbydIxVO3b0bDHZRkOsMOtgSY0G2/WovupIo4XqljNQ46FlNguJpodOP4bUPkLs7igJdhoZScG53lHa3tnpiJJd2J1665QYt5agPzZkZvmj2u/99LfjSg/98gYfW/dU7U0/BaV1f5LS3YZ3ZJ+VjrBYQzH/JnkkxSYNbW1MYpCJgnWJkVDyg0=",
                new Location(Bukkit.getWorld("world"), 102, 70, 100),
                false,
                true,
                (npc, player) -> {
                    CustomNPC.setInfoTextLine(npc, "§c§lIch bin Krass!");
                    npc.equipment().queue(EnumWrappers.ItemSlot.MAINHAND, new ItemStack(Material.COMMAND)).send(player);
                    npc.metadata().queue(MetadataModifier.EntityMetadata.SKIN_LAYERS, true).send(player);
                },
                event -> {
                    if(event.getAction() == EnumWrappers.EntityUseAction.INTERACT) {
                        event.getPlayer().sendMessage(event.getNPC().getGameProfile().getName() + " §8> §7Hast du gesehen wie Krass das ist!");
                    }
                }
        ).spawn();
    }

}
