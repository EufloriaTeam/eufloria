package uz.pdp.eufloria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.common.CommonUtils;
import uz.pdp.eufloria.entity.Card;
import uz.pdp.eufloria.entity.Order;
import uz.pdp.eufloria.entity.Payment;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.entity.enums.OrderStatus;
import uz.pdp.eufloria.repository.OrderRepository;
import uz.pdp.eufloria.repository.PaymentRepository;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final OrderRepository orderRepository;
    private final CardService cardService;

    public void pay(UUID orderId, UUID cardId, double sum) {
        Order order = getUsersOrderById(orderId);
        if (Objects.equals(OrderStatus.PAID, order.getStatus()))
            throw ApiException.throwException("It's already paid!");

        Card card = cardService.subtract(cardId, sum);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        repository.save(new Payment(order, card));
    }

    private Order getUsersOrderById(UUID orderId) {
        User user = CommonUtils.getCurrentUser();
        return user.getOrders().stream()
                .filter((o) -> Objects.equals(o.getId(), orderId))
                .findFirst()
                .orElseThrow(() -> ApiException.throwException("Something went wrong"));
    }
}
