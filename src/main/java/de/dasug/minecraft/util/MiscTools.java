/*
 * This class uses snippets of Code by the Railcraft Project
 * using the following License: http://railcraft.info/wiki/info:license
 * 
 * The original Railcraft Code can be found at:
 * https://github.com/CovertJaguar/Railcraft
 */
package de.dasug.minecraft.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class MiscTools {
	/**
     * Returns the side closest to the player. Used in placement logic for
     * blocks.
     *
     * @param world
     * @param i
     * @param j
     * @param k
     * @param entityplayer
     * @return a side
     */
    public static ForgeDirection getSideClosestToPlayer(World world, int i, int j, int k, EntityLivingBase entityplayer) {
        if (MathHelper.abs((float) entityplayer.posX - (float) i) < 2.0F && MathHelper.abs((float) entityplayer.posZ - (float) k) < 2.0F) {
            double d = (entityplayer.posY + 1.82D) - (double) entityplayer.yOffset;
            if (d - (double) j > 2D)
                return ForgeDirection.UP;
            if ((double) j - d > 0.0D)
                return ForgeDirection.DOWN;
        }
        int dir = MathHelper.floor_double((double) ((entityplayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        switch (dir) {
            case 0:
                return ForgeDirection.NORTH;
            case 1:
                return ForgeDirection.EAST;
            case 2:
                return ForgeDirection.SOUTH;
        }
        return dir != 3 ? ForgeDirection.DOWN : ForgeDirection.WEST;
    }
}
