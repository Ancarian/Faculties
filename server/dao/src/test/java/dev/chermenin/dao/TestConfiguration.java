package dev.chermenin.dao;

import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.CommonsQueryLoggingListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "dev.chermenin")
@EntityScan(basePackages = "dev.chermenin")
@EnableJpaRepositories
@EnableTransactionManagement
@TestPropertySource("classpath:application.yml")
public class TestConfiguration {

}