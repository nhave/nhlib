package com.nhave.nhlib.core;

import java.io.File;

import com.nhave.nhlib.config.ConfigHandler;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy
{
	public void registerRenderers() {}
	
	public void setupConfig(File configFile)
	{
		FMLCommonHandler.instance().bus().register(new ConfigHandler(false));
		ConfigHandler.init(configFile);
	}
	
    public void registerKeybindings() {}

	public void registerEventHandlers() {}
}