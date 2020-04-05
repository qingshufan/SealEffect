package seal.fan.com.cmd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import seal.fan.com.SealEffect;
import seal.fan.com.api.Fairy;
import seal.fan.com.data.ImageData;


public class SealCommands implements CommandExecutor,TabExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(!sender.isOp()) {
			sender.sendMessage("can't go along");
			return true;
		}
		if(args.length==0) {
			sender.sendMessage("§c§m §6§m §e§m §a§m §b§m——§d§m §f §b§l SealEffect §m——§d§m §3§m §9§m §5§m §7§m §4§m");
			sender.sendMessage("§e/se reload §d-重载配置");
			sender.sendMessage("§e/se send [玩家]  [代号]  [图片路径]  [特效类型]  [存活时间]  [图片大小]   x y z [图片状态] §d-发送一个图片到指定坐标给某个玩家看 ");
			sender.sendMessage("§e/se play [玩家]  [代号] [文档] x y z [图片状态] §d-将配置好的文档发送到指定坐标 不填坐标就是玩家的坐标");
			sender.sendMessage("§e/se stop [玩家] §d-停止该玩家看到的所有特效 all代表所有玩家");
			sender.sendMessage("§c§m §6§m §e§m §b§m——§d§m§f §6♦小凡制作♦§m——§d§m§3§m §9§m §5§m §7§m");
	    	return true;
		}
		if(args.length==1) {
            if(args[0].equalsIgnoreCase("reload")){
            	for(File file:SealEffect.pm.listFiles()){
            		if(!file.exists()){
            			continue;
            		}
            		if(file.isDirectory()){
            			for(File f2:file.listFiles()){
                     		if(!f2.exists()){
                    			continue;
                    		}
                     		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f2);
                     		SealEffect.This.reloadConfig(yml,f2);
            			}
            		}else{
             		YamlConfiguration ymls = YamlConfiguration.loadConfiguration(file);
             		SealEffect.This.reloadConfig(ymls,file);
            		}
            	}
               	sender.sendMessage("§d重载完毕");
               	return true;
               }
		}
		if("send".equalsIgnoreCase(args[0])) {
			if(args.length<11) {
				sender.sendMessage("§c参数不全");
				return true;
			}
            if(!isInt(args[5])) {
           	 sender.sendMessage("§c参数错误:存活时间为数字");
				return true; 
            }
             if(!isInt(args[6])) {
            	 sender.sendMessage("§c参数错误:图片大小为数字");
 				return true; 
             }
             if(!isInt(args[7])) {
            	 sender.sendMessage("§c参数错误:请输入正确的坐标");
 				return true; 
             }
             if(!isInt(args[8])) {
            	 sender.sendMessage("§c参数错误:请输入正确的坐标");
 				return true; 
             }
             if(!isInt(args[9])) {
            	 sender.sendMessage("§c参数错误:请输入正确的坐标");
 				return true; 
             }
			String dh =args[2];
			String loc=args[3];
			String type=args[4];
			int age = Integer.valueOf(args[5]);
			int scale = Integer.valueOf(args[6]);
			int x =Integer.valueOf(args[7]);
			int y =Integer.valueOf(args[8]);
			int z =Integer.valueOf(args[9]);
			Boolean cancel=true;
			if(args.length==11) {
				cancel =Boolean.parseBoolean(args[10]);
			}
			List<String> list=new ArrayList<>();
			list.add(loc);
			ImageData image =new ImageData(list,type,age,scale,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d);
 			Player p =Bukkit.getServer().getPlayerExact(args[1]);
			if(args[1]=="@a") {
				for(Player player:Bukkit.getServer().getOnlinePlayers()) {
					Fairy.send(dh, player, x, y, z,image,cancel);
				}
			}else {
			//Fairy.send(p,p.getName(), x, y, z,scale, age,0f, loc, type,dh);
			Fairy.send(dh, p, x, y, z, image,cancel);
			}
			sender.sendMessage("§d发送成功");
			return true;
		}
		if(args[0].equalsIgnoreCase("play")) {
			if(args.length<4) {
				sender.sendMessage("§c参数不全");
				return true;
			}
			Player p =Bukkit.getServer().getPlayerExact(args[1]);
			if(p==null) {
				sender.sendMessage("玩家不在线");
				return true;
			}
			int x =(int)Math.round(p.getLocation().getX());
			int y =(int)Math.round(p.getLocation().getY());
			int z =(int)Math.round(p.getLocation().getZ());
			Boolean cancel=true;
			if(args.length==5) {
				cancel =Boolean.parseBoolean(args[4]);
			}
			if(args.length==8) {
				cancel =Boolean.parseBoolean(args[4]);
	                if(!isInt(args[4])) {
	                  	 sender.sendMessage("§c参数错误:请输入正确的坐标");
	       				return true; 
	                   }
	                   if(!isInt(args[5])) {
	                  	 sender.sendMessage("§c参数错误:请输入正确的坐标");
	       				return true; 
	                   }
	                   if(!isInt(args[6])) {
	                  	 sender.sendMessage("§c参数错误:请输入正确的坐标");
	       				return true; 
	                   }
	                   cancel=Boolean.parseBoolean(args[7]);
	    				 x =Integer.valueOf(args[4]);
	    				 y =Integer.valueOf(args[5]);
	    				 z =Integer.valueOf(args[6]);
			}
			String dh =args[2];
			String ymldata =args[3];
			
			File image_mkdir=new File(SealEffect.pm,"image");
            if(!isCreated(ymldata)) {
              	 sender.sendMessage("§c参数错误:请输入已经创建的文档名");
              	 return true;
            }
            File images=new File(image_mkdir,ymldata+".yml");
            YamlConfiguration yml =YamlConfiguration.loadConfiguration(images);
            ImageData image = new ImageData(yml.getStringList("path"),yml.getString("type"),yml.getInt("age"),yml.getInt("scale"),yml.getDouble("vx")
            		,yml.getDouble("vy")
            		,yml.getDouble("vz")
            		,yml.getDouble("gravity")
            		,yml.getDouble("addx")
            		,yml.getDouble("addy")
            		,yml.getDouble("addz")
            		,yml.getDouble("rotate_x")
            		,yml.getDouble("rotate_y")
            		,yml.getDouble("rotate_z"),yml.getDouble("info"),yml.getDouble("yaw")
            		,yml.getDouble("vp")
            		);
			if(args[1]=="@a") {
				for(Player player:Bukkit.getServer().getOnlinePlayers()) {
					Fairy.send(dh, player, x, y, z, image,cancel);
				}
			}else {
				
				Fairy.send(dh,p, x, y, z, image,cancel);
			}
//			sender.sendMessage("§c"+ymldata+"§e播放成功");
			return true;
		}
		if(args.length<10) {
			if(args[0].equalsIgnoreCase("stop")) {
				if(args.length==2) {
					 if(args[1].equalsIgnoreCase("all")) {
						 for(Player p:Bukkit.getServer().getOnlinePlayers()) {
							 Fairy.sendExit(p);
						 }
					 }else {
						 Player p=Bukkit.getPlayerExact(args[1]);
						 if(p==null) {
							 sender.sendMessage("玩家不在线");
							 return true;
						 }
						 Fairy.sendExit(p);
					 }
					 sender.sendMessage("§d已停止指定玩家所有特效");
					return true;
				}
			}
			sender.sendMessage("未知指令");
			return true;
		}
		return false;
	}
    public boolean isCreated(String ymldata) {
		File image_mkdir=new File(SealEffect.pm,"image");
		for(File file:image_mkdir.listFiles()) {
			if(file.exists()) {
				if(file.getName().replace(".yml","").equalsIgnoreCase(ymldata)) {
					return true;
				}
			}
		}
		return false;
    }
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg2, String[] args) {
		return null;
	}
	public boolean isInt(String q)
	  {
	    try
	    {
	       Integer.valueOf(q).intValue();
	    }
	    catch (Exception e)
	    {
	      return false;
	    }
	    
	    return true;
	  }
}
