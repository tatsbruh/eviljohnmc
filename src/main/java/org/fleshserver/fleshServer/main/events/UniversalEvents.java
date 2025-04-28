package org.fleshserver.fleshServer.main.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataType;
import org.fleshserver.fleshServer.main.misc.DataHandler;
import org.fleshserver.fleshServer.main.misc.EntityHelper;
import org.fleshserver.fleshServer.main.misc.ItemBuilder;

public class UniversalEvents implements Listener {

    @EventHandler
    public void cancelTarget(EntityTargetEvent e){
        var targetter = e.getEntity();
        var targetted = e.getTarget();
        if(targetted instanceof LivingEntity li1){
            if(targetter instanceof LivingEntity li2){
                if(DataHandler.has(li1,"assimilated", PersistentDataType.STRING) && DataHandler.has(li2,"assimilated",PersistentDataType.STRING)){
                    e.setCancelled(true);
                }
                if(DataHandler.has(targetter,"simskeleton",PersistentDataType.STRING)){
                    double dist = targetter.getLocation().distance(targetted.getLocation());
                    if(dist < 10.0){
                        targetter.getWorld().playSound(targetter.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0F, 1.0F);
                        EntityHelper.setMainHand((LivingEntity) targetter,new ItemBuilder(Material.IRON_SWORD).build());
                    }else{
                        targetter.getWorld().playSound(targetter.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0F, 1.0F);
                        EntityHelper.setMainHand((LivingEntity) targetter,new ItemBuilder(Material.BOW).build());
                    }
                }
            }
        }
    }
}
