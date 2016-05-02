package com.iztek.ayniyat.data.dbunit;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import com.iztek.util.persistence.HibernateUtil;

/**
 * @author Cagdas CIRIT
 */
public class DeHibernatizedTestCase extends DatabaseTestCase
{
    public DeHibernatizedTestCase(String name)
    {
        super(name);
    }
    
    // For Mysql Connection
   /* protected IDatabaseConnection getConnection() throws Exception
    {
        //There must be Ayniyat MYSQL schema before this connection  
        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql:///ayniyat", "root", "");
        return new DatabaseConnection(jdbcConnection);
    }*/
    
    protected IDatabaseConnection getConnection() throws Exception
    {
        //let Hibernate to construct the db schema if it doesn't exist
        HibernateUtil.getSession();
        HibernateUtil.closeSession();
        
        Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:hsqldb:mem:ayniyat", "sa", "");
        return new DatabaseConnection(jdbcConnection);
    }

    protected IDataSet getDataSet() throws Exception
    {
        return new XmlDataSet(new FileInputStream(getPath()+"ayniyatDataSet.xml"));
    }
    
    /*You should try to reuse the same connection for the entire test suite to reduce the overhead of creating 
    a new connection for each test. Since version 1.1, DatabaseTestCase is closing every connection in setUp() 
    and tearDown(). Override the closeConnection() method with an empty body to modify this behavior.*/
    protected void closeConnection(IDatabaseConnection connection) throws Exception{}
    
    private String getPath(){
        return new DeHibernatizedTestCase("").getClass().getResource("/com/iztek/ayniyat/data/dbunit/").getPath();
    }
}
