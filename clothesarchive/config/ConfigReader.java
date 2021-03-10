package clothesarchive.config;

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
        String filename = "clothesarchive/config/app.config";
        InputStream in =null;
        try{
            in = new FileInputStream(filename);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the value of the port property
     */
    public String getPort(){
        return prop.getProperty("port");
    }

    /**
     * @return the value of the domain property
     */
    public String getDomain(){
        return prop.getProperty("domain");
    }

    /**
     * @return the value of the database property
     */
    public String getDatabaseName(){
        return prop.getProperty("database");
    }

    /**
     * @return the value of the user property
     */
    public String getUser(){
        return prop.getProperty("user");
    }

    /**
     * @return the value of the password property
     */
    public String getUserPassword(){
        return prop.getProperty("password");
    }
}
