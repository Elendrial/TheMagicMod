package me.hii488.themagicmod.Items.Runes.BaseRunes;

public class BaseAugmentingRuneItem extends BaseRuneItem{
	
	protected int level;
	protected int xp;
	
	protected float affectedAreaModifier;
	protected int affectedAreaModifierType;
	
	protected int maxTicksModifier;
	protected int maxTicksModifierType;
	
	public float getAffectedAreaModifier(){
		return affectedAreaModifier;
	}
	
	public int getAffectedAreaModifierType(){
		return affectedAreaModifierType;
	}
	
	
	public int getMaxTicksModifier(){
		return maxTicksModifier;
	}
	
	/* 0: + 
	 * 1: *
	 * 2: + %
	 */
	public int getMaxTicksModifierType(){
		return maxTicksModifierType;
	}
	
}
