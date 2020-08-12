package com.lht.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

import java.sql.SQLException;

@SpringBootApplication
@MapperScan("com.lht.shardingjdbc.mapper")
public class ShardingjdbcApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ShardingjdbcApplication.class, args);
    }
}
