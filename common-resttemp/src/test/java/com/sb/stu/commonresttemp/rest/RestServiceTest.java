package com.sb.stu.commonresttemp.rest;

import java.util.Date;
import java.util.List;

import com.sb.stu.commonresttemp.entity.resttemp.HeaderParams;
import com.sb.stu.commonresttemp.model.Student;
import com.sb.stu.commonresttemp.util.DateUtil;
import com.sb.stu.commonresttemp.util.RestUtils;
import org.springframework.http.HttpMethod;

import com.alibaba.fastjson.JSON;

public class RestServiceTest {

	public static void main(String[] args) {
		Date d = DateUtil.stringToDate("19931022", "yyyyMMdd");
		System.out.println(DateUtil.dateToString(d));
		
		Object result = null;
		
		/** get测试 */
		//result = listAllTest();
		//System.out.println("response\nlistAllTest:" + JSON.toJSONString(result));
        //
		//result = getBySnoTest();
		//System.out.println("response\ngetBySnoTest:" + JSON.toJSONString(result));
        //
		//result = getBySnoAndHeaderTest();
		//System.out.println("response\ngetBySnoAndHeaderTest:" + JSON.toJSONString(result));
		
		/** post测试 */
		//result = saveStudentTest();
		//System.out.println("response\nsaveStudentTest:" + JSON.toJSONString(result));
		//
		//result = saveStudentByMapTest();
		//System.out.println("response\nsaveStudentByMapTest:" + JSON.toJSONString(result));
		//
		//result = saveStudentAndHeaderTest();
		//System.out.println("response\nsaveStudentAndHeaderTest:" + JSON.toJSONString(result));
		
		/** put测试 */
		//result = updateStudentTest();
		//System.out.println("response\nupdateStudentTest:" + JSON.toJSONString(result));
        //
		//result = updateStudentByMapTest();
		//System.out.println("response\nupdateStudentByMapTest:" + JSON.toJSONString(result));
        //
		//result = updateStudentAndHeaderTest();
		//System.out.println("response\nupdateStudentAndHeaderTest:" + JSON.toJSONString(result));

		/** del测试 */
		result = deleteBySnoTest();
		System.out.println("response\ndeleteBySnoTest:" + ((Student)result).getSname() + ",birthday=" + DateUtil.dateToString(((Student)result).getBirthDay()));
		
	}
	
	/* get测试 */
	public static List<?> listAllTest(){
		String url = "http://localhost:8080/resttemp/rest/student/query/listAll";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		List<?> result = RestUtils.sendRequest(url, HttpMethod.GET, headerParams, List.class);
		return result;
	}
	
	public static Student getBySnoTest(){
		String url = "http://localhost:8080/resttemp/rest/student/query/getBySno?sno=100";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		Student result = RestUtils.sendRequest(url, HttpMethod.GET, headerParams, Student.class);
		return result;
	}
	
	public static Student getBySnoAndHeaderTest(){
		String url = "http://localhost:8080/resttemp/rest/student/query/getBySnoAndHeader?sno=100";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams()
				.param("userInfo", "zs");
		Student result = RestUtils.sendRequest(url, HttpMethod.GET, headerParams, Student.class);
		return result;
	}
	
	/* post测试 */
	public static Student saveStudentTest(){
		String url = "http://localhost:8080/resttemp/rest/student/save";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1); //DATE日期传入controller时,值会丢失
		System.out.println("bodyStr = " + bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.POST, headerParams, bodyStr, Student.class);
		return result;
	}
	
	public static Student saveStudentByMapTest(){
		String url = "http://localhost:8080/resttemp/rest/student/saveByMap";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1);
		System.out.println("bodyStr = " + bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.POST, headerParams, bodyStr, Student.class);
		return result;
	}
	
	public static Student saveStudentAndHeaderTest(){
		String url = "http://localhost:8080/resttemp/rest/student/saveAndHeader";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams()
				.param("userInfo", "zs");
		String bodyStr = JSON.toJSONString(stu1);
		System.out.println("bodyStr = " + bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.POST, headerParams, bodyStr, Student.class);
		return result;
	}
	
	/* put 测试 */
	public static Student updateStudentTest(){
		String url = "http://localhost:8080/resttemp/rest/student/update";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1); //DATE日期传入controller时,值会丢失
		System.out.println("bodyStr = " + bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.PUT, headerParams, bodyStr, Student.class);
		return result;
	}
	
	public static Student updateStudentByMapTest(){
		String url = "http://localhost:8080/resttemp/rest/student/updateByMap";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		String bodyStr = JSON.toJSONString(stu1);
		System.out.println("bodyStr = " + bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.PUT, headerParams, bodyStr, Student.class);
		return result;
	}
	
	public static Student updateStudentAndHeaderTest(){
		String url = "http://localhost:8080/resttemp/rest/student/updateAndHeader";
		
		Student stu1 = new Student("106", "赵六", 6, DateUtil.stringToDate("19931022", "yyyyMMdd"));
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams()
				.param("userInfo", "zs");
		String bodyStr = JSON.toJSONString(stu1);
		System.out.println("bodyStr = " + bodyStr);

		Student result = RestUtils.sendRequest(url, HttpMethod.PUT, headerParams, bodyStr, Student.class);
		return result;
	}
	
	/* delete测试 */
	public static Student deleteBySnoTest(){
		String url = "http://localhost:8080/resttemp/rest/student/deleteBySno?sno=100";
		
		HeaderParams headerParams = HeaderParams.getDefaultHeaderParams();
		
		Student result = RestUtils.sendRequest(url, HttpMethod.DELETE, headerParams, Student.class);
		return result;
	}
	
}
