package com.loopco.pricesolution.boundary;

import com.loopco.pricesolution.control.PriceControl;
import com.loopco.pricesolution.control.PriceRepository;
import com.loopco.pricesolution.entity.Price;
import com.loopco.pricesolution.transfer.PriceRequest;
import com.loopco.pricesolution.transfer.PriceSummary;
import com.loopco.pricesolution.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/prices")
public class PriceResource {

    private final PriceRepository priceRepository;
    private final PriceControl priceControl;

    @ResponseBody
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Price>> getPrices() {
        return ResponseEntity.ok(priceRepository.findAll());
    }


    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceSummary> getProductPrice(@RequestParam(required = false) Map<String, String> parameters) {

        final Long brandId = parseLong(parameters.getOrDefault("brandId", null));
        final Integer productId = parseInteger(parameters.getOrDefault("productId", null));

        LocalDateTime date;
        try {
            date = DateUtils.toLocalDateTime(parameters.getOrDefault("date", null));
        } catch (NullPointerException | DateTimeParseException exception) {
            return ResponseEntity.badRequest().build();
        }

        final PriceRequest priceRequest = PriceRequest.builder()
                .brandId(brandId)
                .productId(productId)
                .requestedDateTime(date)
                .build();

        Optional<PriceSummary> priceSummaryOptional = priceControl.getPrice(priceRequest);

        return priceSummaryOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Integer parseInteger(String value) {
        return Optional.ofNullable(value)
                .map(Integer::valueOf)
                .orElse(null);
    }

    private Long parseLong(String value) {
        return Optional.ofNullable(value)
                .map(Long::parseLong)
                .orElse(null);
    }

}
