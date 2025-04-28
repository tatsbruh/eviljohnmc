package org.fleshserver.fleshServer.main.events;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.EntityEffect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.fleshserver.fleshServer.main.Parasites.Assimilateds.*;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.GenericUtils;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

import java.util.Random;

public class MobEvents implements Listener {

    @EventHandler
    public void lmao(EntityDamageByEntityEvent e){
        var entity = e.getEntity();
        var damager = e.getDamager();
        var cause = e.getCause();
        if(entity instanceof LivingEntity l){
            if(damager instanceof LivingEntity c){
                if(e.getFinalDamage() >= l.getHealth()){
                    if(DataHandler.has(c,"assimilated",PersistentDataType.STRING)){
                        if(!DataHandler.has(c,"roach",PersistentDataType.STRING)) {
                            if (l.getType() == EntityType.ZOMBIE) {
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimZombie testMob = new SimZombie(net.minecraft.world.entity.EntityType.ZOMBIE, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.CAVE_SPIDER || l.getType() == EntityType.SPIDER){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimSpider testMob = new SimSpider(net.minecraft.world.entity.EntityType.SPIDER, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.ENDERMAN){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimEnderman testMob = new SimEnderman(net.minecraft.world.entity.EntityType.ENDERMAN, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.CHICKEN){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimChicken testMob = new SimChicken(net.minecraft.world.entity.EntityType.CHICKEN, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.PIG){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimPig testMob = new SimPig(net.minecraft.world.entity.EntityType.PIG, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.COW || l.getType() == EntityType.MOOSHROOM){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimCow testMob = new SimCow(net.minecraft.world.entity.EntityType.COW, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.SHEEP){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimSheep testMob = new SimSheep(net.minecraft.world.entity.EntityType.SHEEP, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.HORSE || l.getType() == EntityType.MULE || l.getType() == EntityType.DONKEY){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimHorse testMob = new SimHorse(net.minecraft.world.entity.EntityType.HORSE, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }else if(l.getType() == EntityType.VILLAGER || l.getType() == EntityType.ZOMBIE_VILLAGER){
                                if (Math.random() >= 0.5) {
                                    l.setHealth(1);
                                    var loc = l.getLocation().clone();
                                    ParasiteUtils.handleAssimilation(loc);
                                    ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                    SimVillager testMob = new SimVillager(net.minecraft.world.entity.EntityType.ZOMBIE_VILLAGER, nmsw);
                                    testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                    nmsw.addFreshEntity(testMob);
                                    l.remove();
                                }
                            }
                        }else{
                            if (l.getType() == EntityType.CREEPER) {
                                l.setHealth(1);
                                var loc = l.getLocation().clone();
                                ParasiteUtils.handleAssimilation(loc);
                                ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                SimCreeper testMob = new SimCreeper(net.minecraft.world.entity.EntityType.CREEPER, nmsw);
                                testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                nmsw.addFreshEntity(testMob);
                                l.remove();
                            }else if(l.getType() == EntityType.SKELETON || l.getType() == EntityType.BOGGED || l.getType() == EntityType.STRAY){
                                l.setHealth(1);
                                var loc = l.getLocation().clone();
                                ParasiteUtils.handleAssimilation(loc);
                                ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                SimSkeleton testMob = new SimSkeleton(net.minecraft.world.entity.EntityType.SKELETON, nmsw);
                                testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                nmsw.addFreshEntity(testMob);
                                l.remove();
                            }else if(l.getType() == EntityType.IRON_GOLEM){
                                l.setHealth(1);
                                var loc = l.getLocation().clone();
                                ParasiteUtils.handleAssimilation(loc);
                                ServerLevel nmsw = ((CraftWorld) loc.getWorld()).getHandle();
                                SimIronGolem testMob = new SimIronGolem(net.minecraft.world.entity.EntityType.IRON_GOLEM, nmsw);
                                testMob.setPos(loc.getX(), loc.getY(), loc.getZ());
                                nmsw.addFreshEntity(testMob);
                                l.remove();
                            }
                        }
                    }
                }
                if(l instanceof Player p){
                    if(cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && DataHandler.has(c,"simcreeper",PersistentDataType.STRING)){
                        p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,200,2));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,600,0));
                    }
                    if(c.getType() == EntityType.ENDERMAN && DataHandler.has(c,"simenderman",PersistentDataType.STRING)){
                        if(Math.random() <= 0.25){
                            var list = c.getNearbyEntities(15, 15, 15);
                            var mob = list.get(new Random().nextInt(list.size()));
                            if (DataHandler.has(mob,"assimilated",PersistentDataType.STRING)) {
                                var lol = c.getLocation().clone();
                                p.playSound(p.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 10.0F, -1.0F);
                                c.teleport(mob.getLocation());
                                new BukkitRunnable() {
                                    public void run() {
                                        p.playSound(p.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 10.0F, -1.0F);
                                        c.teleport(lol);
                                        mob.teleport(lol);
                                        mob.playEffect(EntityEffect.TELEPORT_ENDER);
                                        c.playEffect(EntityEffect.TELEPORT_ENDER);
                                    }
                                }.runTaskLater(GenericUtils.getPlugin(), 5L);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void arrows(ProjectileLaunchEvent e){
        var proj = e.getEntity();
        var sht = proj.getShooter();
        if(proj instanceof Arrow a){
            if(DataHandler.has((PersistentDataHolder) sht,"simskeleton",PersistentDataType.STRING)){
                DataHandler.set(a,"assimilated",PersistentDataType.STRING,"assimilated");
            }
        }
    }
}
