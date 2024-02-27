package uz.pdp.eufloria.dto;

import lombok.Data;

@Data
public class ShippingDto {
    private String name;
    private String description;
    private String type;
    private double pricePerMile;
}
