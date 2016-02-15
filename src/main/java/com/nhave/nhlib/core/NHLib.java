package com.nhave.nhlib.core;

import org.apache.logging.log4j.Logger;

import com.nhave.nhlib.eventhandlers.ToolStationEventHandler;
import com.nhave.nhlib.handlers.BlockHandler;
import com.nhave.nhlib.handlers.TweaksHandler;
import com.nhave.nhlib.network.PacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MODID, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MCVERSIONS, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUIFACTORY)
public class NHLib
{
    public static Logger logger;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
    
    @Mod.Instance(Reference.MODID)
	public static NHLib instance = new NHLib();
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
		proxy.setupConfig(event.getSuggestedConfigurationFile());
    	//proxy.registerLibs();
		PacketHandler.init();
		proxy.registerKeybindings();
		BlockHandler.preInit();
    }
    
    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {
    	proxy.registerEventHandlers();
    	proxy.registerRenderers();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	BlockHandler.postInit();
    	TweaksHandler.postInit();
    	MinecraftForge.EVENT_BUS.register(new ToolStationEventHandler());
    }
}