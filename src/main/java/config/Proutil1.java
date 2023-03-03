package config;

import com.sun.javaws.security.Resource;

import java.io.*;
import java.util.Properties;

public class Proutil1 {
    public Properties Pro;
    public Proutil1(String Filepath){
         Pro=RedProperties(Filepath);
    }

    private Properties RedProperties(String Filepath) {
        Properties properties = new Properties();
        FileInputStream fileInputStream;

            try {
                fileInputStream = new FileInputStream(Filepath);
                BufferedInputStream in = new BufferedInputStream(fileInputStream);

                properties.load(in);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return properties;
        }

public String GetPro(String key){
       String value;
       if(Pro.containsKey(key)){
           value=Pro.getProperty(key);
           return value;
       }else{
          return null;
       }

}
public int GetLines(){
     return    Pro.size();
}


    }


