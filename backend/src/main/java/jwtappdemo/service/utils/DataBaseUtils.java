package jwtappdemo.service.utils;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * Утилита по созданию БД
 */
public class DataBaseUtils {

    public static BoneCPDataSource createDefaultDataSource(String url) {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("11111111");
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClass("org.postgresql.Driver");
        dataSource.setIdleConnectionTestPeriodInMinutes(1);
        dataSource.setIdleMaxAgeInMinutes(4);
        dataSource.setMaxConnectionsPerPartition(15);
        dataSource.setMinConnectionsPerPartition(4);
        dataSource.setPartitionCount(1);
        dataSource.setAcquireIncrement(3);
        dataSource.setStatementsCacheSize(50);
        return dataSource;
    }
}
