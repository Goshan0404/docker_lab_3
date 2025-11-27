package sinara_project.models.user;

import lombok.Data;
import sinara_project.models.order.UserOrder;

import java.util.Set;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private Set<UserOrder> userOrders;
}
