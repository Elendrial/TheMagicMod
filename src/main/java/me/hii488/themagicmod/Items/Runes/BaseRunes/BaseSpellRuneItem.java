package me.hii488.themagicmod.Items.Runes.BaseRunes;

public class BaseSpellRuneItem extends BaseRuneItem{
	
	public BaseSpellRuneItem(){
		super();
	}
	
	protected BaseAugmentingRuneItem augmentingRunes[];
	protected EnumSpellRuneType runeType;
	
	protected int xp = 0;
	protected int baseLevel = 0;
	
	public int getLevel(){
		int level = baseLevel;
		for(int i = 0; i < this.augmentingRunes.length; i++){
			level += augmentingRunes[i].level;
		}
		
		return level;
		
	}
	
	public EnumSpellRuneType getRuneType(){
		return runeType;
	}
	
	public float getAffectedAreaModifier(){
		if(augmentingRunes != null){
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
			
			return f;
		}
		return 0f;
	}
	
	public float getPowerModifier(){
		if(augmentingRunes != null){
			float f = 0f;
			for(int i = 0; i < augmentingRunes.length; i++){
				switch(augmentingRunes[i].getPowerModifierType()){
				case 0:
					f += augmentingRunes[i].getPowerModifier();
					break;
				case 1:
					f *= augmentingRunes[i].getPowerModifier();
					break;
				case 2:
					f += f*augmentingRunes[i].getPowerModifier()/100;
					break;
				}
			}
			
			return f;
			}
		return 0f;
	}
	
	public int getMaxTicksModifier(){
		if(augmentingRunes != null){
			int f = 0;
			for(int i = 0; i < augmentingRunes.length; i++){
				switch(augmentingRunes[i].getMaxTicksModifierType()){
				case 0:
					f += augmentingRunes[i].getMaxTicksModifier();
					break;
				case 1:
					f *= augmentingRunes[i].getMaxTicksModifier();
					break;
				case 2:
					f += f*augmentingRunes[i].getMaxTicksModifier()/100;
					break;
				}
			}
			
			return f;
			}
		return 0;
	}

	public float getSpeedModifier() {
		if(augmentingRunes != null){
			float f = 0;
			for(int i = 0; i < augmentingRunes.length; i++){
				switch(augmentingRunes[i].getSpeedModifierType()){
				case 0:
					f += augmentingRunes[i].getSpeedModifier();
					break;
				case 1:
					f *= augmentingRunes[i].getSpeedModifier();
					break;
				case 2:
					f += f*augmentingRunes[i].getSpeedModifier()/100;
					break;
				}
			}
			
			return f;
			}
		return 0f;
	}
	
	public float getGravityVelocityModifier() {
		if(augmentingRunes != null){
			float f = 0;
			for(int i = 0; i < augmentingRunes.length; i++){
				switch(augmentingRunes[i].getGravityVelocityModifierType()){
				case 0:
					f += augmentingRunes[i].getGravityVelocityModifier();
					break;
				case 1:
					f *= augmentingRunes[i].getGravityVelocityModifier();
					break;
				case 2:
					f += f*augmentingRunes[i].getGravityVelocityModifier()/100;
					break;
				}
			}
			
			return f;
			}
		return 0f;
	}
	
	public boolean getAffectsSolids(){
		if(augmentingRunes != null){
			int i = 0;
			
			for(int j = 0; j < augmentingRunes.length; j++){
				if(augmentingRunes[j].isAffectSolids()){
					i += augmentingRunes[j].level;
				}
				else{
					i -= augmentingRunes[j].level;
				}
			}
			
			return i > 0;
			}
		return false;
	}
	
	public boolean getAffectsLiquids(){
		if(augmentingRunes != null){
			int i = 0;
			
			for(int j = 0; j < augmentingRunes.length; j++){
				if(augmentingRunes[j].isAffectLiquids()){
					i += augmentingRunes[j].level;
				}
				else{
					i -= augmentingRunes[j].level;
				}
			}
			
			return i > 0;
			}
		return false;
	}

}
