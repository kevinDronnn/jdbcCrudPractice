import java.util.List;

public class App {
    public static void main(String[] args) {
//        List<Student> students = CrudJdbc.getStudents("select * from jdbcstudents");
//        System.out.println(students);

//        Student student = new Student();
//        student.setId(1);
//        student.setName("alex");
//        student.setSurname("masharl");
//        student.setCourseName("c#");

//        System.out.println(CrudJdbc.saveStudent(student));

//        System.out.println(CrudJdbc.updateStudent(student.getId(),student));

//        CrudJdbc.deleteStudent(1);

        List<Student> students = CrudJdbc.getStudents();
        System.out.println(students);
    }
}
