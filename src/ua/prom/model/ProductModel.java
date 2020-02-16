package ua.prom.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement
public class ProductModel {

    private String productId;
    private String name;
    private BigDecimal price;
    private String image;
    private String Availability;
    private String url;

}
