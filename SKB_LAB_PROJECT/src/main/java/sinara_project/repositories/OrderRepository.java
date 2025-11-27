package sinara_project.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sinara_project.models.order.UserOrder;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<UserOrder, Long> {
    Page<UserOrder> findAllById(Long userId, Pageable pageable);

    UserOrder save(UserOrder order);
}
