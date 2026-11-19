package com.example.student.service;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import java.util.*;
@Service
public class StudentService {
 private final StudentRepository repo;
 private final EntityManager em;
 public StudentService(StudentRepository repo, EntityManager em){this.repo=repo;this.em=em;}
 private void validate(Student s){
  if(!Double.isFinite(s.getProgrammingScore()) || !Double.isFinite(s.getMathematicsScore()) || s.getProgrammingScore()<0 || s.getProgrammingScore()>100 || s.getMathematicsScore()<0 || s.getMathematicsScore()>100 || s.getTechnicalSkills()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Scores must be 0 to 100 and skills must be a list");
 }
 @Transactional public Student add(Student s){validate(s);if(repo.existsById(s.getStudentId()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Duplicate ID");em.persist(s);em.flush();return s;}
 public List<Student> all(){return repo.findAll();}
 public Student byId(int id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));}
 public List<Student> email(String email){return repo.findByEmailIgnoreCase(email);}
 public List<Student> course(String course){return repo.findByCourseNameIgnoreCase(course);}
 @Transactional public Student update(int id,Student s){validate(s);Student old=byId(id);old.setProgrammingScore(s.getProgrammingScore());old.setMathematicsScore(s.getMathematicsScore());old.setTechnicalSkills(new ArrayList<>(s.getTechnicalSkills()));return repo.save(old);}
 @Transactional public void delete(int id){repo.delete(byId(id));}
 public List<Student> merit(){List<Student> list=new ArrayList<>(all());list.sort(Comparator.comparingDouble(Student::getAverageScore).reversed());return list;}
 public Map<String,Set<Integer>> skills(){Map<String,Set<Integer>> map=new TreeMap<>();for(Student s:all())for(String skill:s.getTechnicalSkills())map.computeIfAbsent(skill,k->new TreeSet<>()).add(s.getStudentId());map.entrySet().removeIf(e->e.getValue().size()<2);return map;}
 public List<Student> improvement(){List<Student> result=new ArrayList<>();for(Student s:all())if(s.getProgrammingScore()<50 && s.getMathematicsScore()>=75)result.add(s);return result;}
}
