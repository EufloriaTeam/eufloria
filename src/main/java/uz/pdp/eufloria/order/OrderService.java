package uz.pdp.eufloria.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.entity.*;
import uz.pdp.eufloria.entity.enums.OrderStatus;
import uz.pdp.eufloria.order.dto.OrderResponseDto;
import uz.pdp.eufloria.repository.BucketItemRepository;
import uz.pdp.eufloria.repository.OrderRepository;
import uz.pdp.eufloria.repository.ShippingRepository;
import uz.pdp.eufloria.rsql.SpecificationBuilder;
import uz.pdp.eufloria.security.UserPrincipal;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BucketItemRepository bucketItemRepository;
    private final ShippingRepository shippingRepository;
    private final OrderDtoMapper orderDtoMapper;
    public OrderResponseDto create(List<BucketItem.BucketId> plantIds, UUID shippingId) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();

        List<BucketItem> items = plantIds.stream()
                .map(plantId -> bucketItemRepository.findById(plantId).orElseThrow(
                        () -> ApiException.throwException("Bucket not found"))).toList();

        Shipping shipping = shippingRepository.findById(shippingId).orElseThrow(
                () -> ApiException.throwException("Shipping not found"));

        Order order = new Order(items, shipping);
        Order saved = orderRepository.save(order);
        return orderDtoMapper.toResponseDto(saved);
    }

    public OrderResponseDto get(UUID orderId) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        List<Order> orders = user.getOrders();
        for (Order order : user.getOrders()) {
            if (order.getId().equals(orderId))
                return orderDtoMapper.toResponseDto(order);
        }
        throw ApiException.throwException("Order not found");
    }

    public Page<OrderResponseDto> getAll(String predicate, Pageable pageable) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        List<Order> orders = user.getOrders();

        Specification<Order> specification = SpecificationBuilder.build(predicate);
        Page<Order> page;
        if (specification == null) {
            page = orderRepository.findAll(pageable);
        } else {
            page = orderRepository.findAll(specification, pageable);
        }
        return page.map(entity -> orderDtoMapper.toResponseDto(entity));
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public OrderResponseDto update(UUID orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> ApiException.throwException("Order not found"));
        boolean statusFound = false;
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.equals(status))
                statusFound=true;
        }
        if (!statusFound){
            throw ApiException.throwException("Not found order status");
        }
        order.setStatus(OrderStatus.valueOf(status));
        Order saved = orderRepository.save(order);
        return orderDtoMapper.toResponseDto(saved);
    }
}
