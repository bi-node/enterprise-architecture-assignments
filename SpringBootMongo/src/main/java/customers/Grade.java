package customers;

import org.springframework.data.annotation.Id;

public class Grade {
    @Id
    private  long id;
    private  String course;
    private  String gr;

    public Grade(long id, String course, String gr) {
        this.id = id;
        this.course = course;
        this.gr = gr;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", Course='" + course + '\'' +
                ", grade='" + gr + '\'' +
                '}';
    }
}
