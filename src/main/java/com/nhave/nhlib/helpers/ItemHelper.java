package com.nhave.nhlib.helpers;

import net.minecraft.item.ItemStack;

public class ItemHelper
{
	public static boolean areItemsEqual(ItemStack stack1, ItemStack stack2)
	{
		if (stack1 != null && stack2 != null)
		{
			if ((stack1.getItem() == stack2.getItem()) && (stack1.getItemDamage() == stack2.getItemDamage())) return true;
		}
		return false;
	}
}