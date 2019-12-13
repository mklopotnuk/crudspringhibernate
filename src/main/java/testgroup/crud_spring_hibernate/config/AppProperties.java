package testgroup.crud_spring_hibernate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "testgroup.crud_spring_hibernate")
@PropertySource(value = "classpath:application.properties")
@EnableWebMvc
public class AppProperties {

    @Value("${urlBarcodeGenerator}")
    private String urlBarcodeGenerator;

    @Value("${barcodeFileFormat}")
    private String barcodeFileFormat;

    @Value("${barcodeResolution}")
    private String barcodeResolution;

    public String getUrlBarcodeGenerator() {
        return urlBarcodeGenerator;
    }

    public String getBarcodeFileFormat() {
        return barcodeFileFormat;
    }

    public String getBarcodeResolution() {
        return barcodeResolution;
    }
}
