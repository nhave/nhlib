package com.nhave.nhlib.handlers;

import com.nhave.nhlib.config.ConfigHandler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TweaksHandler
{
	public static void postInit()
	{
		if (ConfigHandler.tweakStoneSlab)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.double_stone_slab), Blocks.stone_slab, Blocks.stone_slab);
		}
		if (ConfigHandler.tweakCommandBlock) Blocks.command_block.setCreativeTab(CreativeTabs.tabRedstone);
		if (ConfigHandler.tweakCommandCart) Items.command_block_minecart.setCreativeTab(CreativeTabs.tabTransport);
	}
}