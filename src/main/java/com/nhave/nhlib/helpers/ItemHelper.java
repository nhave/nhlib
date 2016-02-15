package com.nhave.nhlib.helpers;

import buildcraft.api.tools.IToolWrench;
import net.minecraft.entity.player.EntityPlayer;
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
	
	public static boolean isToolWrench(EntityPlayer player, ItemStack stack, int x, int y, int z)
	{
		return stack != null && stack.getItem() instanceof IToolWrench && ((IToolWrench)stack.getItem()).canWrench(player, x, y, z);
	}
	
	public static void useWrench(EntityPlayer player, ItemStack stack, int x, int y, int z)
	{
		if (stack != null && stack.getItem() instanceof IToolWrench) ((IToolWrench)stack.getItem()).wrenchUsed(player, x, y, z);
	}
	
	public static void addItemToPlayer(EntityPlayer player, ItemStack stack)
	{
		if (!player.inventory.addItemStackToInventory(stack))
		{
			if (!player.worldObj.isRemote) player.entityDropItem(stack, 1F);
		}
	}
}