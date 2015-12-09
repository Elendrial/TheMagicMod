package me.hii488.themagicmod.Entities.Projectiles;

import java.util.Random;

import me.hii488.themagicmod.CustomThings.CustomExplosion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExplodingMagicShot extends EntityBaseMagicShot{

	private static float increasedDest = 1;
	private static boolean flaming = false;
	private static boolean isDestructive = true;
	
	public Random rand = new Random();
	
	public EntityExplodingMagicShot(World worldIn) {
		super(worldIn);
	}

	public EntityExplodingMagicShot(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	public EntityExplodingMagicShot(World worldIn, EntityLivingBase shooterIn) {
		super(worldIn, shooterIn);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		for (int j = 0; j < 20; ++j){
			worldObj.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, rand.nextDouble()/5-0.1, rand.nextDouble()/5-0.1, rand.nextDouble()/5-0.1, new int[0]);
		}
	}
	
	protected void onImpact(MovingObjectPosition movingObjectPosition) {
		if (movingObjectPosition.entityHit != null){
            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 100);
        }
		
        CustomExplosion explosion = new CustomExplosion(worldObj, this, this.posX, this.posY, posZ, this.affectedAreaSize, this.power, increasedDest, flaming, isDestructive, this.affectLiquids, affectSolids);
        explosion.doExplosionA();
        explosion.doExplosionB(true, 0f);
        
        for (int j = 0; j < 20; ++j){
            this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, (rand.nextDouble()-0.5)*5, (rand.nextDouble()-0.5)*5, (rand.nextDouble()-0.5)*5, new int[0]);
        }
        
        if (!this.worldObj.isRemote){
            this.setDead();
        }
    }
	
	public void setincreasedDest(float increasedDest){
		this.increasedDest = increasedDest;
	}
	
	public float getincreasedDest(){
		return this.increasedDest;
	}
	
	public void setFlaming(boolean flaming){
		this.flaming = flaming;
	}
	
	public boolean getFlaming(){
		return flaming;
	}
	
	public void setDesructive(boolean smoking){
		this.isDestructive = smoking;
	}
	
	public boolean getDestructive(){
		return isDestructive;
	}
	
}
