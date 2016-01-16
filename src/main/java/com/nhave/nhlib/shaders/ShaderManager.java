package com.nhave.nhlib.shaders;

import com.nhave.nhlib.api.item.IItemShader;
import com.nhave.nhlib.api.item.IShadeAble;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class ShaderManager
{
	public static ItemStack getShader(ItemStack stack)
	{
		String compound = "SHADERS";
		String key = "SHADER";
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound == null)
        {
            return null;
        }
        else
        {
        	NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag(compound);

            if (nbttagcompound1 == null)
            {
                return null;
            }
            else
            {
                NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompoundTag(key);
                return nbttagcompound2 == null ? null : (nbttagcompound1.hasKey(key) ? stack.loadItemStackFromNBT(nbttagcompound2) : null);
            }
        }
	}
	
	public static void setShader(ItemStack shadeable, ItemStack shader)
	{
		String compound = "SHADERS";
		String key = "SHADER";
        NBTTagCompound nbttagcompound = shadeable.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            shadeable.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag(compound);

        if (!nbttagcompound.hasKey(compound))
        {
            nbttagcompound.setTag(compound, nbttagcompound1);
        }

        NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompoundTag(key);

        if (!nbttagcompound1.hasKey(key))
        {
        	nbttagcompound1.setTag(key, nbttagcompound2);
        }
        
        shader.writeToNBT(nbttagcompound2);
    }
	
	public static void removeShader(ItemStack stack)
	{
		if (stack.stackTagCompound != null) stack.stackTagCompound.removeTag("SHADERS");
	}
	
	public static boolean hasShader(ItemStack stack)
	{
		ItemStack shader = getShader(stack);
		return shader != null && shader.getItem() instanceof IItemShader;
	}
	
	public static boolean canApplyShader(ItemStack shadeable, ItemStack shader)
	{
		if (shadeable.getItem() instanceof IShadeAble && shader.getItem() instanceof IItemShader)
		{
			return ((IItemShader) shader.getItem()).canApplyTo(shader, shadeable);
		}
		else return false;
	}
	
	public static int getIntegerTag(ItemStack stack, String tag, int def)
	{
		if (!hasShader(stack)) return def;
		
		IItemShader shader = (IItemShader) getShader(stack).getItem();
		Object data = shader.getShaderData(getShader(stack), stack, tag);
		
		if (data instanceof Integer) return (Integer) data;
		else return def;
	}
	
	public static String getStringTag(ItemStack stack, String tag, String def)
	{
		if (!hasShader(stack)) return def;
		
		IItemShader shader = (IItemShader) getShader(stack).getItem();
		Object data = shader.getShaderData(getShader(stack), stack, tag);
		
		if (data instanceof String) return (String) data;
		else return def;
	}
	
	public static boolean getBooleanTag(ItemStack stack, String tag, boolean def)
	{
		if (!hasShader(stack)) return def;
		
		IItemShader shader = (IItemShader) getShader(stack).getItem();
		Object data = shader.getShaderData(getShader(stack), stack, tag);
		
		if (data instanceof Boolean) return (Boolean) data;
		else return def;
	}
	
	public static IIcon getIIconTag(ItemStack stack, String tag, IIcon def)
	{
		if (!hasShader(stack)) return def;
		
		IItemShader shader = (IItemShader) getShader(stack).getItem();
		Object data = shader.getShaderData(getShader(stack), stack, tag);
		
		if (data instanceof IIcon) return (IIcon) data;
		else return def;
	}
}