package uz.ama.travel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ama.travel.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
}
