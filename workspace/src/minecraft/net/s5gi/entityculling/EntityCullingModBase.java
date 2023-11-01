package net.s5gi.entityculling;

import com.logisticscraft.occlusionculling.OcclusionCullingInstance;

import java.util.Arrays;
import java.util.HashSet;

public class EntityCullingModBase {
    public static EntityCullingModBase instance = new EntityCullingModBase();

    public static boolean enabled = true;
    public int renderedBlockEntities = 0;
    public int skippedBlockEntities = 0;
    public int renderedEntities = 0;
    public int skippedEntities = 0;

    public OcclusionCullingInstance culling;
    public CullTask cullTask;

    public EntityCullingModBase() {
        culling = new OcclusionCullingInstance(128, new Provider());
        cullTask = new CullTask(culling, new HashSet<>(Arrays.asList("tile.beacon")));
        Thread cullThread = new Thread(cullTask, "CullThread");
        cullThread.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("The CullingThread has crashed! Please report the following stacktrace!");
            ex.printStackTrace();
        });
        cullThread.start();
    }
}
