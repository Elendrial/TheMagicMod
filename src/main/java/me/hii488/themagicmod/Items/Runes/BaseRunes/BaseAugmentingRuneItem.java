package me.hii488.themagicmod.Items.Runes.BaseRunes;

public class BaseAugmentingRuneItem extends BaseRuneItem{
	
	public BaseAugmentingRuneItem(){
		super();
	}
	
	protected int level = 0;
	protected int xp = 0;
	
	protected float affectedAreaModifier = 0;
	protected int affectedAreaModifierType = 0;
	
	protected float powerModifier = 0;
	protected int powerModifierType = 0;
	
	protected int maxTicksModifier = 0;
	protected int maxTicksModifierType = 0;
	
	protected float speedModifier = 0;
	protected int speedModifierType = 0;
	
	protected float gravityVelocityModifier = 0;
	protected int gravityVelocityModifierType = 0;
	
	protected boolean affectLiquids = false;
    protected boolean affectSolids = false;
    
	/* Modifier Types:
	 * 0: + 
	 * 1: *
	 * 2: + %
	 */
	
	public float getAffectedAreaModifier(){
		return affectedAreaModifier;
	}
	
	public int getAffectedAreaModifierType(){
		return affectedAreaModifierType;
	}
	
	public float getPowerModifier(){
		return affectedAreaModifier;
	}
	
	public int getPowerModifierType(){
		return affectedAreaModifierType;
	}
	
	public int getMaxTicksModifier(){
		return maxTicksModifier;
	}
	
	public int getMaxTicksModifierType(){
		return maxTicksModifierType;
	}

	public int getSpeedModifierType() {
		return speedModifierType;
	}

	public float getSpeedModifier() {
		return speedModifier;
	}
	
	public boolean isAffectLiquids() {
		return affectLiquids;
	}

	public boolean isAffectSolids() {
		return affectSolids;
	}

	public float getGravityVelocityModifier() {
		return gravityVelocityModifier;
	}

	public int getGravityVelocityModifierType() {
		return gravityVelocityModifierType;
	}
	
}
