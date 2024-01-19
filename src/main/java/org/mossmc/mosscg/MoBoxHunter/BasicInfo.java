package org.mossmc.mosscg.MoBoxHunter;

import org.bukkit.Location;

import java.util.ArrayList;

public class BasicInfo {
    public static String pluginName = "MoBoxHunter";
    public static String pluginVersion = "v2.01";
    public static String gameName = "Hunter";
    public static ArrayList<String> choseTime = new ArrayList<>();
    public static ArrayList<String> choseFull = new ArrayList<>();
    public static boolean compassUnlock = false;
    public static boolean teamDamage = false;
    public static boolean runnerNetherWorld = false;
    public static boolean runnerEndWorld = false;
    public static boolean hunterNetherWorld = false;
    public static boolean hunterEndWorld = false;
    public static boolean canPoint = false;
    public static boolean canDamage = false;
    public static boolean canInteract = false;
    public static boolean isFastMode = false;
    public static long StartTime = 0L;

    public static playerRole winner;

    public static Location endLocation;

    public static int startTime = 10;

    public enum playerRole {
        Hunter, Runner, Observer
    }

    public enum runnerStatus {
        Alive, Dead
    }
}
