package org.fleshserver.fleshServer.main.Parasites.Builders;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.GenericUtils;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

public class Rupter extends CaveSpider {
    public Rupter(EntityType<? extends CaveSpider> entitytypes, Level world) {
        super(entitytypes, world);
        this.getBukkitEntity().setCustomName(GenericUtils.format("&6Carrier"));
        EntityHelper.setMobHealth((org.bukkit.entity.LivingEntity) this.getBukkitEntity(),15);
        EntityHelper.setMobDamage((org.bukkit.entity.LivingEntity) this.getBukkitEntity(),5);
        EntityHelper.setMobArmor((org.bukkit.entity.LivingEntity) this.getBukkitEntity(),5);
        EntityHelper.setMobRange((org.bukkit.entity.LivingEntity) this.getBukkitEntity(),40);
        EntityHelper.setAgile((org.bukkit.entity.LivingEntity) this.getBukkitEntity());
        DataHandler.set(this.getBukkitEntity(),"rupter", PersistentDataType.STRING,"rupter");
        DataHandler.set(this.getBukkitEntity(),"assimilated", PersistentDataType.STRING,"assimilated");
        EntityHelper.addPotionEffect((org.bukkit.entity.LivingEntity) this.getBukkitEntity(), PotionEffectType.SPEED,1);
        this.setPersistenceRequired(false);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.6F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3,new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, (entityliving, worldserver) -> {
            return (ParasiteUtils.checkOrganicHostile(entityliving.getType()) || ParasiteUtils.checkOrganicPassive(entityliving.getType())) && !DataHandler.has(entityliving.getBukkitEntity(),"assimilated", PersistentDataType.STRING);
        }));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, IronGolem.class, true));
    }

    @Override
    public int getMaxFallDistance(){
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean doHurtTarget(ServerLevel worldserver, Entity entity) {
        if (super.doHurtTarget(worldserver, entity)) {
            if (entity instanceof net.minecraft.world.entity.LivingEntity) {
                ((net.minecraft.world.entity.LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.LUCK, 1200 , 0), this, EntityPotionEffectEvent.Cause.ATTACK);
            }
            return true;
        } else {
            return false;
        }
    }

}
