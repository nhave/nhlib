package com.nhave.nhlib.blocks;

import java.util.ArrayList;
import java.util.List;

import com.nhave.nhlib.api.block.ITooltipBlock;
import com.nhave.nhlib.helpers.ItemHelper;
import com.nhave.nhlib.tiles.TileEntityToolStation;
import com.nhave.nhlib.util.StringUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockToolStation extends Block implements ITooltipBlock
{
	public IIcon[] blockIcons;
	
	public BlockToolStation()
	{
		super(Material.iron);
		this.setHardness(50F);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcons = new IIcon[4];
		this.blockIcons[0] = reg.registerIcon("nhcore:ToolStation_Top");
		this.blockIcons[1] = reg.registerIcon("nhcore:ToolStation_Bottom");
		this.blockIcons[2] = reg.registerIcon("nhcore:ToolStation_Side");
		this.blockIcons[3] = reg.registerIcon("nhcore:ToolStation_Front");
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side == 3 && meta == 0 ? this.blockIcons[3] : (side == 1 ? this.blockIcons[0] : (side == 0 ? this.blockIcons[1] : (side != meta ? this.blockIcons[2] : this.blockIcons[3])));
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack)
    {
        int l = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }
	
	@Override
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityToolStation();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntityToolStation tile = (TileEntityToolStation) world.getTileEntity(x, y, z);
		if (side == 1 && tile != null && !player.isSneaking() && world.isAirBlock(x, y + 1, z))
		{
			player.swingItem();
			return tile.onTileActivated(world, x, y, z, player);
		}
		else if (player.getCurrentEquippedItem() != null && ItemHelper.isToolWrench(player, player.getCurrentEquippedItem(), x, y, z))
		{
			if (player.isSneaking())
			{
				dismantleBlock(player, world, x, y, z);
				player.swingItem();
				ItemHelper.useWrench(player, player.getCurrentEquippedItem(), x, y, z);
				return !world.isRemote;
			}
			else
			{
				int meta = world.getBlockMetadata(x, y, z);
				
				if (meta == 2) meta = 5;
				else if (meta == 5) meta = 3;
				else if (meta == 3) meta = 4;
				else if (meta == 4) meta = 2;
				else meta = 2;
				
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
				player.swingItem();
				ItemHelper.useWrench(player, player.getCurrentEquippedItem(), x, y, z);
				return !world.isRemote;
			}
		}
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!world.isRemote)
        {
    		TileEntityToolStation tile = (TileEntityToolStation) world.getTileEntity(x, y, z);
    		if (world.getTileEntity(x, y, z) != null && !world.isAirBlock(x, y, z))
    		{
    			ItemStack stack = tile.getItemStack();
    			if (stack != null) dropBlockAsItem(world, x, y, z, stack);
    			tile.clearItemStack();
    		}
        }
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		if (!world.isRemote)
        {
    		TileEntityToolStation tile = (TileEntityToolStation) world.getTileEntity(x, y, z);
    		if (world.getTileEntity(x, y, z) != null)
    		{
    			ItemStack stack = tile.getItemStack();
    			if (stack != null) dropBlockAsItem(world, x, y, z, stack);
    		}
        }
	}
	
	public void dismantleBlock(EntityPlayer thePlayer, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
    	List drops = block.getDrops(world, x, y, z, metadata, 0);
    	block.onBlockHarvested(world, x, y, z, metadata, thePlayer);
	    world.setBlockToAir(x, y, z);
        
        if (!world.isRemote)
        {
        	ArrayList<? extends ItemStack> items = (ArrayList<? extends ItemStack>) drops;
        	for (ItemStack stack : items)
        	{
        		dropBlockAsItem(world, x, y, z, stack);
            }
        }
	}

	public void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack)
	{
		float f = 0.3F;
    	double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
    	double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
    	double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
    	EntityItem theItem = new EntityItem(world, x + x2, y + y2, z + z2, stack);
    	theItem.delayBeforeCanPickup = 10;
    	world.spawnEntityInWorld(theItem);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		list.add(StringUtils.BOLD + StatCollector.translateToLocal("tooltip.block.machine"));
		list.add(StringUtils.LIGHT_BLUE + StatCollector.translateToLocal("tooltip.rarity.rare"));
	}
}