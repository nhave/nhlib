package com.nhave.nhlib.handlers;

import com.nhave.nhlib.core.NHLib;
import com.nhave.nhlib.items.ItemWrench;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHandler
{
	public static Item itemWrench;
	
	public static void preInit()
	{
		itemWrench = new ItemWrench().setCreativeTab(NHLib.creativeTab).setTextureName("nhcore:Wrench").setUnlocalizedName("nhlib.itemWrench");

		registerItem(itemWrench);
	}
	
	public static void postInit()
	{
		GameRegistry.addRecipe(new ItemStack(itemWrench),
			new Object[] {"X X", " X ", "X X",
			'X', Items.iron_ingot});
	}
	
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
}