package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("select s from School s join fetch s.students")
    List<School> findSchoolWithStudents();

    @Query("select s from School s")
    List<School> findSchoolOnly();

}
