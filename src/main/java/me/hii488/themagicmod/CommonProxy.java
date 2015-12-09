package me.hii488.themagicmod;

import me.hii488.themagicmod.Handlers.GuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void registerRenders(){
		NetworkRegistry.INSTANCE.registerGuiHandler(TheMagicMod.modinstance, new GuiHandler());
	}

	
}
