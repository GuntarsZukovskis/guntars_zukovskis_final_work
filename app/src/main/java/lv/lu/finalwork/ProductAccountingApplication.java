package lv.lu.finalwork;

import lv.lu.finalwork.config.AppConfiguration;
import lv.lu.finalwork.ui.ConsulUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductAccountingApplication {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ConsulUI consulUI = context.getBean(ConsulUI.class);
        consulUI.startUI();
    }

}
