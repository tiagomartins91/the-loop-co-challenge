package com.loopco.pricesolution.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price_list", nullable = false)
    private Integer priceList;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "curr", nullable = false)
    private String currency;

    public boolean isStartDateEqualOrBefore(LocalDateTime date) {
        return this.startDate.equals(date) || this.startDate.isBefore(date);
    }

    public boolean isEndDateEqualOrAfter(LocalDateTime date) {
        return this.endDate.equals(date) || this.endDate.isAfter(date);
    }

    public Long getBrandId() {
        return this.brand.getId();
    }
}
