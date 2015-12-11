package me.hii488.themagicmod.Items.Runes.BaseRunes;

import me.hii488.themagicmod.Registries.TMMTabRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseRuneItem extends Item{
	
	public BaseRuneItem(){
		super();
		this.setCreativeTab(TMMTabRegistry.MagicTab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public Item setUnlocalizedName(String par1Str) {
		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}
	
}
