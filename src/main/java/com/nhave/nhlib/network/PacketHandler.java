package com.nhave.nhlib.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("nhlib");

    public static void init()
    {
        INSTANCE.registerMessage(MessageKeyPressed.class, MessageKeyPressed.class, 0, Side.SERVER);
    }
}