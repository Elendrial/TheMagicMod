package me.hii488.themagicmod.Items.Wands;

import me.hii488.themagicmod.Entities.Projectiles.EntityBaseMagicShot;
import me.hii488.themagicmod.Entities.Projectiles.EntityExplodingMagicShot;
import me.hii488.themagicmod.Events.MagicAboutToFireEvent;
import me.hii488.themagicmod.Items.Runes.BaseRuneItem;
import me.hii488.themagicmod.Registries.TMMItemRegistry;
import me.hii488.themagicmod.Registries.TMMTabRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class WoodenWand extends BaseWandItem{
	
	public WoodenWand(){
		super();		
		this.setMaxDamage(50);
		this.setUnlocalizedName("woodenwand");
		
		this.setBaseAffectedAreaSize(5f);
		this.setBaseMaxTicks(10);
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
	public int getMaxItemUseDuration(ItemStack stack){
        return 10;
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
}
