package me.hii488.themagicmod.Blocks;

import java.util.Random;

import me.hii488.themagicmod.TheMagicMod;
import me.hii488.themagicmod.Entities.TileEntities.TileEntityWandAugmenter;
import me.hii488.themagicmod.Handlers.GuiHandler;
import me.hii488.themagicmod.Registries.TMMTabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WandAugmenter extends BlockContainer{

	public WandAugmenter() {
		super(Material.iron);
		this.blockHardness = 40f;
		this.blockResistance = 40f;
		this.setCreativeTab(TMMTabRegistry.MagicTab);
		this.setUnlocalizedName("wandaugmenter");
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		TileEntityWandAugmenter te = (TileEntityWandAugmenter) worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, te);
		super.breakBlock(worldIn, pos, state);
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta){
		return new TileEntityWandAugmenter();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((TileEntityWandAugmenter) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}
	
	@Override
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
		worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, pos.getX() + 0.5, pos.getY() + 1.01, pos.getZ() + 0.5, rand.nextDouble()-0.5, rand.nextDouble(), rand.nextDouble()-0.5, new int[0]);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(TheMagicMod.modinstance, GuiHandler.TileEntityWandAugmenterGUI, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public int getRenderType() {
		return 3;
	}
	
	@Override
	public Block setUnlocalizedName(String par1Str) {
		GameRegistry.registerBlock(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

}
