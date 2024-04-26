package uz.pdp.eufloria.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class PostOfficeDto {
    private String postCode;
    private String name;
    private AddressDto address;
    private LocalTime openTime;
    private LocalTime closeTime;
}
