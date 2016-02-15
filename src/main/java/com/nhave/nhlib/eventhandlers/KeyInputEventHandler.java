package com.nhave.nhlib.eventhandlers;

import com.nhave.nhlib.core.Key;
import com.nhave.nhlib.interfaces.IKeyBound;
import com.nhave.nhlib.main.KeyBinds;
import com.nhave.nhlib.network.MessageKeyPressed;
import com.nhave.nhlib.network.PacketHandler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler
{
	private static Key getPressedKeybinding()
    {
        if (KeyBinds.toggle.getIsKeyPressed())
        {
            return Key.TOGGLE;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
    	if (getPressedKeybinding() == Key.UNKNOWN)
        {
            return;
        }

        if (FMLClientHandler.instance().getClient().inGameHasFocus)
        {
            if (FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                if (entityPlayer.getCurrentEquippedItem() != null)
                {
                    ItemStack currentlyEquippedItemStack = entityPlayer.getCurrentEquippedItem();
                    
                    if (currentlyEquippedItemStack.getItem() instanceof IKeyBound)
                    {
                        boolean chat = ((IKeyBound)currentlyEquippedItemStack.getItem()).shouldAddChat(entityPlayer, currentlyEquippedItemStack);
                        if (entityPlayer.worldObj.isRemote)
                        {
                            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeybinding(), chat));
                        }
                        else
                        {
                            ((IKeyBound) currentlyEquippedItemStack.getItem()).doKeyBindingAction(entityPlayer, currentlyEquippedItemStack, getPressedKeybinding().name(), chat);
                        }
                    }
                }
            }
        }
   	}
}