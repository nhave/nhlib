package com.nhave.nhlib.main;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBinds
{
    public static KeyBinding toggle = new KeyBinding("key.nh.toggle", Keyboard.KEY_G, "key.nh.category");
}