package me.hii488.themagicmod.GUIs;

import me.hii488.themagicmod.References;
import me.hii488.themagicmod.Entities.TileEntities.TileEntityWandAugmenter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiWandAugmenter extends GuiContainer{
	
	private IInventory playerInv;
    private TileEntityWandAugmenter tileWandAugmenter;
	
	public GuiWandAugmenter(IInventory playerInv, TileEntityWandAugmenter furnaceInv){
        super(new ContainerWandAugmenter(playerInv, furnaceInv));
        
        this.playerInv = playerInv;
        this.tileWandAugmenter = furnaceInv;
        
        this.xSize = 176;
		this.ySize = 166;
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(References.MODID + "textures/gui/container/tileentitywandaugmenter.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		String s = this.tileWandAugmenter.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, 88 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);		//#404040
        this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);   
	}
}
