package org.fleshserver.fleshServer.main.misc;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

public class DataHandler {
    //Entities or player
    public static NamespacedKey key(String key) {
        return new NamespacedKey(GenericUtils.getPlugin(), key);
    }
    public static <T, Z> void set(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type, Z value) {
        DataHandler.getDataContainer(holder).set(DataHandler.Key(key), type, value);
    }
    private static PersistentDataContainer getDataContainer(PersistentDataHolder holder){
        return holder.getPersistentDataContainer();
    }

    public static <T, Z> Z get(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type) {
        if(!DataHandler.has(holder, key, type)){
            return null;
        }
        return DataHandler.getDataContainer(holder).get(DataHandler.Key(key), type);
    }

    public static <T, Z> boolean has(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type) {
        if(holder == null || holder.getPersistentDataContainer() == null || holder.getPersistentDataContainer().get(DataHandler.Key(key), type) == null)return false;
        return DataHandler.getDataContainer(holder).has(DataHandler.Key(key), type);
    }

    public static PersistentDataContainer newDataContainer(PersistentDataHolder holder){
        return holder.getPersistentDataContainer().getAdapterContext().newPersistentDataContainer();
    }


    public static <T, Z> boolean equals(PersistentDataHolder holder, String key, PersistentDataType<T, Z> type, Z value) {
        if(!DataHandler.has(holder, key, type)) return false;
        return DataHandler.get(holder, key, type).equals(value) || value.equals(DataHandler.get(holder, key, type));
    }

    public static NamespacedKey Key(String string){
        return new NamespacedKey(GenericUtils.getPlugin(), string);
    }
}
