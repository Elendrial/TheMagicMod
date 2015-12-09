package me.hii488.themagicmod.Events;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * ArrowLooseEvent is fired when a player stops using a bow.<br>
 * This event is fired whenever a player stops using a bow in
 * ItemBow#onPlayerStoppedUsing(ItemStack, World, EntityPlayer, int).<br>
 * <br>
 * {@link #bow} contains the ItemBow ItemStack that was used in this event.<br>
 * {@link #charge} contains the value for how much the player had charged before stopping the shot.<br>
 * <br>
 * This event is {@link Cancelable}.<br>
 * If this event is canceled, the player does not stop using the bow.<br>
 * <br>
 * This event does not have a result. {@link HasResult}<br>
 * <br>
 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
 **/
@Cancelable
public class MagicFiredEvent extends PlayerEvent
{
    public final ItemStack wand;
    public int charge;
    
    public MagicFiredEvent(EntityPlayer player, ItemStack wand, int charge)
    {
        super(player);
        this.wand = wand;
        this.charge = charge;
    }
}