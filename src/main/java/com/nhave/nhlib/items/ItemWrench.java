package com.nhave.nhlib.items;

import java.util.List;

import com.nhave.nhlib.util.StringUtils;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemWrench extends Item implements IToolWrench
{
	public ItemWrench()
	{
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		list.add(StringUtils.BOLD + StatCollector.translateToLocal("tooltip.item.wrench") + "     ");
		list.add(StringUtils.WHITE + StatCollector.translateToLocal("tooltip.rarity.basic"));
	}
	
	@Override
	public boolean doesSneakBypassUse(World arg0, int arg1, int arg2, int arg3, EntityPlayer arg4)
	{
		return true;
	}
	
	@Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z)
	{
		return true;
	}
	
	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z)
	{
		player.swingItem();
	}
}