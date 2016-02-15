package com.nhave.nhlib.events;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.ItemStack;

@Cancelable
public class ToolStationUpdateEvent extends Event
{
	public final ItemStack input;
	public final ItemStack mod;
	public ItemStack output;
	public int materialCost;

	public ToolStationUpdateEvent(ItemStack input, ItemStack mod)
	{
		this.input = input;
		this.mod = mod;
		this.materialCost = 0;
	}
}