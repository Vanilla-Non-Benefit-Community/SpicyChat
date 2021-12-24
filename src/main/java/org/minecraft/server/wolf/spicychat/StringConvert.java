package org.minecraft.server.wolf.spicychat;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class StringConvert {
    public static String hexColorConvert(String message)
    {
        int r=-1,g=-1,b=-1;
        if(message.length() <= 4)return message;
        while(firstFormatDetect(message,ConfigLoader.firstFormatBegin))
        {
            char[] tempStr = message.substring(message.indexOf(ConfigLoader.firstFormatBegin)+1,message.indexOf(ConfigLoader.firstFormatBegin)+4).toCharArray();
                r = Integer.parseInt(String.valueOf(tempStr[0]),16);
                g = Integer.parseInt(String.valueOf(tempStr[1]),16);
                b = Integer.parseInt(String.valueOf(tempStr[2]),16);
            message = firstTextReplace(message,ConfigLoader.firstFormatBegin,17*r,17*g,17*b);
        }
        if(!firstFormatDetect(message,ConfigLoader.firstFormatBegin))
        while(secondFormatDetect(message,ConfigLoader.secondFormatBegin,ConfigLoader.secondFormatLast))
        {
            String[] tempStr = message.substring(message.indexOf(ConfigLoader.secondFormatBegin)+1,message.indexOf(ConfigLoader.secondFormatLast)).split(" ");
            try
            {
                r = Integer.parseInt(tempStr[0]);
                g = Integer.parseInt(tempStr[1]);
                b = Integer.parseInt(tempStr[2]);
                if(0 <= r && r <= 255 && 0 <= g && g<= 255 && 0 <= b && b <= 255)
                {
                    message = secondTextReplace(message,ConfigLoader.secondFormatBegin,ConfigLoader.secondFormatLast,r,g,b);
                }
            }catch (NumberFormatException ignore)
            {
                //Bukkit.getLogger().info("失败了");
                break;
            }
        }
        return message;
    }
    private static String firstTextReplace(String message,char formatBegin,int r,int g,int b)
    {
        String tempStr1 = message.substring(0,message.indexOf(formatBegin));
        String tempStr2 = message.substring(message.indexOf(formatBegin)+4);
        String colorStr = convertRGB(r,g,b);
        char[] colorCode = new char[14];
        for(int i = 0; i < colorStr.length(); i++)
        {
            colorCode[2*i] = '\u00a7';
            colorCode[2*i+1] = colorStr.charAt(i);
        }
        return tempStr1+ String.valueOf(colorCode) +tempStr2;
    }
    private static String secondTextReplace(String message,char formatBegin,char formatLast,int r,int g,int b)
    {
        String tempStr1 = message.substring(0,message.indexOf(formatBegin));
        String tempStr2 = message.substring(message.indexOf(formatLast)+1);
        String colorStr = convertRGB(r,g,b);
        char[] colorCode = new char[14];
        for(int i = 0; i < colorStr.length(); i++)
        {
            colorCode[2*i] = '\u00a7';
            colorCode[2*i+1] = colorStr.charAt(i);
        }
        return tempStr1+ String.valueOf(colorCode) +tempStr2;
    }
    private static String convertRGB(int r,int g,int b)
    {
        String rStr,gStr,bStr;
        if(Integer.toHexString(r).length() < 2)
        {
            rStr = "0"+Integer.toHexString(r);
        }
        else
        {
            rStr = Integer.toHexString(r);
        }

        if(Integer.toHexString(g).length() < 2)
        {
            gStr = "0"+Integer.toHexString(g);
        }
        else
        {
            gStr = Integer.toHexString(g);
        }

        if(Integer.toHexString(b).length() < 2)
        {
            bStr = "0"+Integer.toHexString(b);
        }
        else
        {
            bStr = Integer.toHexString(b);
        }
        return "x"+rStr+gStr+bStr;
    }
    public static String messageReplace(String message)
    {
        //判断消息中是否含有map中的key，把key全取出来，挨个判断是否有，有就替换掉，最后返回message，再进行其他处理
        for(String key : ConfigLoader.messageReplace.keySet())
        {
            if(message.contains(key))
            {
                //Bukkit.getLogger().info(key+" / "+ConfigLoader.messageReplace.get(key));
                message = message.replaceAll(key,ConfigLoader.messageReplace.get(key));
            }
        }
        return message;
    }
    private static boolean firstFormatDetect(String message,char formatBegin)
    {
        if(message.indexOf(formatBegin) != -1)
        {
            char r,g,b;
            r = message.toLowerCase().toCharArray()[message.indexOf(formatBegin)+1];
            g = message.toLowerCase().toCharArray()[message.indexOf(formatBegin)+2];
            b = message.toLowerCase().toCharArray()[message.indexOf(formatBegin)+3];
            try
            {
                Integer.parseInt(String.valueOf(r),16);
                Integer.parseInt(String.valueOf(g),16);
                Integer.parseInt(String.valueOf(b),16);
            }
            catch (NumberFormatException ignore)
            {
                return false;
            }
            return true;
        }
        return false;
    }
    private static boolean secondFormatDetect(String message,char formatBegin,char formatLast)//匹配首尾，并判断是否为三段
    {
        if(message.indexOf(formatBegin) != -1 && message.indexOf(formatLast) != -1)
        {
            if(message.indexOf(formatBegin) - message.indexOf(formatLast) <= 12)
            {
                String[] tempStr = message.substring(message.indexOf(ConfigLoader.secondFormatBegin)+1,message.indexOf(ConfigLoader.secondFormatLast)).split(" ");
                if(tempStr.length == 3 && !tempStr[0].equalsIgnoreCase("") && !tempStr[1].equalsIgnoreCase("") && !tempStr[2].equalsIgnoreCase(""))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
