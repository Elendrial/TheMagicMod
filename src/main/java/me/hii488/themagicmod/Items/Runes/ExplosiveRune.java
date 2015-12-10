package me.hii488.themagicmod.Items.Runes;

import me.hii488.themagicmod.Registries.TMMTabRegistry;

public class ExplosiveRune extends BaseSpellRuneItem{
	
	public ExplosiveRune(){
		super();
		this.setCreativeTab(TMMTabRegistry.MagicTab);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("explosiveSpellRune");
		this.runeType = EnumRuneType.Explosive;
	}
	
}
