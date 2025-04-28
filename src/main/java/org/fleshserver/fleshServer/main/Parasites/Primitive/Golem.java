package org.fleshserver.fleshServer.main.Parasites.Primitive;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.bukkit.block.BlockType;
import org.bukkit.craftbukkit.v1_21_R3.event.CraftEventFactory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.fleshserver.fleshServer.main.Parasites.Pathfinders.NewMeleeGoal;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.ParasiteUtils;

import java.util.Iterator;

public class Golem extends IronGolem {
    public Golem(EntityType<? extends IronGolem> entitytypes, Level world) {
        super(entitytypes, world);
        EntityHelper.setAgile((LivingEntity) this.getBukkitEntity());
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new NewMeleeGoal(this, 1.0, false));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3,new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, (entityliving, worldserver) -> {
            return (ParasiteUtils.checkOrganicPassive(entityliving.getType()) || ParasiteUtils.checkOrganicHostile(entityliving.getType()) || ParasiteUtils.checkNonOrganicHostile(entityliving.getType())) && !DataHandler.has(entityliving.getBukkitEntity(),"assimilated", PersistentDataType.STRING);
        }));
    }
    @Override
    public void aiStep(){
        super.aiStep();
        Level world = this.level();
        if (world instanceof ServerLevel) {
            ServerLevel worldserver = (ServerLevel)world;
            if (this.horizontalCollision && worldserver.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                boolean flag = false;
                AABB axisalignedbb = this.getBoundingBox().inflate(0.5);
                Iterator iterator = BlockPos.betweenClosed(Mth.floor(axisalignedbb.minX), Mth.floor(axisalignedbb.minY), Mth.floor(axisalignedbb.minZ), Mth.floor(axisalignedbb.maxX), Mth.floor(axisalignedbb.maxY), Mth.floor(axisalignedbb.maxZ)).iterator();

                label71:
                while(true) {
                    BlockPos blockposition;
                    Block block;
                    do {
                        do {
                            if (!iterator.hasNext()) {
                                if (!flag && this.onGround()) {
                                    this.jumpFromGround();
                                }
                                break label71;
                            }

                            blockposition = (BlockPos)iterator.next();
                            BlockState iblockdata = worldserver.getBlockState(blockposition);
                            block = iblockdata.getBlock();
                        } while(!(block == Blocks.DIRT));
                    } while(!CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, Blocks.AIR.defaultBlockState()));

                    flag = worldserver.destroyBlock(blockposition, true, this) || flag;
                }
            }
        }
    }
}
