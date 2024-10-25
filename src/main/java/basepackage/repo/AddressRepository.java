package basepackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import basepackage.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
