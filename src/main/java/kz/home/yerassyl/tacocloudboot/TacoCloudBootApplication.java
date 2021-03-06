package kz.home.yerassyl.tacocloudboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.sql.DataSource;

@SpringBootApplication
public class TacoCloudBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudBootApplication.class, args);
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/taco");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("2002");
//
//        return dataSource;
//    }

}
