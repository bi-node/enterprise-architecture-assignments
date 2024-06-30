package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findAllByDepartmentName(String departmentName);

    public List<Student> findAllByGradesCourseName(String courseName);

//   @Query("select s from Student s join s.department d where d.name=:departmentName")
//    public List<Student> allStudentByDepartmentName(@Param("departmentName") String departmentName);

    @Query("select s from Student s join s.grades d where d.course.name=:courseName")
    public List<Student> allStudentByCourseName(@Param("courseName") String courseName);

    @Query("select s from Student s where s.department.name=:departmentName")
    public List<Student> allStudentByDepartmentName(@Param("departmentName") String departmentName);
}
