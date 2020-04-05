package seal.fan.com.listener;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import seal.fan.com.SealEffect;
import seal.fan.com.event.SealKeyPressEvent;

public class SealKeyListener implements Listener{
	@EventHandler
	public void cmd(SealKeyPressEvent e) {
		Player p = e.getPlayer();
		int code =e.getCode();
		File mkdir = SealEffect.keymkdir;
		for(File file:mkdir.listFiles()){
	    	YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
	    	if(!yml.contains("key")) {
	    		continue;
	    	}
			if(code==yml.getInt("key")){
				new BukkitRunnable(){
					public void run() {
						Player player = Bukkit.getPlayerExact(p.getName());
						if(yml.getBoolean("op")) {
						for(String cmd:yml.getStringList("cmd")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd.replace("%player%", p.getName()));
						}
						}else {
							for(String cmd:yml.getStringList("cmd")) {
								Bukkit.dispatchCommand(player,cmd.replace("%player%", p.getName()));
							}
						}
					}
				}.runTaskLater(SealEffect.This,yml.getInt("delay"));
			}
		}
	}
}
