package com.nhave.nhlib.config;

import java.io.File;

import com.nhave.nhlib.core.Reference;
import com.nhave.nhlib.util.RenderUtils.HUDPositions;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler
{
	public static boolean isClientConfig;
	
	public static Configuration config;

    public static boolean tweakStoneSlab = Defaults.tweakStoneSlab;
    public static boolean tweakCommandBlock = Defaults.tweakCommandBlock;
    public static boolean tweakCommandCart = Defaults.tweakCommandCart;
    public static boolean tweakSnowBalls = Defaults.tweakSnowBalls;
    public static boolean tweakEnderPearls = Defaults.tweakEnderPearls;
    
    public static int hudPosition = Defaults.hudPosition;
    public static int hudOffsetX = Defaults.hudOffsetX;
    public static int hudOffsetY = Defaults.hudOffsetY;
    public static double hudScale = Defaults.hudScale;
    public static boolean showHudWhileChatting = Defaults.showHudWhileChatting;
    public static boolean enableHud = Defaults.enableHud;
    public static boolean postModeToChat = Defaults.postModeToChat;
	
	public ConfigHandler(boolean isClient)
	{
		this.isClientConfig = isClient;
	}

	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		loadConfig(false);
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if(eventArgs.modID.equalsIgnoreCase(Reference.MODID))
		{
			loadConfig(false);
		}
	}
	
	public static void loadConfig(boolean load)
	{
		loadCommonConfig();
		if (isClientConfig) loadClientConfig();
		
		if (!config.hasChanged()) return;
		config.save();
	}
	
	public static void loadCommonConfig()
	{
		config.setCategoryComment("common", "Configuration for all Common configs");
		tweakStoneSlab = config.get("common", "TweakStoneSlab", Defaults.tweakStoneSlab, "Adds crafting for Double Stone Slabs").getBoolean(Defaults.tweakStoneSlab);
		tweakCommandBlock = config.get("common", "TweakCommandBlock", Defaults.tweakCommandBlock, "Adds the Command Block to the Redstone Creative Tab").getBoolean(Defaults.tweakCommandBlock);
		tweakCommandCart = config.get("common", "TweakCommandCart", Defaults.tweakCommandCart, "Adds the Command Cart to the Transport Creative Tab").getBoolean(Defaults.tweakCommandCart);
		
		tweakSnowBalls = config.get("common", "TweakSnowBalls", Defaults.tweakSnowBalls, "Increases stacksize of Snow Balls 64").getBoolean(Defaults.tweakSnowBalls);
		tweakEnderPearls = config.get("common", "TweakEnderPearls", Defaults.tweakEnderPearls, "Increases stacksize of Ender Pearls 64").getBoolean(Defaults.tweakEnderPearls);
	}
	
	public static void loadClientConfig()
	{
		config.setCategoryComment("client", "Configuration for all Client configs");
		hudPosition = config.get("client", "HUDBasePosition", Defaults.hudPosition, "The base position of the HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").setMinValue(0).setMaxValue(HUDPositions.values().length - 1).getInt(Defaults.hudPosition);
        hudOffsetX = config.get("client", "HUDOffset-X", Defaults.hudOffsetX, "The HUD display will be shifted horizontally by this value. This value may be negative.").getInt(Defaults.hudOffsetX);
        hudOffsetY = config.get("client", "HUDOffset-Y", Defaults.hudOffsetY, "The HUD display will be shifted vertically by this value. This value may be negative.").getInt(Defaults.hudOffsetY);
        hudScale = Math.abs(config.get("client", "HUDScale", Defaults.hudScale, "How large the HUD will be rendered. Default is 1.0, can be bigger or smaller").setMinValue(0.001D).getDouble(Defaults.hudScale));
        showHudWhileChatting = config.get("client", "ShowHUDwhilechatting", Defaults.showHudWhileChatting, "When enabled, the HUD will display even when the chat window is opened.").getBoolean(Defaults.showHudWhileChatting);
        enableHud = config.get("client", "EnableHud", Defaults.enableHud, "When enabled, ItemModes Will be shown in the HUD").getBoolean(Defaults.enableHud);
		postModeToChat = config.get("client", "PostModeToChat", Defaults.postModeToChat, "Set to true if you have too much stuff on your HUD").getBoolean(Defaults.postModeToChat);
	}
}