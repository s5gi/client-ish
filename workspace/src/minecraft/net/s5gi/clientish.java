package net.s5gi;

import net.minecraft.client.Minecraft;

public class clientish {
    public static String name = "Client-ish";

    //2NDHALFYEAR-1STHALFYEAR.DAYOFMONTH.MONTH
    public static String version = "2320.01.11-alpha";

    public static void startup() {
        Minecraft.setTitle("Minecraft | " + name + " v" + version);
    }
    public static void shutdown() {

    }

    public static void CustomMethodTrackerDebug() {
        //TRACKS CUSTOM METHODS FOR SEARCH UP INCASE OF FUTURE REMOVAL
    }
}
