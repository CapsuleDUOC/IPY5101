package cl.duoc.ipy.websdl;

import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class WebsdlApplication {
	
	@Autowired
    private Jackson2ObjectMapperBuilder builder;
	
	@PostConstruct
    void started() {
    	
        this.builder.serializers(new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
        
        this.builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        this.builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT);
        
        this.builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        
        this.builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT);

        this.builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
 
    }

	public static void main(String[] args) {
		SpringApplication.run(WebsdlApplication.class, args);
	}

}
