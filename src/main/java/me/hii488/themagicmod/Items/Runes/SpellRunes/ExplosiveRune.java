package me.hii488.themagicmod.Items.Runes.SpellRunes;

import me.hii488.themagicmod.Items.Runes.BaseRunes.BaseSpellRuneItem;
import me.hii488.themagicmod.Items.Runes.BaseRunes.EnumSpellRuneType;
import me.hii488.themagicmod.Registries.TMMTabRegistry;

public class ExplosiveRune extends BaseSpellRuneItem{
	
	public ExplosiveRune(){
		super();
		this.setCreativeTab(TMMTabRegistry.MagicTab);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("explosiveSpellRune");
		this.runeType = EnumSpellRuneType.Explosive;
	}
	
}
