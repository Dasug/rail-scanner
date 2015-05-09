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
		return new TileEntityRailScanner(world);
	}
	
	@Override
    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        return true;
    }
	
	@Override
    public int damageDropped(int meta) {
        return meta;
    }
	
}
