package com.nhave.nhlib.renders;

import org.lwjgl.opengl.GL11;

import com.nhave.nhlib.tiles.TileEntityToolStation;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class RenderTileToolStation extends TileEntitySpecialRenderer
{
	private EntityItem entItem = null;
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float arg4)
	{
		TileEntityToolStation tileEntity = (TileEntityToolStation) tile;
		if (tileEntity.getItemStack() != null)
		{
			int meta = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
			ItemStack stack = tileEntity.getItemStack();
			EntityItem entItem = new EntityItem(tileEntity.getWorldObj(), x, y, z, stack);
			GL11.glPushMatrix();
				entItem.hoverStart = 0.0F;
				RenderItem.renderInFrame = true;
				
				if (stack.getItem() instanceof ItemBlock)
				{
					GL11.glTranslatef((float)x + 0.5F, (float)y + 0.99F, (float)z + 0.5F);

					if (meta == 5) GL11.glRotatef(90, 0, 1, 0);
					else if (meta == 4) GL11.glRotatef(270, 0, 1, 0);
					else if (meta == 2) GL11.glRotatef(180, 0, 1, 0);
				}
				else
				{
					GL11.glTranslatef((float)x + 0.5F, (float)y + 1.02F, (float)z + 0.5F);
					GL11.glScalef(1.8F, 1.8F, 1.8F);

					if (meta == 5)
					{
						GL11.glTranslatef(0.2F, 0F, 0F);
						GL11.glRotatef(90, 0, 1, 0);
					}
					else if (meta == 4)
					{
						GL11.glTranslatef(-0.2F, 0F, 0F);
						GL11.glRotatef(270, 0, 1, 0);
					}
					else if (meta == 3)
					{
						GL11.glTranslatef(0F, 0F, 0.2F);
					}
					else if (meta == 2)
					{
						GL11.glTranslatef(0F, 0F, -0.2F);
						GL11.glRotatef(180, 0, 1, 0);
					}
					
					GL11.glRotatef(-90, 1, 0, 0);
				}
				
				RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
				RenderItem.renderInFrame = false;
			GL11.glPopMatrix();
		}
	}
}