package me.hii488.themagicmod.Items.Wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BrokenWand extends BaseWandItem{
	
	public BrokenWand(){
		super();
		this.setUnlocalizedName("brokenwand");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(!world.isRemote){
			player.addChatComponentMessage(new ChatComponentText("This wand has run out of power...").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA).setItalic(true)));
		}
		return stack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft){
		
	}
	
}
