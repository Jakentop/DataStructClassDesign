package Mode;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sets implements Serializable {
 //#region 界面设置
 /**
  * 界面尺寸
  */
  public static int map_size=480;
//#endregion
 
    //#region 演示模式版本  
    /**
     * 演示模式版本
     */
    public static int show_mode = 1;
//#endregion

//#region 蛇身体
    /**
     * 设置蛇头颜色
     */
    public static Color snk_first_color=Color.green;
    
    /**
     * 设置蛇尾颜色
     */
    public static Color snk_last_color=Color.blue;
    
    /**
     * 设置蛇身颜色
     */
    public static Color snk_body_color=Color.white;

    /**
     * 设置食物颜色
     */
    public static Color food_color=Color.red;

    /**
     * 蛇的延迟速度
     */
    private static int sleep_time = 90;
    
    /**
     * get访问蛇的延迟速度
     * @return
     */
    public static int get_sleep_time(int x){ 
        if(x==1) return sleep_time;
        switch(sleep_time)
        {
            case 3:return 0;
            case 30:return 1;
            case 60:return 2;
            case 90:return 3;
            default : return -1;
        }}
    
    
    /**
     * set访问蛇的延迟速度，0 1 2 3，由慢到块
     * @param speed
     * @return int
     */
    public static int set_sleep_time(int speed){
       switch(speed)
       {
           case 0:sleep_time=3;break;
           case 1:sleep_time=30;break;
           case 2:sleep_time=60;break;
           case 3:sleep_time=90;break;
       }
        return 1;
    }
//#endregion

//#region 外部存储过程
/**
 * 负责将Sets的设置保存在外部方便下次读取
 * 
 */
public boolean SaveData() 
{
    try{
        File f=new File("obj.txt");
        FileOutputStream filesteam=new FileOutputStream(f);
        ObjectOutputStream obj=new ObjectOutputStream(filesteam);
        obj.writeObject(this);
        obj.close();
        return true;
    }
    catch (Exception iOException)
    {
        return false;
    }

}
/**
 * 负责将Sets的设置从外部读入
 * 由于这里采用了静态对象，所以请务必在开始时载入一下用户的设置！
 */
public static boolean ReadData()
{
   try{
    File f=new File("obj.data");
    FileInputStream filesteam=new FileInputStream(f);
    ObjectInputStream obj=new ObjectInputStream(filesteam);
    Sets temp=(Sets) obj.readObject();
    System.out.println('x');
    return true;
   }
   catch (Exception iException)
   {
       return false;
   }
}
//#endregion
}