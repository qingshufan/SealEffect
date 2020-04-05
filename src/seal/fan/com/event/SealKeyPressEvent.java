package seal.fan.com.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SealKeyPressEvent extends Event implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	private Player p;
	private int code;
	private boolean cancal;
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
	    return handlers;
	}
    public SealKeyPressEvent(Player p,int code){
    	this.p=p;
    	this.code=code;
    }
    public Player getPlayer(){
    	return p;
    }
    public int getCode(){
    	return code;
    }
    public boolean isCancelled() {
        return cancal;
    }
    public void setCancelled(boolean cancel) {
        this.cancal = cancel;
    }
}
