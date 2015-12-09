package me.hii488.themagicmod;

import me.hii488.themagicmod.Entities.Projectiles.EntityExplodingMagicShot;
import me.hii488.themagicmod.Entities.Render.RenderEntityExplodingMagicShot;
import me.hii488.themagicmod.Registries.TMMBlockRegistry;
import me.hii488.themagicmod.Registries.TMMItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenders(){
		super.registerRenders();
		TMMItemRegistry.registerRender();
		TMMBlockRegistry.registerRender();;
		RenderingRegistry.registerEntityRenderingHandler(EntityExplodingMagicShot.class, new RenderEntityExplodingMagicShot(Minecraft.getMinecraft().getRenderManager()));
	}
}
