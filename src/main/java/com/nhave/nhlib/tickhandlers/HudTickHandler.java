package com.nhave.nhlib.tickhandlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.nhave.nhlib.api.item.IHudItem;
import com.nhave.nhlib.config.ConfigHandler;
import com.nhave.nhlib.helpers.ItemHelper;
import com.nhave.nhlib.util.RenderUtils;
import com.nhave.nhlib.util.RenderUtils.HUDPositions;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.item.ItemStack;

public class HudTickHandler {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private static void tickEnd()
    {
        if (mc.thePlayer != null)
        {
            if ((mc.currentScreen == null || ConfigHandler.showHudWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo)
            {
                int i = 0;
                for (int j = 0; j < 5; j++)
                {
                	int slot = j;
                	if (ConfigHandler.hudPosition < 5) slot = 4-j;
                	ItemStack hudItem = ItemHelper.getCurrentItemOrArmor(mc.thePlayer, slot);
	                
	                if (hudItem != null && hudItem.getItem() instanceof IHudItem)
	                {
	                	IHudItem provider = (IHudItem) hudItem.getItem();
	                    
	                    List<String> info = new ArrayList<String>();
	                    provider.addHudInfo(hudItem, mc.thePlayer, info);
	                    if (ConfigHandler.hudPosition >= 5) Collections.reverse(info);;
	                    
	                    if (info.isEmpty())
	                    {
	                        return;
	                    }
	                    
	                    GL11.glPushMatrix();
	                    mc.entityRenderer.setupOverlayRendering();
	                    GL11.glScaled(ConfigHandler.hudScale, ConfigHandler.hudScale, 1.0D);
	                    for (String s : info)
	                    {
	                        RenderUtils.drawStringAtHUDPosition(s, HUDPositions.values()[ConfigHandler.hudPosition], mc.fontRenderer, ConfigHandler.hudOffsetX, ConfigHandler.hudOffsetY, ConfigHandler.hudScale, 0xeeeeee, true, i);
	                        i++;
	                    }
	                    
	                    GL11.glPopMatrix();
	                }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt)
    {
        if (evt.phase == Phase.END && ConfigHandler.enableHud)
        {
            tickEnd();
        }
    }
    
}