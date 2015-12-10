package me.hii488.themagicmod.GUIs.Slots;

import me.hii488.themagicmod.Items.Wands.BaseWandItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotWand extends Slot{
	public SlotWand(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem().getUnlocalizedName().toLowerCase().contains("wand") && !stack.getItem().getUnlocalizedName().equals("wandaugmenter");
    }

}
