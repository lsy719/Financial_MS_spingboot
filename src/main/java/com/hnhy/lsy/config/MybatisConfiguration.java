package com.hnhy.lsy.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class MybatisConfiguration {

    //数据源
    @Resource
    private DataSource dataSource;

    // 资源读取器
    @Resource
    private MybatisProperties properties;

    /**
     * SqlSessionFactory, 设置VFS为SpringBootVFS
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(this.dataSource);
        bean.setVfs(SpringBootVFS.class); // 关键，将vfs设置为SpringBootVFS
        bean.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        bean.setMapperLocations(this.properties.resolveMapperLocations());
        return bean;
    }

}
