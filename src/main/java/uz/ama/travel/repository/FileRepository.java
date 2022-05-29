package uz.ama.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;
import uz.ama.travel.model.Attachment;

@Repository
public interface FileRepository extends JpaRepository<Attachment, Integer> {
}
