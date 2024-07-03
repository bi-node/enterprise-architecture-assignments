package customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {
    public Student findByName(String name);
    public Student findByPhoneno(String no);
    public Student findByAddressCity(String city);

    public List<Student> findAllByGradesCourse(String course);

    @Query("{ 'grades.course': ?0, 'grades.gr': ?1 }")
    public List<Student> findAllByGradesWithGrade(String course, String grade);
}
