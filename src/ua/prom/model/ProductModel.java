package ua.prom.model;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductModel {

    private String productId;
    private String name;
    private BigDecimal prise;
}
