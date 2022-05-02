package Bonus;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.logging.Handler;

public class Main {
    private static final String user="LAB8";
    private static final String password="PAROLA";
    public  static HikariDataSource dataSource;
    public static void initDatabaseConnectionPool(){
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:oracle:thin:@//localhost:1521/XE");
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setAutoCommit(false);

    }
    public static void main(String[] args)throws SQLException {
        try{
            int corePoolSize =20;
            int maximumPoolSize = 105;
            int keepAliveTime =105;
            int queSize=30;

            initDatabaseConnectionPool();


            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queSize),threadFactory,new RejectionHandler());
            Monitor monitor=new Monitor(5,poolExecutor);
            Thread thread = new Thread(monitor);
            thread.start();

            for(int i=0;i<5;i++){
                poolExecutor.execute(new Worker(i));
            }
            Thread.sleep(2000);
            poolExecutor.shutdown();
            Thread.sleep(2000);
            monitor.shutdown();

        }catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            closeDatabaseConnectionPool();
        }
    }

    public static void closeDatabaseConnectionPool() {
        dataSource.close();
    }
}
