package me.hii488.themagicmod.Entities.Render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityExplodingMagicShot extends Render{

	private static final ResourceLocation textures = new ResourceLocation("textures/entity/magic/exploding.png");
	
	public RenderEntityExplodingMagicShot(RenderManager renderManager) {
		super(renderManager);
	}

	  public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
	        GlStateManager.pushMatrix();
	        GlStateManager.translate((float)x, (float)y, (float)z);
	        GlStateManager.enableRescaleNormal();
	        GlStateManager.scale(0.5F, 0.5F, 0.5F);
	        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	        GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	        GlStateManager.disableRescaleNormal();
	        GlStateManager.popMatrix();
	        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	    }

	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textures;
	}

}
