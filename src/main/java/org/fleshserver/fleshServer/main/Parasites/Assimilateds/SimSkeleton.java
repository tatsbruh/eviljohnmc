package org.fleshserver.fleshServer.main.Parasites.Assimilateds;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.misc.*;

public class SimSkeleton extends Skeleton {
    private int inPowderSnowTime;
    public SimSkeleton(EntityType<? extends Skeleton> entitytypes, Level world) {
        super(entitytypes, world);
        this.getBukkitEntity().setCustomName(GenericUtils.format("&6Hijacked Skeleton"));
        EntityHelper.setMobHealth((LivingEntity) this.getBukkitEntity(),22);
        EntityHelper.setMobDamage((LivingEntity) this.getBukkitEntity(),7);
        EntityHelper.setMobArmor((LivingEntity) this.getBukkitEntity(),4);
        EntityHelper.setMobRange((LivingEntity) this.getBukkitEntity(),40);
        EntityHelper.setAgile((LivingEntity) this.getBukkitEntity());
        ((org.bukkit.entity.Skeleton)this.getBukkitEntity()).setShouldBurnInDay(false);
        DataHandler.set(this.getBukkitEntity(),"simskeleton", PersistentDataType.STRING,"simskeleton");
        DataHandler.set(this.getBukkitEntity(),"assimilated", PersistentDataType.STRING,"assimilated");
        EntityHelper.addPotionEffect((LivingEntity) this.getBukkitEntity(), PotionEffectType.SPEED,0);
        EntityHelper.setMainHand((LivingEntity) this.getBukkitEntity(),new ItemBuilder(Material.BOW).build());
        this.setPersistenceRequired(false);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3,new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, (entityliving, worldserver) -> {
            return (ParasiteUtils.checkOrganicHostile(entityliving.getType()) || ParasiteUtils.checkFlyingMobs(entityliving.getType())) && !DataHandler.has(entityliving.getBukkitEntity(),"assimilated", PersistentDataType.STRING);
        }));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, IronGolem.class, true));
    }

    @Override
    public int getMaxFallDistance(){
        return Integer.MAX_VALUE;
    }

    @Override
    protected int getHardAttackInterval() {
        return 10;
    }
    @Override
    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && !this.isNoAi()) {
            this.inPowderSnowTime = -1;
            this.setFreezeConverting(false);
        }
        super.tick();
    }

    @Override
    public boolean doHurtTarget(ServerLevel worldserver, Entity entity) {
        if (super.doHurtTarget(worldserver, entity)) {
            if (entity instanceof net.minecraft.world.entity.LivingEntity) {
                if(Math.random() <= 0.2) {
                    ((net.minecraft.world.entity.LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.LUCK, 5 * 20, 0), this, EntityPotionEffectEvent.Cause.ATTACK);
                }
            }

            return true;
        } else {
            return false;
        }
    }

}
