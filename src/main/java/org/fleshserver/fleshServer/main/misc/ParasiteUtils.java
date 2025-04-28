package org.fleshserver.fleshServer.main.misc;

import net.minecraft.world.entity.EntityType;
import org.bukkit.*;

public class ParasiteUtils {
    public static void handleAssimilation(Location l){
        l.getWorld().spawnParticle(Particle.EXPLOSION,l, 5);
        l.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR,l, 15);
        l.getWorld().playSound(l, Sound.ENTITY_ZOMBIE_INFECT, SoundCategory.MASTER,10,1);
    }

    public static boolean checkOrganicHostile(EntityType entityType) {
        if (entityType == EntityType.CAVE_SPIDER || entityType == EntityType.DROWNED || entityType == EntityType.ELDER_GUARDIAN || entityType == EntityType.ENDERMAN
                || entityType == EntityType.EVOKER || entityType == EntityType.GUARDIAN || entityType == EntityType.HOGLIN || entityType == EntityType.HUSK
                || entityType == EntityType.PIGLIN || entityType == EntityType.PIGLIN_BRUTE || entityType == EntityType.PILLAGER || entityType == EntityType.RAVAGER
                || entityType == EntityType.SPIDER || entityType == EntityType.VINDICATOR || entityType == EntityType.WITCH || entityType == EntityType.ZOGLIN || entityType == EntityType.ZOMBIE
                || entityType == EntityType.ZOMBIE_VILLAGER || entityType == EntityType.ZOMBIFIED_PIGLIN) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkOrganicPassive(EntityType entityType) {
        if (entityType == EntityType.ARMADILLO  || entityType == EntityType.AXOLOTL || entityType == EntityType.CAMEL || entityType == EntityType.CAT
        || entityType == EntityType.CHICKEN || entityType == EntityType.COW || entityType == EntityType.DOLPHIN || entityType == EntityType.DONKEY || entityType == EntityType.FOX
        || entityType == EntityType.FROG || entityType == EntityType.GLOW_SQUID || entityType == EntityType.GOAT || entityType == EntityType.HORSE || entityType == EntityType.LLAMA
        || entityType == EntityType.MOOSHROOM || entityType == EntityType.MULE || entityType == EntityType.OCELOT || entityType == EntityType.PANDA || entityType == EntityType.PIG
        || entityType == EntityType.POLAR_BEAR || entityType == EntityType.RABBIT || entityType == EntityType.SHEEP || entityType == EntityType.SNIFFER || entityType == EntityType.SQUID
        || entityType == EntityType.TURTLE || entityType == EntityType.TRADER_LLAMA || entityType == EntityType.VILLAGER || entityType == EntityType.WANDERING_TRADER || entityType == EntityType.WOLF) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkNonOrganicHostile(EntityType entityType) {
        if (entityType == EntityType.BOGGED || entityType == EntityType.BREEZE || entityType == EntityType.CREEPER || entityType == EntityType.CREAKING
        || entityType == EntityType.IRON_GOLEM || entityType == EntityType.MAGMA_CUBE || entityType == EntityType.SHULKER || entityType == EntityType.SKELETON ||
        entityType == EntityType.SLIME || entityType == EntityType.SNOW_GOLEM || entityType == EntityType.STRAY || entityType == EntityType.WARDEN || entityType == EntityType.WITHER_SKELETON) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkFlyingMobs(EntityType entityType) {
        if (entityType == EntityType.ALLAY || entityType == EntityType.BEE || entityType == EntityType.BAT || entityType == EntityType.BLAZE || entityType == EntityType.PHANTOM || entityType == EntityType.PARROT
        || entityType == EntityType.GHAST) {
            return true;
        } else {
            return false;
        }
    }
}
