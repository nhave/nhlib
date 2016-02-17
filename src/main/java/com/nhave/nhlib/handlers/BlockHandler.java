package com.nhave.nhlib.handlers;

import com.nhave.nhlib.blocks.BlockToolStation;
import com.nhave.nhlib.core.NHLib;
import com.nhave.nhlib.itemblocks.ItemBlockTooltip;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BlockHandler
{
	public static Block blockToolStation;
	
	public static void preInit()
	{
		blockToolStation = new BlockToolStation().setCreativeTab(NHLib.creativeTab).setBlockName("nhlib.blockToolStation");
		
		registerBlock(blockToolStation);
		
		GameRegistry.registerTileEntity(com.nhave.nhlib.tiles.TileEntityToolStation.class, "TileToolStation");
	}
	
	public static void postInit()
	{
		GameRegistry.addRecipe(new ItemStack(blockToolStation),
			new Object[] {"XXX", "YZY", "XXX",
			'X', Items.iron_ingot,
			'Y', Blocks.iron_bars,
			'Z', Items.redstone});
	}
	
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, ItemBlockTooltip.class, block.getUnlocalizedName());
	}
}