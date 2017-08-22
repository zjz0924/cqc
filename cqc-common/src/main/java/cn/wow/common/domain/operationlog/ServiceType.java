package cn.wow.common.domain.operationlog;

import java.io.Serializable;

/**
 * 
 * An enum for the different ServiceType types that exists in the MSA application.
 * 
 * @author MIEP/Ericsson
 *
 */
public enum ServiceType implements Serializable
{
   ACCOUNT("Account"), 
   USER_LOGIN("User Login"), 
   USER_LOGOUT("User Logout"),
   ROLE("Role"),
   SUB_ACT_TRACE("Subscriber Activity Tracing"),
   PROCESS("Process"),
   CONFIGURATION("Configuration"),
   SERVER("Node"),
   APN("Access Point Name"),
   SNMPCOUNTER("SnmpCounter"),
   DASHBOARDTEMPLATE("Dashboard Template"),
   AP_AUID("Aggregation Proxy - AUID"),
   AP_POOL("Aggregation Proxy - Pool"),
   AP_SERVER("Aggregation Proxy - Server"),
   BSF("BootStrapping Server Function"),
   DIAMETER_ROUTE("Diameter - Route"),
   DIAMETER_PEER("Diameter - Peer"),
   DIAMETER_APPLICATION("Diameter - Application"),
   ZONES("Zone"),
   SERVERGROUP("Cluster Management"),
   
   ORIGIN_SERVER("Origin Server"),
   ORIGIN_SERVER_CERT("Origin Server Certificate"),
   RADIUS_ATTRIBUTE("Radius Attribute"),
   RADIUS_CLIENT("Radius Client"),
   INFORMATION_FORWARDING("Information Forwarding"),
   WORK_FLOW_SCRIPT("Workflow Script"),
   USER_AGENT("User Agent - Internal"),
   URL_FILTER_GROUP("Content Filtering Group"), // alias to CFG
   CFG_CATEGORY("Content Filtering Category"),
   HOME_PAGE("Home Page"),
   HOME_HOSTED_SERVER_CERT("Hosted Server Certificate"),
   TLS_CONNECTION_PROPERTY("TLS Connection Property"),
   
   SUBSCRIBER_PLAN("Subscriber Plan"),
   TRUSTED_CNP("AP Remote Domain"),
   OPEN_ID("OpenID Connect Server"),
   DEVICE("User Agent - External"),                                       

   TCPPROXY("Destination Property"),                            
   TOOL("Tool"),                                            
   IMG_OPTIMIZATION("Image Optimization RuleSet"),
   
   PROTOCOL("Protocol"),
   SP_PROTOCOL("Protocol Control"),
   SP_CONDITION("Subscriber Plan Condition"),
   SP_PROXYPROPS("Proxy Property"),
   SP_ATTRIBUTE("Subscriber Plan Attribute"),
   
   URL_ANTIMALWARE("URL Definition - Anti Malware"),
   URL_CONTENT_ADAPTATION("URL Definition - Content Adaptation"),
   URL_CONTENT_OPTIMIZATION("URL Definition - Content Optimization"),
   URL_FREEACCESS("URL Definition - Free Access"),
   URL_ORIGIN_SERVER("URL Definition - Origin Server"),
   URL_STATISTICS("URL Definition - Statistics"),
   URL_MODIFICATION("URL Definition - URL Modification"),
   URL_WORKFLOW_SCRIPT("URL Definition - Workflow Script"),
   URL_ZONE("URL Definition - Zone"),
   
   //the following 4 service type only for operation log search, see Artifact artf451654 in eForge
   AP_("Aggregation Proxy - *"), //Aggregation Proxy
   DIAMETER_("Diameter - *"), //Diameter
   URL_("URL Definition - *"), //URL Definition
   SP_("URL Rule List - *"), //URL Rule List

   SP_ANTIMALWARE("URL Rule List - Anti Malware"),
   SP_CONTENT_ADAPTATION("URL Rule List - Content Adaptation"),
   SP_CONTENT_OPTIMIZATION("URL Rule List - Content Optimization"),
   SP_FREEACCESS("URL Rule List - Free Access"),
   SP_ORIGIN_SERVER("URL Rule List - Origin Server"),
   SP_STATISTICS("URL Rule List - Statistics"),
   SP_URL_MODIFICATION("URL Rule List - URL Modification"),
   SP_WORKFLOW_SCRIPT("URL Rule List - Workflow Script"),
   SP_ZONE("URL Rule List - Zone"),

   TDF_APP_IDENT("ADC - TDF Application Identifier"),
   IPF("ADC - Application Detection Filter"),
   ADC_RULE_BASE("ADC - ADC Rule Group"),
   ADC_RULES("ADC - ADC Rule"),
   VG_GATEWAY("Virtual Gateway"),
   
   PROCESS_STATUS("Process Status"),
   
   QOS("QoS Information"),
	
   TAG("Tag");

   private String displayName;

   ServiceType(String displayName)
   {
      this.displayName = displayName;
   }

   public String getDisplayName()
   {
      return displayName;
   }

   public static ServiceType valueOfString(String type)
   {
      ServiceType st = null;
      for (ServiceType t : ServiceType.values())
      {
         if (t.name().equalsIgnoreCase(type))
         {
            st = t;
            break;
         }
      }
      return st;
   }
   
}
