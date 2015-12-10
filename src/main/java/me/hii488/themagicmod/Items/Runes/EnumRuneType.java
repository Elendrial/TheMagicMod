package me.hii488.themagicmod.Items.Runes;

import me.hii488.themagicmod.Entities.Projectiles.EntityBaseMagicShot;
import me.hii488.themagicmod.Entities.Projectiles.EntityExplodingMagicShot;
import net.minecraft.world.World;

public enum EnumRuneType {
	Explosive{
		@Override
		public EntityBaseMagicShot getMagicShot(World world){
			return new EntityExplodingMagicShot(world);
		}
	};

	public EntityBaseMagicShot getMagicShot(World world) {
		return null;
	}
}
