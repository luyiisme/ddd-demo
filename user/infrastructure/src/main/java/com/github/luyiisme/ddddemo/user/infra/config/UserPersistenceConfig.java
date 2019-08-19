package com.github.luyiisme.ddddemo.user.infra.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 09:44
 **/
@Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.github.luyiisme.ddddemo.user.domain.mapper",
        sqlSessionTemplateRef = "userSqlSessionTemplate")
public class UserPersistenceConfig {

    @PostConstruct
    private void initDb() {
        String sqlStatements[] = {
                "create table user(id serial,name varchar(255))",
                "insert into user(name) values('jack')",
        };

        Arrays.asList(sqlStatements).forEach(sql -> {
            try {
                userDataSource().getConnection().createStatement().execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }


    @Bean(name = "userDataSource")
    @ConfigurationProperties("spring.datasource.user")
    @Primary
    public DataSource userDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "userSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "userTransactionManager")
    @Primary
    public DataSourceTransactionManager userTransactionManager(@Qualifier("userDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "userSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
