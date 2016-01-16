package com.nhave.nhlib.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound
{
	public abstract void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, String key);
}