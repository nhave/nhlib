package com.nhave.nhlib.main;

import com.nhave.nhlib.eventhandlers.KeyInputEventHandler;
import com.nhave.nhlib.network.PacketHandler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class LibController
{
	private static boolean loaded = false;
	
	public static boolean initClient(String modID)
	{
		if (loaded) return false;
		PacketHandler.init();
		ClientRegistry.registerKeyBinding(KeyBinds.toggle);
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
		loaded = true;
		return true;
	}
	
	public static boolean initServer(String modID)
	{
		if (loaded) return false;
		PacketHandler.init();
		loaded = true;
		return true;
	}
}