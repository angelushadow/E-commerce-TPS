package co.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ecommerce.models.entities.PhoneEntity;

public interface PhoneRepositoy extends JpaRepository<PhoneEntity, Long>{

}
