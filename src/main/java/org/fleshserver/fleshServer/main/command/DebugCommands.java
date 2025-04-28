package org.fleshserver.fleshServer.main.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Subcommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.fleshserver.fleshServer.main.Parasites.Assimilateds.*;
import org.fleshserver.fleshServer.main.Parasites.Builders.Buglin;
import org.fleshserver.fleshServer.main.Parasites.Builders.Roach;

import org.fleshserver.fleshServer.main.Parasites.Builders.Rupter;
import org.fleshserver.fleshServer.main.Parasites.Primitive.Golem;
import org.fleshserver.fleshServer.main.misc.NMSUtils;

@CommandAlias("parasites|staff")
public class DebugCommands extends BaseCommand {
    @Subcommand("summon")
    @CommandCompletion("buglin|roach|rupter|sim_cow|sim_sheep|sim_chicken|sim_pig|sim_horse|sim_villager|sim_zombie|sim_spider|sim_enderman|hij_creeper|hij_skeleton")
    public void summon(CommandSender sender,String[] args){
        if(sender instanceof Player p && args.length > 0){
            switch (args[0].toLowerCase()){
                case "buglin" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    Buglin buglin = new Buglin(EntityType.SILVERFISH,nmsw);
                    buglin.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(buglin, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "roach" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    Roach e = new Roach(EntityType.SILVERFISH,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }case "rupter" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    Rupter e = new Rupter(EntityType.CAVE_SPIDER,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_cow" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimCow testMob = new SimCow(EntityType.COW,nmsw);
                    testMob.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(testMob, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_pig" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimPig e = new SimPig(EntityType.PIG,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_sheep" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimSheep e = new SimSheep(EntityType.SHEEP,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_chicken" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimChicken e = new SimChicken(EntityType.CHICKEN,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_horse" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimHorse e = new SimHorse(EntityType.HORSE,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_villager" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimVillager e = new SimVillager(EntityType.ZOMBIE_VILLAGER,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_zombie" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimZombie testMob = new SimZombie(EntityType.ZOMBIE,nmsw);
                    testMob.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(testMob, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_spider" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimSpider testMob = new SimSpider(EntityType.SPIDER,nmsw);
                    testMob.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(testMob, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "sim_enderman" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimEnderman testMob = new SimEnderman(EntityType.ENDERMAN,nmsw);
                    testMob.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(testMob, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "hij_creeper" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimCreeper e = new SimCreeper(EntityType.CREEPER,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
                case "hij_skeleton" ->{
                    ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
                    SimSkeleton e = new SimSkeleton(EntityType.SKELETON,nmsw);
                    e.setPos(p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ());
                    nmsw.addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);
                }
            }
        }
    }
    @Subcommand("cockblock")
    public void cock(CommandSender sender){
        if(sender instanceof Player p){
            ServerLevel nmsw = ((CraftWorld)p.getLocation().getWorld()).getHandle();
            NMSUtils.spawnNMSentity(nmsw,p.getLocation(),new Golem(EntityType.IRON_GOLEM,nmsw),EntityType.IRON_GOLEM, CreatureSpawnEvent.SpawnReason.NATURAL);
        }
    }
}
