package de.dasug.minecraft.railscanner.tiles;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRailScanner extends TileEntity implements IPeripheral  {
	private World theWorld;
	private boolean listening = false;
	private ForgeDirection direction = ForgeDirection.UP;
	
	public TileEntityRailScanner() {
	}

	@Override
	public String getType() {
		return "rail_scanner";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] {"listen", "stop", "isListening"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws LuaException,
			InterruptedException {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		switch(method) {
			/* 	listen():
			 *	sensor starts looking for minecarts and sending events about them
			*/
			case 0:
				setListening(true);
				return new Object[] {true};
				
			/* 	stop():
			 *	sensor stops looking for minecarts and won't send any additional events
			*/
			case 1:
				setListening(false);
				return new Object[] {true};
				
			/* isListening():
			 * returns true if the sensor is currently listening, otherwise false
			 */
			case 2:
				return new Object[] {isListening()};
			default:
				throw new LuaException("unimplemented method called");
		}
	}

	@Override
	public void attach(IComputerAccess computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IComputerAccess computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(IPeripheral other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ForgeDirection getDirection() {
		return direction;
	}

	public void setDirection(ForgeDirection direction) {
    	this.direction = direction;
    }
    
    /**
     * Called when the corresponding block is placed in the world.
     * @param entityliving
     * @param stack
     */
	public void onBlockPlacedBy(EntityLivingBase entityliving, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	@Override
	public void writeToNBT(NBTTagCompound saveData)
	{
	   super.writeToNBT(saveData);
	   saveData.setByte("direction", (byte) direction.ordinal());
	   saveData.setBoolean("listening", listening);
	}

	@Override
	public void readFromNBT(NBTTagCompound saveData)
	{
	   super.readFromNBT(saveData);
	   this.direction = ForgeDirection.getOrientation((int) saveData.getByte("direction"));
	   if(saveData.hasKey("listening"))
		   this.listening = saveData.getBoolean("listening");
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
		readFromNBT(pkt.func_148857_g());
    }

}
