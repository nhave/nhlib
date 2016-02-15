package com.nhave.nhlib.tiles;

import com.nhave.nhlib.events.ToolStationCraftingEvent;
import com.nhave.nhlib.events.ToolStationUpdateEvent;
import com.nhave.nhlib.helpers.ItemHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityToolStation extends TileEntity
{
	private ItemStack item = null;
	
	public boolean onTileActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (this.item == null && player.getCurrentEquippedItem() != null)
		{
			this.item = player.getCurrentEquippedItem().copy();
			this.item.stackSize = 1;
			player.getCurrentEquippedItem().stackSize--;
			sync();
			return true;
		}
		else if (this.item != null)
		{
			ToolStationUpdateEvent evt = new ToolStationUpdateEvent(item, player.getCurrentEquippedItem());
			MinecraftForge.EVENT_BUS.post(evt);
			if (evt.isCanceled()) return false;
			if (evt.output != null && player.getCurrentEquippedItem().getItem() == evt.mod.getItem() && player.getCurrentEquippedItem().getItemDamage() == evt.mod.getItemDamage())
			{
				ItemStack mod = player.getCurrentEquippedItem().copy();
				ItemStack input = this.item.copy();
				if (evt.materialCost > 0)
				{
					if (evt.materialCost > player.getCurrentEquippedItem().stackSize) return false;
					else player.getCurrentEquippedItem().stackSize -= evt.materialCost;
				}
				this.item = evt.output.copy();
				sync();
				MinecraftForge.EVENT_BUS.post(new ToolStationCraftingEvent(player, evt.output.copy(), input, mod));
				return true;
			}
			else
			{
				ItemHelper.addItemToPlayer(player, item.copy());
				this.item = null;
				sync();
				return true;
			}
		}
		return false;
	}
	
	private void sync()
	{
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty();
	}
	
	public ItemStack getItemStack()
	{
		return this.item;
	}
	
	public void clearItemStack()
	{
		this.item = null;
		sync();
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		writeSyncableDataToNBT(tag);
	}

	public void writeSyncableDataToNBT(NBTTagCompound tag)
	{
		NBTTagList tagList = new NBTTagList();
		int i = 0;
		if(this.item != null)
		{
			NBTTagCompound tag1 = new NBTTagCompound();
			
			tag1.setByte("Slot", (byte)i);
			this.item.writeToNBT(tag1);
			
			tagList.appendTag(tag1);
		}
		
		tag.setTag("ITEM", tagList);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		readSyncableDataFromNBT(tag);
	}
	
	public void readSyncableDataFromNBT(NBTTagCompound tag)
	{
		NBTTagList items = tag.getTagList("ITEM", tag.getId());

		int i = 0;
		NBTTagCompound item = items.getCompoundTagAt(i);
        int j = item.getByte("Slot");
        ItemStack stack = ItemStack.loadItemStackFromNBT(item);
        
        if(stack != null)
        {
        	this.item = stack.copy();
        }
        else this.item = null;
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeSyncableDataToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, syncData);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readSyncableDataFromNBT(pkt.func_148857_g());
	}
}