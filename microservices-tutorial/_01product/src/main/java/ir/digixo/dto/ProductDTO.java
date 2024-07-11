package ir.digixo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private String name;
    private String description;
    private BigDecimal price;
}
