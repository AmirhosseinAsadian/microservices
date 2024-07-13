package ir.anisa.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CouponDTO implements Serializable {
    private String code;
    private String productName;
    private BigDecimal percent;
    private LocalDate expiryDate;
}
