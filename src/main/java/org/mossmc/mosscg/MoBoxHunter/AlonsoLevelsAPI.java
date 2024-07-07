package org.mossmc.mosscg.MoBoxHunter;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AlonsoLevelsAPI {
    public static void addExperience(UUID uuid, int exp) {
        // Add experience to player
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clv addExp " + exp + " " + player.getName());

    }
}
