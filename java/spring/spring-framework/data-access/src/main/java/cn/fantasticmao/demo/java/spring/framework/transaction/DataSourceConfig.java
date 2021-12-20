package cn.fantasticmao.demo.java.spring.framework.transaction;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;

import javax.sql.DataSource;

/**
 * DataSourceConfig
 * <p>
 * Spring 事务传播行为：
 * <ul>
 *     <li>{@link Propagation#REQUIRED} 支持当前事务；如果当前不存在事务，则创建事务</li>
 *     <li>{@link Propagation#SUPPORTS} 支持当前事务；如果当前不存在事务，则以非事务方式执行</li>
 *     <li>{@link Propagation#MANDATORY} 支持当前事务；如果当前不存在事务，则抛出异常</li>
 *     <li>{@link Propagation#REQUIRES_NEW} 创建新的事务；如果当前存在事务，则挂起当前事务</li>
 *     <li>{@link Propagation#NOT_SUPPORTED} 以非事务方式执行；如果当前存在事务，则挂起当前事务</li>
 *     <li>{@link Propagation#NEVER} 以非事务方式执行；如果当前存在事务，则抛出异常</li>
 *     <li>{@link Propagation#NESTED} 以嵌套事务方式执行；如果当前不存在事务，则与 {@link Propagation#REQUIRED} 类似</li>
 * </ul>
 *
 * @author fantasticmao
 * @since 2021/12/20
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource(new Driver(),
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'classpath:init.sql'");
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
