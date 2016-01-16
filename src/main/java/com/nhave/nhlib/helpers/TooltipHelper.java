package com.nhave.nhlib.helpers;

import java.util.List;

import net.minecraft.util.StatCollector;

public class TooltipHelper
{
	public static void addLocalString(List list, String tooltip)
	{
		list.add(StatCollector.translateToLocal(tooltip));
	}
	
	public static void addSplitString(List list, String tooltip, String splitAt)
	{
		String[] info = tooltip.split(splitAt);
		for (int i = 0; i < info.length; ++i)
		{
			list.add(info[i]);
		}
	}
	
	public static void addHiddenTooltip(List list, String tooltip, String splitAt)
	{
		String tooltip1 = StatCollector.translateToLocal(tooltip);
		if (!tooltip1.equals(tooltip))
		{
			addSplitString(list, tooltip1, splitAt);
		}
	}
	
	public static void addHiddenTooltip(List list, String tooltip)
	{
		String tooltip1 = StatCollector.translateToLocal(tooltip);
		if (!tooltip1.equals(tooltip))
		{
			addLocalString(list, tooltip1);
		}
	}
	
	public static String translate(String local)
	{
		return StatCollector.translateToLocal(local);
	}
}