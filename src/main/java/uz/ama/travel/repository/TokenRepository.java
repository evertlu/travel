package uz.ama.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ama.travel.model.User;

@Repository
public interface TokenRepository extends JpaRepository<User, Integer> {
}
