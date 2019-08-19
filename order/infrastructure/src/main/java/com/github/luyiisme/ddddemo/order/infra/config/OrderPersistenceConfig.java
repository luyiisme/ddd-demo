package com.github.luyiisme.ddddemo.order.infra.config;

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
@MapperScan(basePackages = "com.github.luyiisme.ddddemo.order.infra.dao",
        sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderPersistenceConfig {

    @PostConstruct
    private void initDb() {
        String sqlStatements[] = {
                "create table torder(id INT PRIMARY KEY AUTO_INCREMENT, order_no varchar(255), user_id INT, status varchar(255))"
                , "insert into torder(order_no,user_id,status) values('000001',1,'PAID')"
                , "create table line_item(id INT PRIMARY KEY AUTO_INCREMENT, order_id INT, name varchar(255), quantity INT, price varchar(255), product_id INT)"
                , "insert into line_item(order_id,name,quantity,price,product_id) values(1,'milk_1',3,'3',1)"
                , "insert into line_item(order_id,name,quantity,price,product_id) values(1,'bread_1',1,'6.5',2)"
                , "create table product(id INT, name varchar(255))"
                , "insert into product(id,name) values(1,'milk')"
                , "insert into product(id,name) values(2,'bread')"
        };

        Arrays.asList(sqlStatements).forEach(sql -> {
            try {
                orderDataSource().getConnection().createStatement().execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }


    @Bean(name = "orderDataSource")
    @ConfigurationProperties("spring.datasource.order")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "orderTransactionManager")
    public DataSourceTransactionManager orderTransactionManager(@Qualifier("orderDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
