package com.nhave.nhlib.itemblocks;

import java.util.List;

import com.nhave.nhlib.api.block.ITooltipBlock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTooltip extends ItemBlock
{
	public ItemBlockTooltip(Block arg0)
	{
		super(arg0);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean arg)
	{
		if (field_150939_a != null && field_150939_a instanceof ITooltipBlock) ((ITooltipBlock)field_150939_a).addInformation(stack, player, list, arg);
	}
}