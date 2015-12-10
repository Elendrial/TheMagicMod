package me.hii488.themagicmod.GUIs;

import me.hii488.themagicmod.Entities.TileEntities.TileEntityWandAugmenter;
import me.hii488.themagicmod.GUIs.Slots.SlotSpellRune;
import me.hii488.themagicmod.GUIs.Slots.SlotWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWandAugmenter extends Container{

    private final TileEntityWandAugmenter tileWandAugmenter;
    
    /*
	 * SLOTS:
	 *
	 * Tile Entity 0-6 ........ 0  - 6
	 * Player Inventory 7-33 .. 7  - 33
	 * Player Inventory 0-6 ... 34 - 42
	 */
    public ContainerWandAugmenter(IInventory playerInventory, TileEntityWandAugmenter wandAugmenterInventory)
    {
        this.tileWandAugmenter = (TileEntityWandAugmenter) wandAugmenterInventory;
        
   /*     for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlotToContainer(new Slot(tileWandAugmenter, x + y * 3, 62 + x * 18, 17 + y * 18));
            }
        }*/
        
        this.addSlotToContainer(new SlotWand(tileWandAugmenter, 0, 79, 65-16));
        
        this.addSlotToContainer(new SlotSpellRune(tileWandAugmenter, 1, 60, 34-16));
        this.addSlotToContainer(new SlotSpellRune(tileWandAugmenter, 2, 39, 65-16));
        this.addSlotToContainer(new SlotSpellRune(tileWandAugmenter, 3, 60, 96-16));
        this.addSlotToContainer(new SlotSpellRune(tileWandAugmenter, 4, 98, 96-16));
        this.addSlotToContainer(new SlotSpellRune(tileWandAugmenter, 5, 121, 65-16));
        this.addSlotToContainer(new SlotSpellRune(tileWandAugmenter, 6, 98, 34-16));
        
        // Player Inventory, Slot 9-35, Slot IDs 9-35
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18 + 18));
            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 36-44
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 142 + 18));
        }
    }
    
    @Override
	public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileWandAugmenter.isUseableByPlayer(playerIn);
	}
    
    @Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < 6) {
	            // From TE Inventory to Player Inventory
	            if (!this.mergeItemStack(current, 6, 42, true))
	                return null;
	        } else {
	            // From Player Inventory to TE Inventory
	            if (!this.mergeItemStack(current, 0, 6, false))
	                return null;
	        }

	        if (current.stackSize == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.stackSize == previous.stackSize)
	            return null;
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}
    
}
