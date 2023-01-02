package dev.jitos.coursemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
/*Esta es porque la entity que utilizamos está en otro paquete diferente (porque es una libreria que creamos) y
 * tenemos que especificar el paquete en el que está para que no de error. Es un array y tenemos que incluir todas
 * los paquetes de las entidades que tenemos aunque sean en este mismo proyecto*/
@EntityScan({"dev.jitos.commonsstudent.entity," +
        "dev.jitos.coursemicroservice.entity"})
public class CourseMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseMicroserviceApplication.class, args);
    }

}
