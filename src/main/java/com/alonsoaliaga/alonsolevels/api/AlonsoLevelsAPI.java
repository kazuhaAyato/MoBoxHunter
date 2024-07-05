package com.alonsoaliaga.alonsolevels.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AlonsoLevelsAPI {
    public static void addExperience(UUID uuid, int exp) {
        // Add experience to player
        Player player = Bukkit.getPlayer(uuid);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clv addExp " + exp + " " + player.getName());

    }
}
