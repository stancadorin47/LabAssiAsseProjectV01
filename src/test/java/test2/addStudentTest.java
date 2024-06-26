package test2;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class addStudentTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = mock(StudentXMLRepository.class);
    TemaXMLRepository fileRepository2 = mock(TemaXMLRepository.class);
    NotaXMLRepository fileRepository3 = mock(NotaXMLRepository.class);

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
    @Test
    public void addStudentValid(){
        service.deleteStudent("100");
        Student student = new Student("100","First Name Last Name",200);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        System.out.println(result);
        assertEquals(result, 1);
    }

    @Test
    public void BigBangTestValid(){

        Student student = new Student("888","Big Bang",300);
        when(fileRepository1.findOne("888")).thenReturn(student);

        Tema tema = new Tema("888","Descriere",9,7);
        when(fileRepository2.findOne("888")).thenReturn(tema);

        Nota nota = new Nota(new Pair<>("888","888"), 9, 9,"Bine");
        when(fileRepository3.findOne(new Pair<>("888","888"))).thenReturn(nota);

        assertEquals(service.saveStudent(student.getID(), student.getNume(), student.getGrupa()), 1);
        assertEquals(service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline()), 1);
        assertEquals(service.saveNota("888","888", nota.getNota(), nota.getSaptamanaPredare(), nota.getFeedback()), 1);
    }

    @Test
    public void BigBangTestInvalidStudent(){

        Student student = new Student(null,"Big Bang",300);
        when(fileRepository1.findOne("888")).thenReturn(student);

        Tema tema = new Tema(null,"Descriere",9,7);

        Nota nota = new Nota(new Pair<>("886","889"), 9, 9,"Bine");
        when(fileRepository3.findOne(new Pair<>("886","889"))).thenReturn(nota);

        assertEquals(service.saveStudent(student.getID(), student.getNume(), student.getGrupa()), 1);
        assertEquals(service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline()), 1);
        assertEquals(service.saveNota("886","889", nota.getNota(), nota.getSaptamanaPredare(), nota.getFeedback()), -1);
    }

    @Test
    public void BigBangTestInvalidTema(){

        Student student = new Student("889","Big Bang",300);

        Tema tema = new Tema("889","Descriere",9,7);
        when(fileRepository2.findOne("889")).thenReturn(tema);

        Nota nota = new Nota(new Pair<>("886","889"), 9, 9,"Bine");
        when(fileRepository3.findOne(new Pair<>("886","889"))).thenReturn(nota);

        assertEquals(service.saveStudent(student.getID(), student.getNume(), student.getGrupa()), 1);
        assertEquals(service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline()), 1);
        assertEquals(service.saveNota("886","889", nota.getNota(), nota.getSaptamanaPredare(), nota.getFeedback()), -1);
    }

//    @Test
//    public void addStudentInvalidGrupa(){
//        int[] groupBoundries = {100,999};
//        Integer id = 500;
//        for(int boundary: groupBoundries)
//        {
//            for(int i = -1; i <= 1; i++)
//            {
//                id += 1;
//                int value = boundary + i;
//                Student student = new Student(id.toString(),id.toString(),value);
//                int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
//                if( value >= 100 && value <= 999)
//                {
//
//                    System.out.println(value + " " + result);
//                    assertEquals(1, result);
//                } else {
//                    System.out.println(value + " " + result);
//                    assertEquals(0, result);
//                }
//
//            }
//        }
//    }
//
    @Test
    public void addStudentInvalidId(){
        Student student = new Student("","First Name Last Name",200);
        int result = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        assertEquals(result, 1);
    }

    @Test
    public void addStudentInvalidNume(){
        Student student = new Student("101","",200);
        service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
    }

    @Test
    public void addTemaValid(){
        service.deleteTema("100");
        Tema tema = new Tema("100","Descriere",9,7);
        int result = service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline());
        assertEquals(1, result);
    }

    @Test
    public void addTemaInvalidId(){
        Tema tema = new Tema("","Descriere",9,7);
        int result = service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline());
        assertEquals(1, result);

    }


    @Test
    public void addTemaInvalidDescriere(){
        Tema tema = new Tema("102","",9,7);
        int result = service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline());
        assertEquals(1, result);
    }

    @Test
    public void addTemaInvalidDeadline(){
        Tema tema = new Tema("103","Descriere",-1,7);
        int result = service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline());
        assertEquals(1, result);
    }

    @Test
    public void addTemaInvalidStartline(){
        Tema tema = new Tema("104","Descriere",7,-1);
        int result = service.saveTema(tema.getID(), tema.getDescriere(), tema.getDeadline(),tema.getStartline());
        assertEquals(1, result);
    }

    @Test
    public void addNotaValid(){
        Nota nota = new Nota(new Pair<>("100","100"), 9, 9,"Bine");
        service.saveNota("100","100", nota.getNota(), nota.getSaptamanaPredare(), nota.getFeedback());
    }
}
