package me.hii488.themagicmod.Registries;

import me.hii488.themagicmod.Entities.TileEntities.TileEntityWandAugmenter;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TMMTileEntitiesRegistry {
	
	public static void regTileEntities(){
		GameRegistry.registerTileEntity(TileEntityWandAugmenter.class, "wandaugmentertileentity");
	}
	
}
