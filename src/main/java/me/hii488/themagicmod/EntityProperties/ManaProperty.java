package me.hii488.themagicmod.EntityProperties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ManaProperty implements IExtendedEntityProperties{
	
	private static final String identifier = "magicmodMana";
	
	// PROPERTIES ========
	
	private final EntityPlayer player;
	private int mana;
	
	
	// "Main methods" ====
	
	public ManaProperty(EntityPlayer player) {
		this.player = player;
	    this.mana = 100;
    }
	
	@Override
	public void saveNBTData(NBTTagCompound nbt) {
	    nbt.setInteger("mana", this.getMana());
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {
	    if (nbt.hasKey("mana", 3))
	        this.setMana(nbt.getInteger("mana"));
	}

	@Override
	public void init(Entity entity, World world) {
		
	}
	
	
	// Helper Methods ====
	
	public static ManaProperty get(EntityPlayer player) {
	    return (ManaProperty) player.getExtendedProperties(identifier);
	}

	public static void register(EntityPlayer player) {
	    player.registerExtendedProperties(identifier, new ManaProperty(player));
	}

	public boolean isServerSide() {
	    return this.player instanceof EntityPlayerMP;
	}

	
	// GET/SET Methods ===
	
	public void setMana(int mana) {
	    this.mana = mana;
	}

	public int getMana() {
	    return this.mana;
	}
	
}
