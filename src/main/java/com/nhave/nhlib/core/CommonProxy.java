package com.nhave.nhlib.core;

import java.io.File;

import com.nhave.nhlib.main.LibController;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy
{
    public void registerLibs()
    {
    	NHLib.logger.info("Loading library: nhlib");
    	boolean libLoaded = LibController.initServer(Reference.MODID);
    	if (libLoaded) NHLib.logger.info("Successfully loaded library: nhlib");
    	else NHLib.logger.info("Failed to load library: 'nhlib' Library already loaded.");
    }
}