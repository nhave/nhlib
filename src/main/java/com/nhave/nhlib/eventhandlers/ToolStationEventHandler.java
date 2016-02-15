package com.nhave.nhlib.eventhandlers;

import com.nhave.nhlib.api.item.IItemShader;
import com.nhave.nhlib.api.item.IShadeAble;
import com.nhave.nhlib.events.ToolStationUpdateEvent;
import com.nhave.nhlib.helpers.ItemHelper;
import com.nhave.nhlib.shaders.ShaderManager;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;

public class ToolStationEventHandler
{
	@SubscribeEvent
	public void handleToolStationEvent(ToolStationUpdateEvent evt)
	{
		if (evt.input == null || evt.mod == null)
		{
			return;
		}
		else if (evt.input.getItem() instanceof IShadeAble && evt.mod.getItem() instanceof IItemShader)
		{
			if (ShaderManager.hasShader(evt.input) && ItemHelper.areItemsEqual(ShaderManager.getShader(evt.input), evt.mod)) return;
			if (ShaderManager.canApplyShader(evt.input, evt.mod))
			{
				ItemStack shadeable = evt.input.copy();
				ShaderManager.setShader(shadeable, evt.mod.copy());
				evt.materialCost=0;
				evt.output=shadeable;
			}
		}
		else if (evt.input.getItem() instanceof IShadeAble && evt.mod.getItem() == Items.feather)
		{
			if (!ShaderManager.hasShader(evt.input)) return;
			ItemStack shadeable = evt.input.copy();
			ShaderManager.removeShader(shadeable);
			evt.materialCost=0;
			evt.output=shadeable;
		}
	}
	
	/*@SubscribeEvent
	public void handleAnvilEvent(AnvilUpdateEvent evt)
	{
		if (evt.left == null || evt.right == null)
		{
			return;
		}
		else if (evt.left.getItem() instanceof IShadeAble && evt.right.getItem() instanceof IItemShader)
		{
			if (ShaderManager.hasShader(evt.left) && ItemHelper.areItemsEqual(ShaderManager.getShader(evt.left), evt.right)) return;
			if (ShaderManager.canApplyShader(evt.left, evt.right))
			{
				ItemStack shadeable = evt.left.copy();
				ShaderManager.setShader(shadeable, evt.right.copy());
				evt.cost=2;
				evt.materialCost=1;
				evt.output=shadeable;
			}
		}
		else if (evt.left.getItem() instanceof IShadeAble && evt.right.getItem() == Items.feather)
		{
			if (!ShaderManager.hasShader(evt.left)) return;
			ItemStack shadeable = evt.left.copy();
			ShaderManager.removeShader(shadeable);
			evt.cost=2;
			evt.materialCost=1;
			evt.output=shadeable;
		}
	}
	
	@SubscribeEvent
	public void handleRepairEvent(AnvilRepairEvent evt)
	{
		if (evt.left == null || evt.right == null)
		{
			return;
		}
		if (evt.right.getItem() instanceof IShadeAble && evt.left.getItem() instanceof IItemShader)
		{
			++evt.left.stackSize;
		}
	}*/
}