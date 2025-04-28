package org.fleshserver.fleshServer.main.misc;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.fleshserver.fleshServer.FleshServer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericUtils {
    private static Plugin plugin = FleshServer.getPlugin(FleshServer.class);
    public static Plugin getPlugin(){
        return plugin;
    }
    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String format(String s) {
        s = s.replace("#&", "#");
        if (pattern != null) {
            Matcher m = pattern.matcher(s);
            while (m.find()) {
                String cl = s.substring(m.start(), m.end());
                s = s.replace(cl, "" + net.md_5.bungee.api.ChatColor.of(cl));
                m = pattern.matcher(s);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
