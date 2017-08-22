package cn.wow.common.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFileBasic
{
    private Logger logger = LoggerFactory.getLogger("msa.common.config");

    private InitialContext cxt = null;

    private boolean initiated = false;

    public PropertiesFileBasic()

    {
        properties = readProperties();
    }

    public void update()
    {
        properties = readProperties();
    }

    private InitialContext getContext()
    {
        if (cxt == null)
        {
            try
            {
                cxt = new InitialContext();
            }
            catch (NamingException e)
            {
               logger.error(e.getMessage());
            }
        }
        return cxt;
    }

    private Properties readProperties()
    {

        DataSource ds = getDataSource();

        if (ds != null)
        {
            Connection con = null;
            Statement sta = null;
            ResultSet rs = null;
            try
            {
                con = ds.getConnection();
                String sql = "SELECT name, value FROM MSACONFIG";
                sta = con.createStatement();
                rs = sta.executeQuery(sql);
                Properties props = new Properties();
                while (rs.next())
                {
                    props.put(rs.getString("NAME"), rs.getString("VALUE"));
                }
                initiated = true;
                return props;
            }
            catch (SQLException e)
            {
               logger.error(e.getMessage());
            }
            finally
            {
                if (rs != null)
                {
                    try
                    {
                        rs.close();
                    }
                    catch (SQLException e)//NOSONAR
                    {
                    }
                }

                if (sta != null)
                {
                    try
                    {
                        sta.close();
                    }
                    catch (SQLException e)//NOSONAR
                    {
                    }
                }

                if (con != null)
                {
                    try
                    {
                        con.close();
                    }
                    catch (SQLException e)//NOSONAR
                    {
                    }
                }
            }

        }
        else
        {
            logger.error("can not fetch a DataSource from msa or msacenter");
        }
        return new Properties();
    }

    public Properties getProperties()
    {
        return properties;
    }

    public String getPropertyValue(String theName)
    {
        if (!initiated)
        {
            update();
        }
        return properties.getProperty(theName);
    }

    public String getPropertyValue(String theName, String theDefault)
    {
        if (!initiated)
        {
            update();
        }
        if (properties.containsKey(theName))
        {
            return properties.getProperty(theName);
        }
        return theDefault;
    }

    private DataSource getDataSource()
    {
        InitialContext cxt = getContext();
        DataSource ds = null;
        try
        {
            ds = (DataSource)cxt.lookup("java:/comp/env/jdbc/msa");
        }
        catch (NamingException e)//NOSONAR
        {
        }
        if (ds == null)
        {
            try
            {
                ds = (DataSource)cxt.lookup("java:/comp/env/jdbc/msacenter");
            }
            catch (NamingException e)//NOSONAR
            {
                // just ignre here because the ds detective would be guarantee
            }
        }
        return ds;
    }

    private Properties properties = new Properties();

}
