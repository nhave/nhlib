package com.nhave.nhlib.interfaces;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound
{
	@SideOnly(Side.CLIENT)
	public boolean shouldAddChat(EntityPlayer entityPlayer, ItemStack itemStack);
	
	public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, String key, boolean chat);
}