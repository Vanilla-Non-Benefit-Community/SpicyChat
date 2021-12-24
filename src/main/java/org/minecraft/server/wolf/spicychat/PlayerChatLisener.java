package org.minecraft.server.wolf.spicychat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.BroadcastMessageEvent;

public final class PlayerChatLisener implements Listener {
    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e)
    {
        //提取参数
        String message = e.getMessage();
        message = StringConvert.messageReplace(message);
        e.setMessage(StringConvert.hexColorConvert(message));
    }
    @EventHandler
    public void serverChat(BroadcastMessageEvent e)
    {
        String message = e.getMessage();
        message = StringConvert.messageReplace(message);
        e.setMessage(StringConvert.hexColorConvert(message));
    }
}
