package org.minecraft.server.wolf.spicychat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigLoader {
    static char firstFormatBegin;
    static char secondFormatBegin;
    static char secondFormatLast;

    static Map<String,String> messageReplace = new HashMap<>();
    static FileConfiguration config;
    public static void configLoad()
    {
        List<String> tempList = new ArrayList<>();
        Main.instance.saveDefaultConfig();
        Main.instance.reloadConfig();
        config = Main.instance.getConfig();
        tempList = config.getStringList("MessageReplace");

        //使用foreach把MessageReplace里面的字符串分割并传入Map中保存，等待替换
        for(String tempStr : tempList)
        {
            String[] tempArgs = tempStr.split("-");
            messageReplace.put(tempArgs[0],tempArgs[1]);
        }
        firstFormatBegin = config.getString("FirstFormat.Begin", "#").toCharArray()[0];
        secondFormatBegin = config.getString("SecondFormat.Begin", "<").toCharArray()[0];
        secondFormatLast = config.getString("SecondFormat.Last", ">").toCharArray()[0];
    }
}
