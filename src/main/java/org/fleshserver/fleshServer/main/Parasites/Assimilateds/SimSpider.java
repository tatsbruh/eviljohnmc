package org.fleshserver.fleshServer.main.Parasites.Assimilateds;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.GenericUtils;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

public class SimSpider extends Spider {
    public SimSpider(EntityType<? extends Spider> entitytypes, Level world) {
        super(entitytypes, world);
        this.getBukkitEntity().setCustomName(GenericUtils.format("&6Infected Spider"));
        EntityHelper.setMobHealth((org.bukkit.entity.LivingEntity) this.getBukkitEntity(),22);
        EntityHelper.setMobDamage((org.bukkit.entity.LivingEntity) this.getBukkitEntity(),9);
        EntityHelper.setMobArmor((org.bukkit.entity.LivingEntity) this.getBukkitEntity(),3);
        EntityHelper.setMobRange((LivingEntity) this.getBukkitEntity(),40);
        EntityHelper.setAgile((LivingEntity) this.getBukkitEntity());
        DataHandler.set(this.getBukkitEntity(),"simspider", PersistentDataType.STRING,"simspider");
        DataHandler.set(this.getBukkitEntity(),"assimilated", PersistentDataType.STRING,"assimilated");
        EntityHelper.addPotionEffect((org.bukkit.entity.LivingEntity) this.getBukkitEntity(), PotionEffectType.SPEED,0);
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
            return (ParasiteUtils.checkOrganicPassive(entityliving.getType()) || ParasiteUtils.checkOrganicHostile(entityliving.getType())) && !DataHandler.has(entityliving.getBukkitEntity(),"assimilated", PersistentDataType.STRING);
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
