package sinara_project.service.order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sinara_project.models.order.UserOrderDto;
import sinara_project.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable("userOrder")
    public Page<UserOrderDto> getUserOrders(Long userId, PageRequest of) {
        return orderRepository.findAllById(userId, of).map((userORder) -> modelMapper.map(userORder, UserOrderDto.class));
    }
}
