package me.hii488.themagicmod.Items.Runes.AugmentingRunes;

import me.hii488.themagicmod.Items.Runes.BaseRunes.BaseAugmentingRuneItem;

public class TestRune extends BaseAugmentingRuneItem{
	
	public TestRune(){
		super();
		this.affectedAreaModifier = 10;
		this.affectedAreaModifierType = 0;
		this.affectLiquids = false;
		this.affectSolids = true;
		this.gravityVelocityModifier = 0;
		this.gravityVelocityModifierType = 1;
		this.level = 0;
		this.maxTicksModifier = 50;
		this.maxTicksModifierType = 0;
		this.powerModifier = 10;
		this.powerModifierType = 0;
		this.speedModifier = 1;
		this.speedModifierType = 1;
		this.xp = 0;
		this.setUnlocalizedName("testAugmentRune");
	}
	
}
