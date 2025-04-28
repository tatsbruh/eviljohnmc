package org.fleshserver.fleshServer.main.Parasites.Builders;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.v1_21_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.GenericUtils;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

public class Buglin extends Silverfish {
    private int ticksAlive;
    public Buglin(EntityType<? extends Silverfish> entitytypes, Level world) {
        super(entitytypes, world);
        this.ticksAlive = 0;
        this.getBukkitEntity().setCustomName(GenericUtils.format("&6Buglin"));
        EntityHelper.setMobHealth((LivingEntity) this.getBukkitEntity(),8);
        EntityHelper.setMobArmor((LivingEntity) this.getBukkitEntity(),2);
        DataHandler.set(this.getBukkitEntity(),"assimilated", PersistentDataType.STRING,"assimilated");
        EntityHelper.addPotionEffect((LivingEntity) this.getBukkitEntity(), PotionEffectType.SPEED,0);
        this.setPersistenceRequired(false);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Player.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
    }

    @Override
    public void tick(){
        super.tick();
        if(this.ticksAlive <= 1600){
            this.ticksAlive++;
        }else{
            Rupter e = new Rupter(EntityType.CAVE_SPIDER,this.level());
            e.setPos(this.getX(),this.getY(),this.getZ());
            this.level().addFreshEntity(e, CreatureSpawnEvent.SpawnReason.NATURAL);

            this.discard();
        }
    }

}
