package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("config/applicationContext.xml");

        HibernateConnector.getSession();
    }
}
