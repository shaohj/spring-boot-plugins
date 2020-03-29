package com.sprboot.plugin.resttempex.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sprboot.plugin.resttempex.bean.Student;
import com.sprboot.plugin.resttempex.util.DateUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {
	
	/* post */
	@PostMapping(path="saveByMap", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object saveStudentByMap(@RequestBody Map<String, Object> maps){
		return maps;
	}
	
	@PostMapping(path="save", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object saveStudent(@RequestBody Student student){
		return student;
	}
	
	@PostMapping(path="saveAndHeader", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object saveStudentAndHeader(@RequestBody Student student, @RequestHeader String userInfo){
		return student;
	}
	
	/* delete */
	@DeleteMapping(path="deleteBySno", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object deleteBySno(@RequestParam String sno){
		Student stu1 = new Student("100", "张三", 1, DateUtil.stringToDate("19961022", "yyyyMMdd"));
		return stu1;
	};
	
	/* get */
	@GetMapping(path="query/listAll", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object getAllStudent(){
		Student stu1 = new Student("100", "张三", 1, DateUtil.stringToDate("19961022", "yyyyMMdd"));
		com.sprboot.plugin.resttempex.bean.Class myclass = new com.sprboot.plugin.resttempex.bean.Class();
		myclass.setCno("1000");
		myclass.setCname("88班");
		stu1.setsClass(myclass);
		
		Student stu2 = new Student("101", "李四", 2, DateUtil.stringToDate("19970201", "yyyyMMdd"));
		
		List<Student> students = new ArrayList<Student>();
		
		students.add(stu1);
		students.add(stu2);
		
		return students;
	};
	
	@GetMapping(path="query/getBySno", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object getStudentBySno(@RequestParam String sno){
		Student stu1 = new Student("100", "张三", 1, DateUtil.stringToDate("19961022", "yyyyMMdd"));
		return stu1;
	};
	
	@GetMapping(path="query/getBySnoAndHeader", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object getStudentBySno(@RequestParam String sno, @RequestHeader String userInfo){
		Student stu1 = new Student("102", "王五", 1, DateUtil.stringToDate("19951022", "yyyyMMdd"));
		return stu1;
	};
	
	/* put */
	@PutMapping(path="updateByMap", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object updateStudentByMap(@RequestBody Map<String, Object> maps){
		return maps;
	}
	
	@PutMapping(path="update", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object updateStudent(@RequestBody Student student){
		return student;
	}
	
	@PutMapping(path="updateAndHeader", consumes="application/json; charset=utf-8", produces = "application/json; charset=utf-8")
	public Object updateStudentAndHeader(@RequestBody Student student, @RequestHeader String userInfo){
		return student;
	}
	
}
