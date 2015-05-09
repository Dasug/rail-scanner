package de.dasug.minecraft.railscanner.tiles;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRailScanner extends TileEntity implements IPeripheral  {
	private World theWorld;
	private boolean listening = false;
	private ForgeDirection direction = ForgeDirection.UP;
	
	public TileEntityRailScanner(World world) {
		theWorld = world;
	}

	@Override
	public String getType() {
		return "rail_scanner";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] {"listen", "stop"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws LuaException,
			InterruptedException {
		switch(method) {
			/* 	listen():
				sensor starts looking for minecarts and sending events about them
			*/
			case 0:
				listening = true;
				return new Object[] {true};
				
			/* 	stop():
				sensor stops looking for minecarts and won't send any additional events
			*/
			case 1:
				listening = false;
				return new Object[] {true};
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
	
	@Override
    public void writeToNBT(NBTTagCompound par1)
    {
       super.writeToNBT(par1);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
       super.readFromNBT(par1);
    }
    
    public void setDirection(ForgeDirection direction) {
    	this.direction = direction;
    }

}
