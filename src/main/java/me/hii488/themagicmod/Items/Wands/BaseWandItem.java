package me.hii488.themagicmod.Items.Wands;

import me.hii488.themagicmod.References;
import me.hii488.themagicmod.Entities.Projectiles.EntityBaseMagicShot;
import me.hii488.themagicmod.Entities.Projectiles.EntityExplodingMagicShot;
import me.hii488.themagicmod.Events.MagicAboutToFireEvent;
import me.hii488.themagicmod.Items.Runes.BaseRuneItem;
import me.hii488.themagicmod.Items.Runes.BaseSpellRuneItem;
import me.hii488.themagicmod.Registries.TMMItemRegistry;
import me.hii488.themagicmod.Registries.TMMTabRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseWandItem extends Item{
	
	private float baseAffectedAreaSize = 1;
	private int baseMaxTicks = 0;
	
	protected float affectedAreaSize;
	protected int maxTicks;
	
	protected BaseSpellRuneItem[] runes;
	protected int maxRunes;
	
	protected int selectedSlot = 0;
	
	
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
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		MagicAboutToFireEvent event = new MagicAboutToFireEvent(player, stack);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return event.result;
		
        if(player.capabilities.isCreativeMode || true){
        	player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        
        return stack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft){
        
		boolean flag = playerIn.capabilities.isCreativeMode;
        
		if (flag || true){
        	
			if(stack.getItemDamage() == stack.getMaxDamage()-1){
				int slot = playerIn.inventory.currentItem;
				
				BrokenWand bWand = (BrokenWand) TMMItemRegistry.brokenwand;

				stack.damageItem(1, playerIn);
				
				playerIn.inventory.mainInventory[slot] = new ItemStack(bWand, 1, 0);
				
			}
			else{
				stack.damageItem(1, playerIn);
			}
        	
        	EntityBaseMagicShot shot = new EntityExplodingMagicShot(worldIn, playerIn);
        	shot.setAffectedAreaSize(this.affectedAreaSize);
        	shot.setMaxTicksAlive(this.maxTicks);
        	shot.setAffectsSolids(true);
        	
        	if (!worldIn.isRemote)
            {
                worldIn.spawnEntityInWorld(shot);
            }
        	
        }
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
	
	public BaseSpellRuneItem[] getRunes(){
		return this.runes;
	}
	
	public void setRunes(BaseSpellRuneItem[] b){
		this.runes = b;
	}
	
}