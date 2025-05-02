package com.db.hierarchicaltorelationaldm;

//import com.db.hierarchicaltorelationaldm.Controller.QueryController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HierarchicalToRelationalDmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HierarchicalToRelationalDmApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(XQueryToSQLTranslator translator) {
//        return args -> {
//            String xquery = "...";
//            String sql = translator.translateReadQuery(xquery, "store");
//            System.out.println("Translated SQL: " + sql);
//        };
//    }

}
