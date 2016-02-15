package com.nhave.nhlib.network;

import com.nhave.nhlib.core.Key;
import com.nhave.nhlib.interfaces.IKeyBound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage>
{
    private byte keyPressed;

    public MessageKeyPressed()
    {
    }

    public MessageKeyPressed(Key key, boolean chat)
    {
        if (key == Key.TOGGLE && !chat)
        {
            this.keyPressed = (byte) 0;
        }
        else if (key == Key.TOGGLE && chat)
        {
            this.keyPressed = (byte) 1;
        }
        else
        {
            this.keyPressed = (byte) 2;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.keyPressed = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeByte(keyPressed);
    }

    @Override
    public IMessage onMessage(MessageKeyPressed message, MessageContext ctx)
    {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

        if (entityPlayer != null && entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound)
        {
        	if (message.keyPressed == 0)
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), "TOGGLE", false);
            }
        	else if (message.keyPressed == 1)
            {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), "TOGGLE", true);
            }
        }

        return null;
    }
}