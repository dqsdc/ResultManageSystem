
package com.qilinxx.rms.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Auther: ljm
 * @Date: 2018-09-13 15:57
 * @Description: Druid数据源配置
 */
@Configuration
public class DataSourceConfiguration {
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
}
