package com.loopco.pricesolution.boundary;

import com.loopco.pricesolution.control.PriceRepository;
import com.loopco.pricesolution.entity.Price;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/prices")
public class PriceResource {


    private final PriceRepository priceRepository;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Price> getPrices() {
        return priceRepository.findAll();
    }
}
