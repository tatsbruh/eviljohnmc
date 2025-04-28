package org.fleshserver.fleshServer.main.Parasites.Assimilateds;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.GenericUtils;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

import java.lang.reflect.Field;

import static org.fleshserver.fleshServer.main.misc.NMSUtils.registerGenericAttribute;

public class SimChicken extends Chicken {
    private static Field attributeMap;
    public SimChicken(EntityType<? extends Chicken> entitytypes, Level world) {
        super(entitytypes, world);
        try {
            registerGenericAttribute(this.getBukkitEntity(), Attribute.ATTACK_DAMAGE,attributeMap);
            registerGenericAttribute(this.getBukkitEntity(), Attribute.ATTACK_KNOCKBACK,attributeMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.getBukkitEntity().setCustomName(GenericUtils.format("&6Infected Chicken"));
        EntityHelper.setMobHealth((LivingEntity) this.getBukkitEntity(),14);
        EntityHelper.setMobDamage((LivingEntity) this.getBukkitEntity(),6);
        EntityHelper.setMobArmor((LivingEntity) this.getBukkitEntity(),4);
        EntityHelper.setMobRange((LivingEntity) this.getBukkitEntity(),40);
        EntityHelper.setAgile((LivingEntity) this.getBukkitEntity());
        DataHandler.set(this.getBukkitEntity(),"simcock", PersistentDataType.STRING,"simcock");
        DataHandler.set(this.getBukkitEntity(),"assimilated", PersistentDataType.STRING,"assimilated");
        EntityHelper.addPotionEffect((LivingEntity) this.getBukkitEntity(), PotionEffectType.SPEED,1);
        this.setPersistenceRequired(false);
    }
    static {
        try {
            attributeMap = AttributeMap.class.getDeclaredField("attributes");
            attributeMap.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3,new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, (entityliving, worldserver) -> {
            return (ParasiteUtils.checkOrganicPassive(entityliving.getType()) || ParasiteUtils.checkOrganicHostile(entityliving.getType())) && !DataHandler.has(entityliving.getBukkitEntity(),"assimilated", PersistentDataType.STRING);
        }));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, IronGolem.class, true));
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
