package me.hii488.themagicmod;

import me.hii488.themagicmod.Registries.TMMBlockRegistry;
import me.hii488.themagicmod.Registries.TMMEntityRegistry;
import me.hii488.themagicmod.Registries.TMMItemRegistry;
import me.hii488.themagicmod.Registries.TMMTileEntitiesRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, version = References.VERSION)
public class TheMagicMod {
	
	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Instance(References.MODID)
    public static TheMagicMod modinstance;
	
	
    @EventHandler
	public void preInit(FMLPreInitializationEvent event) 
    {
    	TMMItemRegistry.regItems();
    	TMMBlockRegistry.regBlocks();
    	TMMEntityRegistry.regEntities();
    	TMMTileEntitiesRegistry.regTileEntities();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.registerRenders();
    }
    
}
