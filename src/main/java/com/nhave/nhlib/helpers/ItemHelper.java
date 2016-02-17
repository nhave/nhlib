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
	
	public static ItemStack getCurrentItemOrArmor(EntityPlayer player, int slot)
	{
		if (slot == 0) return player.getCurrentEquippedItem();
		else if (slot == 1) return player.getCurrentArmor(0);
		else if (slot == 2) return player.getCurrentArmor(1);
		else if (slot == 3) return player.getCurrentArmor(2);
		else if (slot == 4) return player.getCurrentArmor(3);
		else return null;
	}
}