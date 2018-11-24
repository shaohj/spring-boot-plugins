package com.sb.stu.commonswagger.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 编  号：
 * 名  称：Swagger2Properties
 * 描  述：
 * 完成日期：2018/11/24 14:46
 * @author：felix.shao
 */
@Data
@ConfigurationProperties("swagger")
public class Swagger2Properties {

	/** 解析的包路径 */
	private String basePackage = "";

	/** 解析的url规则 */
	private List<String> basePath = new ArrayList<>();

	/** 在basePath基础上需要排除解析的url规则 */
	private List<String> excludePath = new ArrayList<>();

	/** swagger api标题 */
	private String title = "";

	/** swagger api描述 */
	private String description = "";

	/** swagger api版本 */
	private String version = "";

	/** swagger api许可证 */
	private String license = "";

	/** swagger api许可证URL */
	private String licenseUrl = "";

	/** host信息 */
	private String host = "";

	/** 联系人信息 */
	private Contact contact = new Contact();

	@Data
	@NoArgsConstructor
	public static class Contact {

		/** 联系人 */
		private String name = "";

		/** 联系人url */
		private String url = "";

		/** 联系人email */
		private String email = "";

	}

}
