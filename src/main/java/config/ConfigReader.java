package src.main.java.config;

import java.io.*;
import java.util.Properties;



public class ConfigReader {

    Properties prop;

    /**
     * Constructor that reads .config file and save the properties in the prop variable
     * @throws IOException
     */
    public ConfigReader() throws IOException {
        prop = new Properties();
        String filename = "app.config";
        InputStream in =null;
        try{
            in = new FileInputStream(filename);
            prop.load(in);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the value of the port property
     */
    public String getPort(){
        return prop.getProperty("port", "3306");
    }

    /**
     * @return the value of the domain property
     */
    public String getDomain(){
        return prop.getProperty("domain", "localhost");
    }

    /**
     * @return the value of the database property
     */
    public String getDatabaseName(){
        return prop.getProperty("database", "clothesarchive");
    }

    /**
     * @return the value of the user property
     */
    public String getUser(){
        return prop.getProperty("user", "root");
    }

    /**
     * @return the value of the password property
     */
    public String getUserPassword(){
        return prop.getProperty("password", "root");
    }
}
