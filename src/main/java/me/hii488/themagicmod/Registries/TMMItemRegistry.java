package me.hii488.themagicmod.Registries;

import me.hii488.themagicmod.References;
import me.hii488.themagicmod.Items.Wands.BrokenWand;
import me.hii488.themagicmod.Items.Wands.WoodenWand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class TMMItemRegistry {
	public static Item woodenwand;
	public static Item brokenwand;
	
	public static void regItems(){
		woodenwand = new WoodenWand();
		brokenwand = new BrokenWand();
	}
	
	public static void registerRender(){
		registerRenders(woodenwand);
		registerRenders(brokenwand);
	}
	
	public static void registerRenders(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
