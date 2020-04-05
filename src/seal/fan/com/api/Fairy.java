package seal.fan.com.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import seal.fan.com.SealEffect;
import seal.fan.com.data.ImageData;

public class Fairy {
	@Deprecated
    public static void send(Player p,String name,double x,double y,double z,float scale,int age,float gra,String loc,String type,String dh) {
    	String data = "particle|"+name+"|"+x+"|"+y+"|"+z+"|"+x+"|"+y+"|"+z+"|"+scale+"|"+age+"|"+gra+"|"+loc+"|"+type+"|"+dh;
    	p.sendPluginMessage(SealEffect.This,"fairy",data.getBytes());
    	for(Entity en:p.getNearbyEntities(100,100,100)) {
    		if(en instanceof Player) {
    			Player player =(Player)en;
    			player.sendPluginMessage(SealEffect.This,"fairy",data.getBytes());
    		}
    	}
    }
    public static void sendExit(Player p) {
    	String data = "exit-particle";
    	p.sendPluginMessage(SealEffect.This,"fairy",data.getBytes());
    }
    public static void send(String dh,Player p,double x,double y,double z,ImageData image,boolean cancel) {
    	if(image==null) {
    		image=ImageData.fromtoData("join");
    	}
    	String dhs=dh+p.getUniqueId();
    	String data = "particle|"+p.getUniqueId()+"|"+x+"|"+y+"|"+z+"|"
                +image.getVx()+"|"+image.getVy()+"|"+image.getVz()+"|"
    			+image.getScale()+"|"+image.getAge()+"|"+image.getGravity()+"|"+image.getPath().toString()+"|"
                +image.getType()+"|"+dhs+"|"+image.getAddx()+"|"+image.getAddy()+"|"+image.getAddz()
                +"|"+image.getRotate_x()+"|"+image.getRotate_y()
            	+"|"+image.getRotate_z()+"|"+image.getInfo()+"|"+image.getYaw()+"|"+image.getVp()+"|"+cancel;
    	p.sendPluginMessage(SealEffect.This,"fairy",data.getBytes());
    	for(Entity en:p.getNearbyEntities(100,100,100)) {
    		if(en instanceof Player) {
    			Player player =(Player)en;
    			player.sendPluginMessage(SealEffect.This,"fairy",data.getBytes());
    		}
    	}
    }
}
