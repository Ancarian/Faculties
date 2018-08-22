package dev.chermenin.dao;

import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.CommonsQueryLoggingListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            DataSource dataSourceBean = (DataSource) bean;
            ProxyDataSource proxyDataSource = new ProxyDataSource();
            ChainListener chainListener = new ChainListener();
            chainListener.addListener(new CommonsQueryLoggingListener());
            chainListener.addListener(new DataSourceQueryCountListener());
            proxyDataSource.setListener(chainListener);
            proxyDataSource.setDataSource(dataSourceBean);
            return proxyDataSource;
        }
        return bean;
    }
}