package com.example.springmybatis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyBatisConfig {

    @Value("${db.driver-class-name}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    /*DriverManagerDataSource를 사용할 경우 항상 새로운 Connection을 획득한다.*/
//    @Bean
//    public DriverManagerDataSource dataSource() throws SQLException {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driver);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//
//        return dataSource;
//    }

    /*HikariDataSource*/
    @Bean
    public HikariDataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("myPool");
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        return new HikariDataSource(hikariConfig);

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        /*SqlSessionFactoryBean 객체*/
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        /*DataSource 설정*/
        sqlSessionFactoryBean.setDataSource(dataSource);
        /*config 파일을 설정*/
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis/config/mybatis-config.xml"));
        /*mapper 파일을 설정*/
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
