package me.hii488.themagicmod.Items.Runes.BaseRunes;

import me.hii488.themagicmod.Entities.Projectiles.EntityBaseMagicShot;
import me.hii488.themagicmod.Entities.Projectiles.EntityExplodingMagicShot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public enum EnumSpellRuneType {
	Explosive{
		@Override
		public EntityBaseMagicShot getMagicShot(World world){
			return new EntityExplodingMagicShot(world);
		}
		@Override
		public EntityBaseMagicShot getMagicShot(World world, EntityPlayer player) {
			return new EntityExplodingMagicShot(world, player);
		}
	};

	public EntityBaseMagicShot getMagicShot(World world) {
		return null;
	}
	
	public EntityBaseMagicShot getMagicShot(World world, EntityPlayer player) {
		return null;
	}
}
