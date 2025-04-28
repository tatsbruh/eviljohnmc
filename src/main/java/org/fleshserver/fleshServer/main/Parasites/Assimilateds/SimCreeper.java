package org.fleshserver.fleshServer.main.Parasites.Assimilateds;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.GenericUtils;

public class SimCreeper extends Creeper {

    public SimCreeper(EntityType<? extends Creeper> entitytypes, Level world) {
        super(entitytypes, world);
        createAttributes();
        this.explosionRadius = 6;
        this.getBukkitEntity().setCustomName(GenericUtils.format("&6Hijacked Creeper"));
        EntityHelper.setMobHealth((LivingEntity) this.getBukkitEntity(),30);;
        EntityHelper.setMobArmor((LivingEntity) this.getBukkitEntity(),4);
        EntityHelper.setMobRange((LivingEntity) this.getBukkitEntity(),100);
        EntityHelper.setAgile((LivingEntity) this.getBukkitEntity());
        DataHandler.set(this.getBukkitEntity(),"simcreeper", PersistentDataType.STRING,"simcreeper");
        DataHandler.set(this.getBukkitEntity(),"assimilated", PersistentDataType.STRING,"assimilated");
        EntityHelper.addPotionEffect((LivingEntity) this.getBukkitEntity(), PotionEffectType.SPEED,0);
        this.setPersistenceRequired(false);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SwellGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
    }
    @Override
    public int getMaxFallDistance(){
        return Integer.MAX_VALUE;
    }


}
