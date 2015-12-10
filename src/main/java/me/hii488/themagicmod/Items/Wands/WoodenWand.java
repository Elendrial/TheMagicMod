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
		
		this.maxRunes = 1;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
        return 10;
    }
}
