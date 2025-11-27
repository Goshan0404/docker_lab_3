package sinara_project.models.user;

import jakarta.persistence.*;
import lombok.Data;
import sinara_project.models.order.UserOrder;

import java.util.Set;

@Data
@Entity
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserOrder> userOrders;
}
