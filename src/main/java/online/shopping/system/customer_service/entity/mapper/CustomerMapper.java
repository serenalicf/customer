package online.shopping.system.customer_service.entity.mapper;

import online.shopping.system.customer_service.dto.CustomerDto;
import online.shopping.system.customer_service.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer customer);

}
