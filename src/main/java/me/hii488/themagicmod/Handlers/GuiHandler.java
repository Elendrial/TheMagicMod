package me.hii488.themagicmod.Handlers;

import me.hii488.themagicmod.Entities.TileEntities.TileEntityWandAugmenter;
import me.hii488.themagicmod.GUIs.ContainerWandAugmenter;
import me.hii488.themagicmod.GUIs.GuiWandAugmenter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	public static final int TileEntityWandAugmenterGUI = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == TileEntityWandAugmenterGUI)
			return new ContainerWandAugmenter(player.inventory, (TileEntityWandAugmenter) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == TileEntityWandAugmenterGUI)
			return new GuiWandAugmenter(player.inventory, (TileEntityWandAugmenter) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

}
