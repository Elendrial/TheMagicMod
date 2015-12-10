package me.hii488.themagicmod.Entities.TileEntities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class TileEntityWandAugmenter extends TileEntity implements IInventory{

	/** The ItemStacks that hold the items currently being used in the Tile Entity 
	 * Slot 0 = wand, 1->6 = runes */
    private ItemStack[] wandAugmenterInventory = new ItemStack[3];
    private String wandAugmenterCustomName;
	
    public TileEntityWandAugmenter() {
        this.wandAugmenterInventory = new ItemStack[this.getSizeInventory()];
    }

    public String getName() {
        return this.hasCustomName() ? this.wandAugmenterCustomName : "Wand Augmenter";
    }

    @Override
    public boolean hasCustomName() {
        return this.wandAugmenterCustomName != null && !this.wandAugmenterCustomName.equals("");
    }

    @Override
    public IChatComponent getDisplayName() {
        return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName());
    }
    
    @Override
    public int getSizeInventory() {
        return 9;
    }
    
    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.wandAugmenterInventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0) {
                    this.setInventorySlotContents(index, null);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();
            
        if (stack != null && stack.stackSize == 0)
            stack = null;

        this.wandAugmenterInventory[index] = stack;
        this.markDirty();
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
    }
    
    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }
    
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }
    
    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }
    
    @Override
    public void clear() {
        for (int i = 0; i < this.getSizeInventory(); i++)
            this.setInventorySlotContents(i, null);
    }
    
    public String getCustomName() {
		return this.wandAugmenterCustomName;
	}

	public void setCustomName(String customName) {
	    this.wandAugmenterCustomName = customName;
	}
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
        }

        if (nbt.hasKey("CustomName", 8)) {
            this.setCustomName(nbt.getString("CustomName"));
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.getStackInSlot(i) != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        nbt.setTag("Items", list);

        if (this.hasCustomName()) {
            nbt.setString("CustomName", this.getCustomName());
        }
    }

	@Override
	public String getCommandSenderName() {
        return this.hasCustomName() ? this.wandAugmenterCustomName : "container.wandAugmenter";
	}
	    
    /*
    @Override
	public int getSizeInventory() {
		return this.wandAugmenterInventory.length;
	}
    
    @Override
    public ItemStack getStackInSlot(int index){
        if(index < wandAugmenterInventory.length){
        	return this.wandAugmenterInventory[index];
        }
        return null;
    }
    
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerWandAugmenter(playerInventory, this);
	}

	public String getGuiID() {
		return "themagicmod:wandAugmenter";
	}

	@Override
	public String getCommandSenderName(){
        return this.hasCustomName() ? this.wandAugmenterCustomName : "container.wandAugmenter";
    }

	@Override
	public boolean hasCustomName(){
        return this.wandAugmenterCustomName != null && this.wandAugmenterCustomName.length() > 0;
    }
	
	public void setCustomInventoryName(String p_145951_1_){
        this.wandAugmenterCustomName = p_145951_1_;
    }

	@Override
	public ItemStack decrStackSize(int index, int count){
        if (this.wandAugmenterInventory[index] != null)
        {
            if (this.wandAugmenterInventory[index].stackSize <= count)
            {
                ItemStack itemstack1 = this.wandAugmenterInventory[index];
                this.wandAugmenterInventory[index] = null;
                return itemstack1;
            }
            else
            {
                ItemStack itemstack = this.wandAugmenterInventory[index].splitStack(count);

                if (this.wandAugmenterInventory[index].stackSize == 0)
                {
                    this.wandAugmenterInventory[index] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int index){
        if (this.wandAugmenterInventory[index] != null)
        {
            ItemStack itemstack = this.wandAugmenterInventory[index];
            this.wandAugmenterInventory[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int index, ItemStack stack){
		if(this.getStackInSlot(0).getItem() instanceof BaseWandItem && index != 0){
			BaseWandItem bwand = (BaseWandItem) this.getStackInSlot(0).getItem();
			
			stack = new ItemStack(bwand.getRunes()[index-1]);
		}
		
		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();

            this.markDirty();
        }
	}


	@Override
	public int getInventoryStackLimit(){
        return 1;
    }

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override public void openInventory(EntityPlayer player) {}
	@Override public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 0 && stack.getItem() instanceof BaseWandItem){
			return true;
		}
		if(this.getStackInSlot(0).getItem() instanceof BaseWandItem && stack.getItem() instanceof BaseRuneItem){
			return true;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.wandAugmenterInventory.length; ++i)
        {
            this.wandAugmenterInventory[i] = null;
            this.markDirty();
        }
	}

	public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.wandAugmenterInventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.wandAugmenterInventory.length)
            {
                this.wandAugmenterInventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.wandAugmenterCustomName = compound.getString("CustomName");
        }
    }
	
	public void writeToNBT(NBTTagCompound compound)
	{
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.wandAugmenterInventory.length; ++i)
        {
            if (this.wandAugmenterInventory[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.wandAugmenterInventory[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.wandAugmenterCustomName);
        }
    }
	
	public int addItemStack(ItemStack stack)
    {
        for (int i = 0; i < this.wandAugmenterInventory.length; ++i)
        {
            if (this.wandAugmenterInventory[i] == null || this.wandAugmenterInventory[i].getItem() == null)
            {
                this.setInventorySlotContents(i, stack);
                return i;
            }
        }

        return -1;
    }

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}
	*/
}
