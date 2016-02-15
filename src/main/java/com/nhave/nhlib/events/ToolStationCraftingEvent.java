package com.nhave.nhlib.events;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class ToolStationCraftingEvent extends PlayerEvent
{
	public final ItemStack input;
	public final ItemStack mod;
	public final ItemStack output;

	public ToolStationCraftingEvent(EntityPlayer player, ItemStack output, ItemStack input, ItemStack mod)
	{
		super(player);
		this.output = output;
		this.input = input;
		this.mod = mod;
	}
}