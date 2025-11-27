package sinara_project.service.order;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import sinara_project.models.order.UserOrder;
import sinara_project.models.order.UserOrderDto;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class OrderServiceProducerTest {

    @MockBean
    private KafkaTemplate<String, UserOrderDto> kafkaTemplate;

    @MockBean
    private OrderModelService modelService;

    @MockBean
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderServiceProducer producer;

    private final String topic = "order-topic";


    @Test
    void testCreateOrder() throws Exception {

        producer = new OrderServiceProducer(kafkaTemplate, topic);

        UserOrderDto inputDto = new UserOrderDto();
        UserOrder savedEntity = new UserOrder();
        inputDto.setUserId(1L);


        when(modelService.mapAndSave(inputDto)).thenReturn(savedEntity);
        when(kafkaTemplate.send(topic, "42", inputDto)).thenReturn(new CompletableFuture<>());
        when(modelMapper.map(savedEntity, UserOrderDto.class)).thenReturn(inputDto);

        UserOrderDto result = producer.createOrder(inputDto);

        assertThat(result).isEqualTo(inputDto);
    }

}