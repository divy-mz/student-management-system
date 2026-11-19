package com.example.student.controller;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.*;
@RestController
@RequestMapping("/students")
public class StudentController {
 private final StudentService service;
 public StudentController(StudentService service){this.service=service;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Student add(@RequestBody Student s){return service.add(s);}
 @GetMapping public List<Student> all(){return service.all();}
 @GetMapping("/{id}") public Student byId(@PathVariable int id){return service.byId(id);}
 @PutMapping("/{id}") public Student update(@PathVariable int id,@RequestBody Student s){return service.update(id,s);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable int id){service.delete(id);}
 @GetMapping("/merit") public List<Student> merit(){return service.merit();}
 @GetMapping("/email/{email}") public List<Student> email(@PathVariable String email){return service.email(email);}
 @GetMapping("/course/{course}") public List<Student> course(@PathVariable String course){return service.course(course);}
 @GetMapping("/skills") public Map<String,Set<Integer>> skills(){return service.skills();}
 @GetMapping("/improvement") public List<Student> improvement(){return service.improvement();}
}
