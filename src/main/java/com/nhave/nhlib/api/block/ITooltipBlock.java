package com.nhave.nhlib.api.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ITooltipBlock
{
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean arg);
}