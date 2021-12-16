package cn.fantasticmao.demo.java.spring.framework.transaction;

import com.mysql.cj.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * ApplicationConfiguration
 *
 * @author fantasticmao
 * @since 2019-08-16
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class ApplicationConfiguration {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        return new SimpleDriverDataSource(new Driver(),
                "jdbc:mysql://localhost:3306/lib_examples?useSSL=false", "root", "");
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }
}
