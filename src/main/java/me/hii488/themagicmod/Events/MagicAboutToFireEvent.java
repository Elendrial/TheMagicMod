package me.hii488.themagicmod.Events;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * ArrowNockEvent is fired when a player begins using a bow.<br>
 * This event is fired whenever a player begins using a bow in
 * ItemBow#onItemRightClick(ItemStack, World, EntityPlayer).<br>
 * <br>
 * {@link #result} contains the resulting ItemStack due to the use of the bow. <br>
 * <br>
 * This event is {@link Cancelable}.<br>
 * If this event is canceled, the player does not begin using the bow.<br>
 * <br>
 * This event does not have a result. {@link HasResult}<br>
 * <br>
 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
 **/
@Cancelable
public class MagicAboutToFireEvent extends PlayerEvent
{
    public ItemStack result;
    
    public MagicAboutToFireEvent(EntityPlayer player, ItemStack result)
    {
        super(player);
        this.result = result;
    }
}