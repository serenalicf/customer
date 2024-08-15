package online.shopping.system.customer_service.repository;

import online.shopping.system.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> findByCustomerId(Integer customerId);

    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByJwtId(String jti);
}
