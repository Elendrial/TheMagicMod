package me.hii488.themagicmod.Registries;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TMMTabRegistry {
	public static final CreativeTabs MagicTab = new CreativeTabs("TheMagicTab") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return TMMItemRegistry.woodenwand;
        }
    };
    
    
}
