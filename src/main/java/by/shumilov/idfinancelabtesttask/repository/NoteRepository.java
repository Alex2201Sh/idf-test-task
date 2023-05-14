package by.shumilov.idfinancelabtesttask.repository;

import by.shumilov.idfinancelabtesttask.bean.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
