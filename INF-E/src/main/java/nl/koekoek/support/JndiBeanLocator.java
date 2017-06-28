package nl.koekoek.support;

import javax.naming.NamingException;

import org.springframework.jndi.JndiObjectFactoryBean;

public class JndiBeanLocator {

    public Object lookupBean(String jndiName) {
        return lookupBean(jndiName, new JndiObjectFactoryBean());
    }

    Object lookupBean(String jndiName, JndiObjectFactoryBean jndiObjectFactoryBean) {
        try {
            jndiObjectFactoryBean.setJndiName(jndiName);
            jndiObjectFactoryBean.afterPropertiesSet();
            return jndiObjectFactoryBean.getObject();
        } catch (NamingException ne) {
            throw new IllegalStateException(ne);
        }
    }

}
