
package com.qilinxx.rms.configure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;

/**
 * @Auther: ljm
 * @Date: 2018-09-13 16:02
 * @Description: Mybatis映射配置
 */

@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
@MapperScan(basePackages = "com.qilinxx.rms.domain", sqlSessionFactoryRef = "masterSqlSessionFactory", basePackageClasses = MapperScannerConfigurer.class)
public class MasterDataSourceConfiguration {
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/qilinxx/rms/domain/mapper/*.xml"));
        bean.setTypeAliasesPackage("com.qilinxx.rms.domain.model");
        return bean.getObject();
    }

    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
