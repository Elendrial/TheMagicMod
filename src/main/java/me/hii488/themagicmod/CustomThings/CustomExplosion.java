package me.hii488.themagicmod.CustomThings;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomExplosion extends Explosion{
	
	/** whether or not the explosion sets fire to blocks around it */
    private final boolean isFlaming;
    /** whether or not this explosion blows stuff up */
    private final boolean isDestructive;
    private final Random explosionRNG;
    private final World worldObj;
    private final double explosionX;
    private final double explosionY;
    private final double explosionZ;
    private final Entity exploder;
    private final float explosionSize;
    private final List<BlockPos> affectedBlockPositions;
    private final Map<EntityPlayer, Vec3> playerKnockbackMap;
    private final Vec3 position;
    
    private final float explosionIntensity;
    private final float explosionIncreasedDestruction;
    private final boolean destroysLiquids;
    private final boolean destroysSolids;
    private Random rand = new Random();

    @SideOnly(Side.CLIENT)
    public CustomExplosion(World worldIn, Entity exploder, double x, double y, double z, float size, float intensity, float increasedDest, List<BlockPos> p_i45752_10_, boolean destLiquids, boolean destroysSolids)
    {
        this(worldIn, exploder, x, y, z, size, intensity, increasedDest, false, true, p_i45752_10_, destLiquids, destroysSolids);
    }

    @SideOnly(Side.CLIENT)
    public CustomExplosion(World worldIn, Entity exploder, double x, double y, double z, float size, float intensity, float increasedDest, boolean flaming, boolean smoking, List<BlockPos> p_i45753_12_, boolean destLiquids, boolean destroysSolids)
    {
        this(worldIn, exploder, x, y, z, size, intensity, increasedDest, flaming, smoking, destLiquids, destroysSolids);
        this.affectedBlockPositions.addAll(p_i45753_12_);
    }

    public CustomExplosion(World worldIn, Entity exploder, double x, double y, double z, float size, float intensity, float increasedDest, boolean flaming, boolean smoking, boolean destLiquids, boolean destroysSolids)
    {
        super(worldIn, exploder, x, y, z, size, flaming, smoking);
    	this.explosionRNG = new Random();
        this.affectedBlockPositions = Lists.<BlockPos>newArrayList();
        this.playerKnockbackMap = Maps.<EntityPlayer, Vec3>newHashMap();
        this.worldObj = worldIn;
        this.exploder = exploder;
        this.explosionSize = size;
        this.explosionX = x;
        this.explosionY = y;
        this.explosionZ = z;
        this.isFlaming = flaming;
        this.isDestructive = smoking;
        this.position = new Vec3(explosionX, explosionY, explosionZ);
        this.explosionIntensity = intensity;
        this.explosionIncreasedDestruction = increasedDest;
        this.destroysLiquids = destLiquids;
        this.destroysSolids = destroysSolids;
    }
    
    /**
     * Does the first part of the explosion (sets blocks to be destroyed)
     */
    public void doExplosionA()
    {
        Set<BlockPos> set = Sets.<BlockPos>newHashSet();
        
        int checkingRadius = (int) (explosionSize * 3);
        
        for(int i = (int) -checkingRadius; i < checkingRadius; i++){
        	for(int j = (int) -checkingRadius; j < checkingRadius; j++){
        		if(explosionY - j >= 0){
	        		for(int k =(int) -checkingRadius; k < checkingRadius; k++){
	        			
	        			if(isDestroyed(i,j,k)){
	        				int x = (int) (i + this.explosionX);
	        				int y = (int) (j + this.explosionY);
	        				int z = (int) (k + this.explosionZ);
	        				
	        				BlockPos blockpos = new BlockPos(x, y, z);
	                        IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
	        				
	                        if ((this.exploder == null || this.exploder.verifyExplosion(this, this.worldObj, blockpos, iblockstate, 1f)))
	                        {
	                            set.add(blockpos);
	                        }
	                        
	        			}
	        		}
        		}
        	}
        }
        
        
        this.affectedBlockPositions.addAll(set);
        float f3 = this.explosionSize * 2.0F;
        int k1 = MathHelper.floor_double(this.explosionX - (double)f3 - 1.0D);
        int l1 = MathHelper.floor_double(this.explosionX + (double)f3 + 1.0D);
        int i2 = MathHelper.floor_double(this.explosionY - (double)f3 - 1.0D);
        int i1 = MathHelper.floor_double(this.explosionY + (double)f3 + 1.0D);
        int j2 = MathHelper.floor_double(this.explosionZ - (double)f3 - 1.0D);
        int j1 = MathHelper.floor_double(this.explosionZ + (double)f3 + 1.0D);
        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, f3);
        Vec3 vec3 = new Vec3(this.explosionX, this.explosionY, this.explosionZ);
        
        
        for (int k2 = 0; k2 < list.size(); ++k2)
        {
            Entity entity = (Entity)list.get(k2);

            if (!entity.isImmuneToExplosions())
            {
                double d12 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)f3;

                if (d12 <= 1.0D)
                {
                    double d5 = entity.posX - this.explosionX;
                    double d7 = entity.posY + (double)entity.getEyeHeight() - this.explosionY;
                    double d9 = entity.posZ - this.explosionZ;
                    double d13 = (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);

                    if (d13 != 0.0D)
                    {
                        d5 = d5 / d13;
                        d7 = d7 / d13;
                        d9 = d9 / d13;
                        double d14 = (double)this.worldObj.getBlockDensity(vec3, entity.getEntityBoundingBox());
                        double d10 = (1.0D - d12) * d14;
                        entity.attackEntityFrom(DamageSource.setExplosionSource(this), (float)((int)((d10 * d10 + d10) / 2.0D * 8.0D * (double)f3 + 1.0D)));
                        double d11 = EnchantmentProtection.func_92092_a(entity, d10);
                        entity.motionX += d5 * d11;
                        entity.motionY += d7 * d11;
                        entity.motionZ += d9 * d11;

                        if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.disableDamage)
                        {
                            this.playerKnockbackMap.put((EntityPlayer)entity, new Vec3(d5 * d10, d7 * d10, d9 * d10));
                        }
                    }
                }
            }
        }
        
        
        
        
    }
    
    // (x-a)^2 + (y-b)^2 + (z-c)^2 = r^2
    // (a, b, c) = centre
    // i = x, j = y, l = z 
    private boolean isDestroyed(int x, int y, int z) {
        double distance = (double)MathHelper.sqrt_double(x * x + y * y + z * z);
    	
        BlockPos blockpos = new BlockPos(x + this.explosionX, y + this.explosionY, z + this.explosionZ);
        IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
        
    	if(distance <= explosionSize * 1.1){
			return true;
		}
    	
    	double chance = 0;
    	if(explosionSize < 15){
    		chance = 1*explosionIntensity / ((distance-explosionSize) * (distance-explosionSize) / 2);
    	}
    	else{
        	chance = explosionIntensity*(explosionSize/10) / ((distance-explosionSize+(explosionIntensity/2 * 2)) * (distance-explosionSize+(explosionIntensity/2 * 2)));
    	}
    	//double chance = 0D;
    	if(this.rand.nextFloat() < chance){
    		return true;
    	}
		
		return false;
	}
    
    

	public void doExplosionB(boolean spawnParticles, float dropChance)
    {
        this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

        if (this.explosionSize >= 2.0F && this.isDestructive)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
        }
        else
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
        }

        if (this.isDestructive)
        {
            for (BlockPos blockpos : this.affectedBlockPositions)
            {
                Block block = this.worldObj.getBlockState(blockpos).getBlock();

                if (spawnParticles)
                {
                    double d0x = (double)((float)blockpos.getX() + this.worldObj.rand.nextFloat());
                    double d1y = (double)((float)blockpos.getY() + this.worldObj.rand.nextFloat());
                    double d2z = (double)((float)blockpos.getZ() + this.worldObj.rand.nextFloat());
                    double d3x = d0x - this.explosionX;
                    double d4y = d1y - this.explosionY;
                    double d5z = d2z - this.explosionZ;
                    double d6 = (double)MathHelper.sqrt_double(d3x * d3x + d4y * d4y + d5z * d5z);
                    d3x = d3x / d6;
                    d4y = d4y / d6;
                    d5z = d5z / d6;
                    double d7 = 0.5D / (d6 / (double)this.explosionSize + 0.1D);
                    d7 = d7 * (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
                    d3x = d3x * d7;
                    d4y = d4y * d7;
                    d5z = d5z * d7;
                    this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0x + this.explosionX * 1.0D) / 2.0D, (d1y + this.explosionY * 1.0D) / 2.0D, (d2z + this.explosionZ * 1.0D) / 2.0D, d3x, d4y, d5z, new int[0]);
                    this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0x, d1y, d2z, d3x, d4y, d5z, new int[0]);
                }

                if (block.getMaterial() != Material.air && block.getExplosionResistance(worldObj, blockpos, (Entity)null, this) < explosionIntensity * 20 * (rand.nextFloat() + 1) * explosionIncreasedDestruction)
                {
                	if((block.getMaterial().isLiquid() && destroysLiquids) || !block.getMaterial().isLiquid()){
                		if((block.getMaterial().isSolid() && destroysSolids) || !block.getMaterial().isSolid()){
		                    if (block.canDropFromExplosion(this))
		                    {	
		                    	if(dropChance / (this.explosionSize*this.explosionIntensity) >= 1)block.dropBlockAsItemWithChance(this.worldObj, blockpos, this.worldObj.getBlockState(blockpos), 1f, 0);
		                    	else block.dropBlockAsItemWithChance(this.worldObj, blockpos, this.worldObj.getBlockState(blockpos), dropChance / (this.explosionSize*this.explosionIntensity), 0);
		                    }
		
		                    block.onBlockExploded(this.worldObj, blockpos, this);
                		}
                	}
                }
            }
        }

        if (this.isFlaming)
        {
            for (BlockPos blockpos1 : this.affectedBlockPositions)
            {
                if (this.worldObj.getBlockState(blockpos1).getBlock().getMaterial() == Material.air && this.worldObj.getBlockState(blockpos1.down()).getBlock().isFullBlock() && this.explosionRNG.nextInt(3) == 0)
                {
                    this.worldObj.setBlockState(blockpos1, Blocks.fire.getDefaultState());
                }
            }
        }
    }
	
}
