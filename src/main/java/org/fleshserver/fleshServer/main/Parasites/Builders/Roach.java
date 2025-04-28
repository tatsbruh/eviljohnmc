package org.fleshserver.fleshServer.main.Parasites.Builders;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.GenericUtils;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

public class Roach extends Silverfish {
    public Roach(EntityType<? extends Silverfish> entitytypes, Level world) {
        super(entitytypes, world);
        this.getBukkitEntity().setCustomName(GenericUtils.format("&6Roach"));
        EntityHelper.setMobHealth((LivingEntity) this.getBukkitEntity(),5);
        EntityHelper.setMobDamage((LivingEntity) this.getBukkitEntity(),20);
        EntityHelper.setMobArmor((LivingEntity) this.getBukkitEntity(),2);
        DataHandler.set(this.getBukkitEntity(),"roach", PersistentDataType.STRING,"roach");
        DataHandler.set(this.getBukkitEntity(),"assimilated", PersistentDataType.STRING,"assimilated");
        EntityHelper.addPotionEffect((LivingEntity) this.getBukkitEntity(), PotionEffectType.SPEED,1);
        this.setPersistenceRequired(false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Player.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0, false));
        this.targetSelector.addGoal(2,new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, (entityliving, worldserver) -> {
            return ParasiteUtils.checkNonOrganicHostile(entityliving.getType()) && !DataHandler.has(entityliving.getBukkitEntity(),"assimilated", PersistentDataType.STRING);
        }));
    }
    @Override
    public boolean doHurtTarget(ServerLevel worldserver, Entity entity) {
        if (super.doHurtTarget(worldserver, entity)) {
            if (entity instanceof net.minecraft.world.entity.LivingEntity) {
                this.discard(EntityRemoveEvent.Cause.PLUGIN);
            }
            return true;
        } else {
            return false;
        }
    }
}
