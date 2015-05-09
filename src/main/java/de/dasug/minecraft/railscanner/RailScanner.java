/**
 * 
 */
package de.dasug.minecraft.railscanner;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dan200.computercraft.api.ComputerCraftAPI;
import de.dasug.minecraft.railscanner.blocks.BlockRailScanner;
import de.dasug.minecraft.railscanner.tiles.TileEntityRailScanner;

/**
 * @author Dasug
 *
 */
@Mod(modid = RailScanner.MODID, version = RailScanner.VERSION, dependencies="required-after:ComputerCraft;after:CCTurtle")
public class RailScanner {
	public static final String MODID = "railscanner";
    public static final String VERSION = "0.1";
    
    public static Block railScanner;
    
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
		// Create RailScanner Block
    	railScanner = new BlockRailScanner();
    	GameRegistry.registerBlock(railScanner, "railscanner.blocks.railScanner");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	GameRegistry.registerTileEntity(TileEntityRailScanner.class, "railscanner.tile.railScanner");
    	ComputerCraftAPI.registerPeripheralProvider(new PeripheralProvider());
    }
}
