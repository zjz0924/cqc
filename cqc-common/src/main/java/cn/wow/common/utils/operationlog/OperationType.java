package cn.wow.common.utils.operationlog;

import java.io.Serializable;

public enum OperationType implements Serializable {

   CREATE("Create", LogLevel.ONLYNEW), UPDATE("Update", LogLevel.BOTH),
   SCHEDULE("Schedule"),SCHEDULED("Scheduled"),
   DELETE("Delete", LogLevel.ONLYOLD), SAVE("Save", LogLevel.BOTH),
   MOVE("Move", LogLevel.BOTH),
   START("Start"), STOP("Stop"), RESTART("Restart"), 
   START_SERVER("Start on Server Level"), STOP_SERVER("Stop on Server Level"), RESTART_SERVER("Restart on Server Level"),
   START_CLUSTER("Start on Cluster Level"), STOP_CLUSTER("Stop on Cluster Level"), RESTART_CLUSTER("Restart on Cluster Level"), 
   IMPORT("Import"), EXPORT("Export"), 
   LOGIN("Login"), LOGOUT("Logout"), SYNC("Sync", LogLevel.BOTH),
   SAVE_SETTINGS("Save Settings"), // for SAT only
   // will not use in code
   UNKNOWN("Unknown");

   private String displayName;
   
   private LogLevel logLevel;

   OperationType(String displayName)
   {
      this.displayName = displayName;
      this.logLevel = LogLevel.NONE;
   }

   OperationType(String displayName, LogLevel logLevel)
   {
      this.displayName = displayName;
      this.logLevel = logLevel;
   }

   public String getDisplayName()
   {
      return displayName;
   }
   
   public boolean logBoth()
   {
      return logLevel == LogLevel.BOTH;
   }

   public boolean onlyLogOld()
   {
      return logLevel == LogLevel.ONLYOLD;
   }

   public boolean onlyLogNew()
   {
      return logLevel == LogLevel.ONLYNEW;
   }

   public static OperationType valueOfString(String name)
   {
      OperationType opt = UNKNOWN;
      for (OperationType item : OperationType.values())
      {
         String n = name != null ? name.trim() : "";
         if (n.equalsIgnoreCase(item.getDisplayName()) || n.equalsIgnoreCase(item.name()))
         {
            opt = item;
            break;
         }
      }
      return opt;
   }
   
   private static enum LogLevel
   {      
      NONE, ONLYOLD, ONLYNEW, BOTH
   }

}
