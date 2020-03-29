package com.sprboot.plugin.resttempex.rest;

import java.util.ArrayList;
import java.util.List;

import com.sprboot.plugin.resttempex.bean.Student;
import com.sprboot.plugin.resttempex.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("class")
public class ClassController {
	
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
	
}
