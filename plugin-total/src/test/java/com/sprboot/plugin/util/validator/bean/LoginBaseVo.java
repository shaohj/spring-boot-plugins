package com.sprboot.plugin.util.validator.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sprboot.plugin.util.validator.HbValidatorUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * 编  号：
 * 名  称：LoginBaseVo
 * 描  述：
 * 完成日期：2020/4/4 19:34
 * @author：felix.shao
 */
@Slf4j
@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginBaseVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 1：app;2:wx;3：游客 */
	@NotEmpty(message = "来源不能为空")
	private String source;

	@NotNull(message = "用户id不能为空", groups = { AppLoginGroup.class })
	/** app的userId */
	private Integer userId;

	@NotNull(message = "会员id不能为空", groups = { WxLoginGroup.class })
	/** 会员中心的memberId */
	private Integer memberId;

	@NotEmpty(message = "游客id不能为空", groups = { VisitorLoginGroup.class })
	private String uuid;

	public void isSuccessLoginCheck() {
		switch (source) {
		case "1":
			// 验证必填项
			String e1 = HbValidatorUtil.validateAndGetErrorInfo(this, Default.class, AppLoginGroup.class);
			if (!StringUtils.isEmpty(e1)) {
				throw new IllegalArgumentException(e1);
			};
			break;
		case "2":
			// 微信
			// 验证必填项
			String e2 = HbValidatorUtil.validateAndGetErrorInfo(this, Default.class, WxLoginGroup.class);
			if (!StringUtils.isEmpty(e2)) {
				throw new IllegalArgumentException(e2);
			};
			break;
		case "3":
			//游客
			String e3 = HbValidatorUtil.validateAndGetErrorInfo(this, Default.class, VisitorLoginGroup.class);
			if (!StringUtils.isEmpty(e3)) {
				throw new IllegalArgumentException(e3);
			};
			break;
		default:
			throw new IllegalArgumentException("错误的source参数");
		}
	}
	
}
