package com.nhave.nhlib.core;

import com.nhave.nhlib.main.LibController;

public class ClientProxy extends CommonProxy
{
	@Override
    public void registerLibs()
    {
		NHLib.logger.info("Loading library: nhlib");
    	boolean libLoaded = LibController.initClient(Reference.MODID);
    	if (libLoaded) NHLib.logger.info("Successfully loaded library: nhlib");
    	else NHLib.logger.info("Failed to load library: 'nhlib' Library already loaded.");
    }
}