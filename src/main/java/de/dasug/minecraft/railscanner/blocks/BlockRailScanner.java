/*
 * This class uses snippets of Code by the Railcraft Project
 * using the following License: http://railcraft.info/wiki/info:license
 * 
 * The Original Railcraft Code can be found at:
 * https://github.com/CovertJaguar/Railcraft
 */
package de.dasug.minecraft.railscanner.blocks;

import de.dasug.minecraft.railscanner.RailScanner;
import de.dasug.minecraft.railscanner.tiles.TileEntityRailScanner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRailScanner extends BlockContainer {

	public BlockRailScanner() {
		super(Material.rock);
		
		setResistance(4.5F);
        setHardness(2.0F);
        setStepSound(soundTypeStone);
		
		setBlockName(RailScanner.MODID + "_" + "railScanner");
		setCreativeTab(CreativeTabs.tabTransport);
		setBlockTextureName(RailScanner.MODID + ":" + RailScanner.MODID + "_" + "railScanner");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityRailScanner();
	}
	
	@Override
    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        return true;
    }
	
	@Override
    public int damageDropped(int meta) {
        return meta;
    }
	
	// Determine direction here
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
        TileEntity tile = world.getTileEntity(i, j, k);
        if (tile instanceof TileEntityRailScanner) {
            ((TileEntityRailScanner) tile).setDirection(ForgeDirection.UP/*MiscTools.getSideClosestToPlayer(world, i, j, k, entityliving)*/);
            ((TileEntityRailScanner) tile).onBlockPlacedBy(entityliving, stack);
        }
    }
    
    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityRailScanner) {
        	TileEntityRailScanner detector = (TileEntityRailScanner) tile;
            if (detector.getDirection() == axis)
                detector.setDirection(axis.getOpposite());
            else
                detector.setDirection(axis);
            world.markBlockForUpdate(x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {
        return ForgeDirection.VALID_DIRECTIONS;
    }
	
}
