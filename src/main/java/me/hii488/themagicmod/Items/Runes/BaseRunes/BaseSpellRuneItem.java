package me.hii488.themagicmod.Items.Runes.BaseRunes;

public class BaseSpellRuneItem extends BaseRuneItem{
	
	protected BaseAugmentingRuneItem augmentingRunes[];
	protected EnumSpellRuneType runeType;
	
	protected int xp;
	protected int baseLevel;
	protected int level;
	
	public int calculateLevel(){
		level = baseLevel;
		for(int i = 0; i < this.augmentingRunes.length; i++){
			level += augmentingRunes[i].level;
		}
		
		return level;
		
	}
	
	public EnumSpellRuneType getRuneType(){
		return runeType;
	}
	
	public float getAffectedAreaModifier(){
		float f = 0f;
		for(int i = 0; i < augmentingRunes.length; i++){
			
			/* 0: + 
			 * 1: *
			 * 2: + %
			 */
			switch(augmentingRunes[i].getAffectedAreaModifierType()){
			case 0:
				f += augmentingRunes[i].getAffectedAreaModifier();
				break;
			case 1:
				f *= augmentingRunes[i].getAffectedAreaModifier();
				break;
			case 2:
				f += f*augmentingRunes[i].getAffectedAreaModifier()/100;
				break;
			}
		}
		
		return 0f;
	}
	
	public int getMaxTicksModifier(){
		return 0;
	}
	
}
