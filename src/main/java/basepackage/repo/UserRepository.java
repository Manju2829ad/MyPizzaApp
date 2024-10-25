package basepackage.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import basepackage.model.User;

public interface  UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByMobileNo(String mobileNo);

}
