package me.hii488.themagicmod.Items.Runes;

public class BaseSpellRuneItem extends BaseRuneItem{
	
	protected BaseAugmentingRuneItem augmentingRunes[];
	
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
	
}
