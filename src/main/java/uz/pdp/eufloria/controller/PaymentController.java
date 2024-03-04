package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.service.PaymentService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + PaymentController.BASE_URL)
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;
    public static final String BASE_URL = "/payment";

    @PostMapping(params = {"orderId", "cardId", "sum"})
    public ApiResponse<?> pay(UUID orderId, UUID cardId, double sum) {
        service.pay(orderId, cardId, sum);
        return ApiResponse.respond(true, "Successfully Paid!");
    }
}
