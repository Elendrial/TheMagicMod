package me.hii488.themagicmod.Registries;

import me.hii488.themagicmod.References;
import me.hii488.themagicmod.Blocks.WandAugmenter;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class TMMBlockRegistry {
	
	public static Block wandAugmenter;
	
	public static void regBlocks(){
		wandAugmenter = new WandAugmenter();
	}
	
	public static void registerRender(){
		registerRenders(wandAugmenter);
	}
	
	public static void registerRenders(Block block){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(References.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
