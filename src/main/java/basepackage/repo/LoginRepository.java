package basepackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import basepackage.model.Login;
import jakarta.persistence.Id;

public interface LoginRepository  extends  JpaRepository<Login , Long> {

}
