package me.hii488.themagicmod.Items.Wands;

import me.hii488.themagicmod.References;
import me.hii488.themagicmod.Events.MagicAboutToFireEvent;
import me.hii488.themagicmod.Items.Runes.BaseRuneItem;
import me.hii488.themagicmod.Registries.TMMTabRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseWandItem extends Item{
	
	private float baseAffectedAreaSize = 0;
	private int baseMaxTicks = 0;
	
	protected float affectedAreaSize;
	protected int maxTicks;
	
	protected BaseRuneItem[] runes;
	
	
	public BaseWandItem(){
		super();
		this.setCreativeTab(TMMTabRegistry.MagicTab);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}
	
	@Override
	public Item setUnlocalizedName(String par1Str) {
		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}
	
	
	public float getBaseAffectedAreaSize(){
		return baseAffectedAreaSize;
	}
	
	public void setBaseAffectedAreaSize(float f){
		this.baseAffectedAreaSize = f;
	}
	
	public int getBaseMaxTicks(){
		return this.baseMaxTicks;
	}
	
	public void setBaseMaxTicks(int i){
		this.baseMaxTicks = i;
	}
	
	public BaseRuneItem[] getRunes(){
		return this.runes;
	}
	
	public void setRunes(BaseRuneItem[] b){
		this.runes = b;
	}
	
}