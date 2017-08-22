package cn.wow.common.config;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFile
{
   
   private PropertiesFileBasic propertiesFileBasic = null;
   
   private static Logger logger = LoggerFactory.getLogger("miep.msa.common.config");
   
   public PropertiesFile() {
   	this("MsaWebApp");
   }
   
   public PropertiesFile(String theName)
   {
      try
      {
         propertiesFileBasic = new PropertiesFileBasic();
      }
      catch (Exception theException)
      {
         logger.error("Error handling property file. ",
               theException);
      }
   }

   public void update()
   {
      try
      {
         if (propertiesFileBasic != null)
         {
            propertiesFileBasic.update();
         }
      }
      catch (Exception theException)
      {
         logger.error("Property file handling fails. ",
               theException);
      }
      
   }

   public Properties getProperties()
   {
      Properties properties = null;
      if (propertiesFileBasic != null){
         properties = propertiesFileBasic.getProperties();
      }
      return properties;
   }

   public String getPropertyValue(String theName)
   {
      String value = null;
      if (propertiesFileBasic != null){
         value = propertiesFileBasic.getPropertyValue(theName);
      }
      return value;
   }

}
