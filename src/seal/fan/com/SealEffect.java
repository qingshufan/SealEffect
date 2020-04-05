package seal.fan.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.Charsets;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import seal.fan.com.cmd.SealCommands;
import seal.fan.com.event.SealKeyPressEvent;
import seal.fan.com.listener.SealKeyListener;

public class SealEffect extends JavaPlugin implements PluginMessageListener{
	public static File pm=null;
	public static File keymkdir=null;	
	public static SealEffect This;
	public static Map<String,Long> keytime = new HashMap<String,Long>();
	public static Map<String,Integer> presskey = new HashMap<String,Integer>();
	public static YamlConfiguration yml=null;
	public void onEnable() {
		This = this;
  	    this.getDataFolder().mkdir();
  	    pm=this.getDataFolder();
        keymkdir=new File(pm,"key");
        keymkdir.mkdir();
  	    this.getServer().getPluginCommand("se").setExecutor(new SealCommands());
  	    this.getServer().getPluginCommand("se").setTabCompleter(new SealCommands());
  	    this.getServer().getPluginManager().registerEvents(new SealKeyListener(),this);
       	Bukkit.getMessenger().registerIncomingPluginChannel(this, "fairy",this);
       	Bukkit.getMessenger().registerOutgoingPluginChannel(this, "fairy");
  	    loadImage();
  	    loadSeal();
  	    
	}
    public void loadImage() {
        File image_mkdir=new File(pm,"image");
       		 image_mkdir.mkdir();
        File file =new File(image_mkdir,"test.yml");
        if(!file.exists()) {
       	 try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
       	 YamlConfiguration yml =YamlConfiguration.loadConfiguration(file);
       	 List<String> list =new ArrayList<String>();
       	 list.add("elpx0");
       	 yml.set("path",list);
       	 yml.set("type","pf");
       	 yml.set("age",1000);
       	 yml.set("scale",25);
       	 yml.set("vx",0.1d);
       	 yml.set("vy",0.1d);
       	 yml.set("vz",0.1d);
       	 yml.set("addx",0d);
       	 yml.set("addy",1.0d);
       	 yml.set("addz",0d);
       	 yml.set("gravity",0d);
       	 yml.set("null","请先按照wiki配置图片特效,'此选项无效'");
       	 try {
				yml.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
	@SuppressWarnings("deprecation")
	public void reloadConfig(YamlConfiguration yml,File file){
  	  InputStream files= null;
		try {
			files = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        yml.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(files, Charsets.UTF_8)));
    }
	public void loadSeal(){
    	 File file = new File(this.getDataFolder(),"config.yml");
    	 if(!file.exists()) {
          	  this.saveResource("config.yml",false);
    	 }
    	 yml =YamlConfiguration.loadConfiguration(file);
     }
	@Override
	public void onPluginMessageReceived(String arg0, Player p, byte[] data) {
		 String s = new String(data);
		 if(s.startsWith("key-")) {
			 int code = Integer.valueOf(s.split("-")[1]);
			 if(p!=null) {
				 if(!presskey.containsKey(p.getName()+code)) {
					 boolean b =true;
					 if(keytime.containsKey(p.getName())) {
						 long time = System.currentTimeMillis();
						 long times=time-keytime.get(p.getName());
						 if(times<yml.getInt("key_delay")) {
							 b=false;
						 }
					 }
					 if(!b) {
						 return;
					 }
					 presskey.put(p.getName()+code,code);
					 SealKeyPressEvent keyevent =new SealKeyPressEvent(p,code);
					 Bukkit.getPluginManager().callEvent(keyevent);
					 keytime.put(p.getName(),System.currentTimeMillis());
				 }else {
					 presskey.remove(p.getName()+code);
				 }
			 }
		 }
		
	}
}
