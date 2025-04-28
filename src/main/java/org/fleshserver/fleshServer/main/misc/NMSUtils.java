package org.fleshserver.fleshServer.main.misc;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_21_R3.attribute.CraftAttribute;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftLivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class NMSUtils {
    public static void registerGenericAttribute(org.bukkit.entity.Entity entity, Attribute attribute, Field attributeMap) throws IllegalAccessException {
        AttributeMap attributeMapBase = ((CraftLivingEntity) entity).getHandle().getAttributes();
        Map<Holder<net.minecraft.world.entity.ai.attributes.Attribute>, AttributeInstance> map = (Map<Holder<net.minecraft.world.entity.ai.attributes.Attribute>, AttributeInstance>) attributeMap.get(attributeMapBase);
        Holder<net.minecraft.world.entity.ai.attributes.Attribute> attributeBase = CraftAttribute.bukkitToMinecraftHolder(attribute);
        AttributeInstance attributeModifiable = new AttributeInstance(attributeBase, AttributeInstance::removeModifiers);
        map.put(attributeBase, attributeModifiable);
    }

    public static void spawnNMSentity(ServerLevel level, Location location, Entity spawn, EntityType entityType, CreatureSpawnEvent.SpawnReason spawnReason)  {
        Entity entity = null;
        try {
            entity = spawn.getClass().getConstructor(EntityType.class, Level.class).newInstance(entityType, level);
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        if(entity != null){
            entity.setPos(location.getX(),location.getY(),location.getZ());
            level.addFreshEntity(entity,spawnReason);
        }
    }
}

