package org.fleshserver.fleshServer.main.events;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.Parasites.Assimilateds.*;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

public class PotionEffects implements Listener {
    @EventHandler
    public void potChange(EntityPotionEffectEvent e){
        var entity = e.getEntity();
        var action = e.getAction();
        var newest = e.getNewEffect();
        var old = e.getOldEffect();
        var cause = e.getCause();
        if(entity instanceof LivingEntity l){
                if(!DataHandler.has(l,"assimilated", PersistentDataType.STRING) && !l.isDead() && l.getHealth() > 0 && l.isValid() && old != null && old.getType() == PotionEffectType.LUCK && newest == null && action == EntityPotionEffectEvent.Action.REMOVED){
                    if(l.getType() == EntityType.ZOMBIE) {
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimZombie testMob = new SimZombie(net.minecraft.world.entity.EntityType.ZOMBIE, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.SPIDER || l.getType() == EntityType.CAVE_SPIDER){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimSpider testMob = new SimSpider(net.minecraft.world.entity.EntityType.SPIDER, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.ENDERMAN){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimEnderman testMob = new SimEnderman(net.minecraft.world.entity.EntityType.ENDERMAN, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.CHICKEN){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimChicken testMob = new SimChicken(net.minecraft.world.entity.EntityType.CHICKEN, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.PIG){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimPig testMob = new SimPig(net.minecraft.world.entity.EntityType.PIG, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.COW || l.getType() == EntityType.MOOSHROOM){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimCow testMob = new SimCow(net.minecraft.world.entity.EntityType.COW, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.SHEEP){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimSheep testMob = new SimSheep(net.minecraft.world.entity.EntityType.SHEEP, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.HORSE || l.getType() == EntityType.MULE || l.getType() == EntityType.DONKEY){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimHorse testMob = new SimHorse(net.minecraft.world.entity.EntityType.HORSE, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }else if(l.getType() == EntityType.VILLAGER || l.getType() == EntityType.ZOMBIE_VILLAGER){
                        var loc = l.getLocation().clone();
                        ParasiteUtils.handleAssimilation(loc);
                        ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                        SimVillager testMob = new SimVillager(net.minecraft.world.entity.EntityType.ZOMBIE_VILLAGER, nmsw);
                        testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                        nmsw.addFreshEntity(testMob);
                        l.remove();
                    }
                }

        }
    }
}
