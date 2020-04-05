package seal.fan.com.data;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import seal.fan.com.SealEffect;

public class ImageData {
       List<String> path;//ͼƬ·��
       String type;//��Ч����
       int age;//�������
       int scale;//ͼƬ��С
       double gravity; //����ֵ
       double vx; //ƫ��xֵ
       double vy; //ƫ��yֵ
       double vz; //ƫ��zֵ
       double vp; //����ҷ���ƫ�Ƶ��ٶ�
       double addx;//����ʱ����xƫ��
       double addy;//����ʱ����yƫ
       double addz;//����ʱ����zƫ
       double rotate_x;
       double rotate_y;
       double rotate_z;
   	double elrx;
   	double elrz;
   	double eudx;
   	double info;
   	double yaw;
       public ImageData(List<String> path,String type,int age,int scale,double vx,double vy,double vz,double gravity,double addx,double addy,double addz,
    		   double rotate_x,double rotate_y,double rotate_z,double info,double eudz,double vp) {
    	   this.path=path;
    	   this.type=type;
    	   this.age=age;
    	   this.scale=scale;
    	   this.vx=vx;
    	   this.vy=vy;
    	   this.vz=vz;
    	   this.gravity=gravity;
    	   this.addx=addx;
    	   this.addy=addy;
    	   this.addz=addz;
    	   this.rotate_x=rotate_x;
    	   this.rotate_y=rotate_y;
    	   this.rotate_z=rotate_z;
   		   this.info=info;
   		   this.yaw=eudz;
   		   this.vp=vp;
       }
       public ImageData(List<String> path,String type,int age,int scale,double vx,double vy,double vz,double gravity,double addx,double addy,double addz
    		   ,double rotate_x,double rotate_y,double rotate_z,
    		   double elrx,double elrz,double eudx,double info,double eudz,double vp) {
    	   this.path=path;
    	   this.type=type;
    	   this.age=age;
    	   this.scale=scale;
    	   this.vx=vx;
    	   this.vy=vy;
    	   this.vz=vz;
    	   this.gravity=gravity;
    	   this.addx=addx;
    	   this.addy=addy;
    	   this.addz=addz;
    	   this.rotate_x=rotate_x;
    	   this.rotate_y=rotate_y;
    	   this.rotate_z=rotate_z;
   		   this.elrx=elrx;
   		   this.elrz=elrz;
   		   this.eudx=eudx;
   		   this.info=info;
   		   this.yaw=eudz;
   		   this.vp=vp;
       }
		public static ImageData fromtoData(String path) {
			File image_mkdir=new File(SealEffect.pm,"image");
            File images=new File(image_mkdir,path+".yml");
            if(!images.exists()) {
            	return null;
            }
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
            return image;
		}
	    public Double getRotate_x(){
	        return rotate_x;
	    }
	    public Double getRotate_y(){
	        return rotate_y;
	    }

	    public Double getRotate_z(){
	        return rotate_z;
	    }
	    public Double getElrx(){
	        return this.elrx;
	    }
	    public Double getElrz(){
	        return elrz;
	    }

	    public Double getEudx(){
	        return eudx;
	    }
	    public Double getInfo(){
	        return info;
	    }
	    public Double getYaw(){
	        return yaw;
	    }
	    
       public void setVx(double vx) {
    	   this.vx=vx;
       }
       public Double getVx() {
    	   return this.vx;
       }
       public void setVp(double vp) {
    	   this.vp=vp;
       }
       public Double getVp() {
    	   return this.vp;
       }
       public void setVy(double vy) {
    	   this.vy=vy;
       }
       public void setYaw(double vy) {
    	   this.yaw=vy;
       }
       public void setInfo(double vy) {
    	   this.info=vy;
       }
       public Double getVy() {
    	   return this.vy;
       }
       public void setVz(double vz) {
    	   this.vz=vz;
       }
       public void setAddx(double addx) {
    	   this.addx=addx;
       }
       public Double getAddx() {
    	   return this.addx;
       }
       public void setAddy(double addy) {
    	   this.addy=addy;
       }
       public Double getAddy() {
    	   return this.addy;
       }
       public void setAddz(double addz) {
    	   this.addz=addz;
       }
       public Double getAddz() {
    	   return this.addz;
       }
       public Double getGravity() {
    	   return this.gravity;
       }
       public void setGravity(double gravity) {
    	   this.gravity=gravity;
       }
       public Double getVz() {
    	   return this.vz;
       }
       public void setAge(int age) {
    	   this.age=age;
       }
       public Integer getAge() {
    	   return this.age;
       }
       public void setScale(int scale) {
    	   this.scale=scale;
       }
       public Integer getScale() {
    	   return this.scale;
       }
       public void setPath(List<String> path) {
    	   this.path=path;
       }
       public List<String> getPath(){
    	   return this.path;
       }
       public void setType(String type) {
    	   this.type=type;
       }
       public String getType() {
    	   return this.type;
       }
       public void setRotate_x(Double rotate_x){
           this.rotate_x=rotate_x;
       }
       public void setRotate_y(Double rotate_y){
           this.rotate_y=rotate_y;
       }
       public void setRotate_z(Double rotate_z){
           this.rotate_z=rotate_z;
       }
       
}
