package com.nhave.nhlib.core;

import java.io.File;

import com.nhave.nhlib.config.ConfigHandler;
import com.nhave.nhlib.eventhandlers.KeyInputEventHandler;
import com.nhave.nhlib.main.KeyBinds;
import com.nhave.nhlib.renders.RenderTileToolStation;
import com.nhave.nhlib.tickhandlers.HudTickHandler;
import com.nhave.nhlib.tiles.TileEntityToolStation;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
        FMLCommonHandler.instance().bus().register(new HudTickHandler());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToolStation.class, new RenderTileToolStation());
        //RenderingRegistry.registerBlockHandler(new RenderTileToolStation());
        //RenderTileToolStation.ToolStationRenderID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void setupConfig(File configFile)
	{
		FMLCommonHandler.instance().bus().register(new ConfigHandler(true));
		ConfigHandler.init(configFile);
	}
	
	@Override
    public void registerKeybindings()
	{
		ClientRegistry.registerKeyBinding(KeyBinds.toggle);
	}

	@Override
	public void registerEventHandlers()
	{
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
	}
}