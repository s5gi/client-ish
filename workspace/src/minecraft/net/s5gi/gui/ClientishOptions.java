package net.s5gi.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class ClientishOptions extends GuiScreen {
    private String info = "[INFO]: Awaiting Button Press...";
    private GuiScreen comingFrom;
    public ClientishOptions(GuiScreen loc) {
        this.comingFrom = loc;
    }

    private static HashMap<Integer, OnClickEvent> onClickDoMap = new HashMap<>();
    private void addButton(int ID, int ScreenX, int ScreenY, int width, String label, OnClickEvent clickEvent, boolean enabled) {
        GuiButton button = new GuiButton(ID, ScreenX, ScreenY, label);
        button.enabled = enabled;
        onClickDoMap.put(ID, clickEvent);
        if (!(width <=-1)) {
            button.setWidth(width);
        }
        this.buttonList.add(button);
    }
    @Override
    public void initGui() {
        GlStateManager.pushMatrix();
        int cW = this.width/2;
        int cH = this.height/2;
        int left = -155;
        int right = 5;

        int row = 1;
        addButton(0, 0, 0, 40, "<---", (button) -> {
            mc.displayGuiScreen(comingFrom);
        }, true);

        addButton(1, cW+left, cH/2, 155, "Run Java Garbage Collector", (button) -> {
            System.gc();
            info = "[INFO]: Executed System.gc();";
            button.enabled = false;
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            Runnable task = () -> {
                button.enabled = true;
                info = "[INFO]: Awaiting Button Press...";
            };
            scheduler.schedule(task, 3, TimeUnit.SECONDS);
            scheduler.shutdown();
        },true);

        addButton(2, cW+right, cH/2, 155, "unused", (button) -> {},false);
        addButton(3, cW+left, cH/2 + 25, 155, "unused", (button) -> {},false);
        addButton(4, cW+right, cH/2+ 25, 155, "unused", (button) -> {},false);
        addButton(5, cW+left, cH/2 + 50, 155, "unused", (button) -> {},false);
        addButton(6, cW+right, cH/2 + 50, 155, "unused", (button) -> {},false);

        GlStateManager.popMatrix();
        super.initGui();
    }

    @Override
    public void drawScreen(int mX, int mY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, info, this.width / 2, this.height/5, 0xFFFFFFFF );
        super.drawScreen(mX, mY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        onClickDoMap.get(button.id).run(button);
    }

    public interface OnClickEvent {
        public void run(GuiButton button);
    }
}
