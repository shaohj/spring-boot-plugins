package com.sprboot.plugin.resttempex.rest;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.resttempex.bean.Student;
import com.sprboot.plugin.resttempex.bean.param.HeaderParams;
import com.sprboot.plugin.resttempex.util.DateUtil;
import com.sprboot.plugin.resttempex.util.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.util.Date;
import java.util.List;

@Slf4j
public class RestServiceTest {

	private Date date;

	@Before
	public void init(){
		date = DateUtil.stringToDate("19931022", "yyyyMMdd");
		log.info(DateUtil.dateToString(date));
	};

	/* get测试 */
	@Test
	public void listAllTest(){
		String url = "http://localhost:8080/resttemp/student/query/listAll";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		List<?> result = RestUtils.sendRequest(url, HttpMethod.GET, headerParams, List.class);
		log.info("\n-->result = {}", JSON.toJSONString(result));
	}

	@Test
	public void getBySnoTest(){
		String url = "http://localhost:8080/resttemp/student/query/getBySno?sno=100";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		Student result = RestUtils.sendRequest(url, HttpMethod.GET, headerParams, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}

	@Test
	public void getBySnoAndHeaderTest(){
		String url = "http://localhost:8080/resttemp/student/query/getBySnoAndHeader?sno=100";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams()
				.param("userInfo", "zs");
		Student result = RestUtils.sendRequest(url, HttpMethod.GET, headerParams, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}
	
	/* post测试 */
	@Test
	public void saveStudentTest(){
		String url = "http://localhost:8080/resttemp/student/save";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1); //DATE日期传入controller时,值会丢失
		log.info("\n-->bodyStr = {}", bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.POST, headerParams, bodyStr, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}

	@Test
	public void saveStudentByMapTest(){
		String url = "http://localhost:8080/resttemp/student/saveByMap";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1);
		log.info("\n-->bodyStr = {}", bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.POST, headerParams, bodyStr, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}

	@Test
	public void saveStudentAndHeaderTest(){
		String url = "http://localhost:8080/resttemp/student/saveAndHeader";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams()
				.param("userInfo", "zs");
		String bodyStr = JSON.toJSONString(stu1);
		log.info("\n-->bodyStr = {}", bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.POST, headerParams, bodyStr, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}
	
	/* put 测试 */
	@Test
	public void updateStudentTest(){
		String url = "http://localhost:8080/resttemp/student/update";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1); //DATE日期传入controller时,值会丢失
		log.info("\n-->bodyStr = {}", bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.PUT, headerParams, bodyStr, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}

	@Test
	public void updateStudentByMapTest(){
		String url = "http://localhost:8080/resttemp/student/updateByMap";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1);
		log.info("\n-->bodyStr = {}", bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.PUT, headerParams, bodyStr, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}

	@Test
	public void updateStudentAndHeaderTest(){
		String url = "http://localhost:8080/resttemp/student/updateAndHeader";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams()
				.param("userInfo", "zs");
		String bodyStr = JSON.toJSONString(stu1);
		log.info("\n-->bodyStr = {}", bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.PUT, headerParams, bodyStr, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}
	
	/* delete测试 */
	@Test
	public void deleteBySnoTest(){
		String url = "http://localhost:8080/resttemp/student/deleteBySno?sno=100";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		
		Student result = RestUtils.sendRequest(url, HttpMethod.DELETE, headerParams, Student.class);
		log.info("\n-->\nresult = {}", JSON.toJSONString(result));
	}
	
}
