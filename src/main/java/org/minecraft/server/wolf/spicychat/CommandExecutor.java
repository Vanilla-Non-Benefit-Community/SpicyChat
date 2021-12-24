package org.minecraft.server.wolf.spicychat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandExecutor implements org.bukkit.command.CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 0)
        {
            sender.sendMessage("[ SpicyChat ] 缺少必要参数！");
            return true;
        }
        if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("spicychat.reload"))
        {
            ConfigLoader.configLoad();
            sender.sendMessage("[ SpicyChat ] 重载完成！");
            return true;
        }
        return true;
    }
}
