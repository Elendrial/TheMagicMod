package me.hii488.themagicmod.Registries;

import me.hii488.themagicmod.TheMagicMod;
import me.hii488.themagicmod.Entities.Projectiles.EntityExplodingMagicShot;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class TMMEntityRegistry {
	
	public static void regEntities(){
		createEntityNoEgg(EntityExplodingMagicShot.class, "MagicShotExploding");
	}
	
	
	public static void createEntityNoEgg(Class entityClass, String entityName){
		int randomId = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, TheMagicMod.modinstance, 64, 1, true);
	}
	
}
