package me.hii488.themagicmod.GUIs.Slots;

import me.hii488.themagicmod.Items.Runes.BaseRuneItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRune extends Slot{
	
	public SlotRune(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem().getUnlocalizedName().contains("rune");
    }
	
}
