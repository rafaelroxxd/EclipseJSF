package br.com.drogaria.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MyUtil
{
private static SessionFactory sessionFactory;
private static ServiceRegistry serviceRegistry;
private static final ThreadLocal<Session> threadLocal;

static {
try {
    Configuration configuration = new Configuration();
    configuration.configure();
    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    threadLocal = new ThreadLocal<Session>();

    } catch(Throwable t){
      t.printStackTrace();
      throw new ExceptionInInitializerError(t);
    }
}
public static Session getSession() {
    Session session = threadLocal.get();
    if(session == null){
        session = sessionFactory.openSession();
        threadLocal.set(session);
    }
    return session;
}

public static void closeSession() {
    Session session = threadLocal.get();
    if(session != null){
    session.close();
    threadLocal.set(null);
    }
}

public static void closeSessionFactory() {
    sessionFactory.close();
    StandardServiceRegistryBuilder.destroy(serviceRegistry);
   }
	
	
	
	
	
	
}
