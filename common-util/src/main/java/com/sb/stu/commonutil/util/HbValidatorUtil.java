package com.sb.stu.commonutil.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Value;

/**
 * hibernate验证器工具类
 */
public class HbValidatorUtil {

	/** 验证器工厂 */
	public static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

	public static <T> String validateAndGetErrorInfo(T object, Class<?>... groups) {
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validate(object, groups);
		return set == null || set.isEmpty() ? null : makeErrorInfo(set);
	}

	public static <T> String validatePropertyAndGetErrorInfo(T object, String propertyName, Class<?>... groups) {
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validateProperty(object, propertyName, groups);
		return set == null || set.isEmpty() ? null : makeErrorInfo(set);
	}

	public static <T> String validateValueAndGetErrorInfo(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validateValue(beanType, propertyName, value, groups);
		return set == null || set.isEmpty() ? null : makeErrorInfo(set);
	}

	/**
	 * 
	 * @desc 统一构造错误信息
	 */
	private static <T> String makeErrorInfo(Set<ConstraintViolation<T>> set) {
		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<T> constraintViolation : set) {
			sb.append(constraintViolation.getMessage()).append(";");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: mapAppender
	 * @Description: map附加器
	 * @param paramMap
	 *            要附加的map
	 * @return 附加后的map
	 * @throws InstantiationException
	 *             实例化异常
	 * @throws IllegalAccessException
	 *             实例化参数异常
	 */
	public static <K, V extends Collection> Map<K, V> mapAppender(Map<K, V>... paramMap)
			throws InstantiationException, IllegalAccessException {
		// 返回结果
		Map<K, V> resMap = new HashMap<>();

		if (paramMap == null || paramMap.length == 0) {
			return resMap;
		}

		for (Map<K, V> map : paramMap) {
			for (Entry<K, V> en : map.entrySet()) {
				K k = en.getKey();
				V v = en.getValue();
				if (resMap.containsKey(k)) {
					resMap.get(k).addAll(v);
				} else {
					Collection c = v.getClass().newInstance();
					c.addAll(v);
					resMap.put(k, (V) c);
				}
			}
		}

		return resMap;
	}

	/**
	 * 
	 * @Title: removeNullPoint
	 * @Description: 删除set中的null值
	 * @param ts
	 *            Set对象
	 */
	public static <T extends Set> void removeNullPoint(T... ts) {
		if (ts == null || ts.length == 0) {
			return;
		}
		for (T t : ts) {
			t.remove(null);
		}
	}

	/**
	 * 
	 * @Title: addErrorInfo
	 * @Description: 验证器错误信息添加工具
	 * @param errInfoBoxMap
	 *            错误信息盒子Map（用于存放错误信息，由使用该方法的人传入）
	 * @param errorLine
	 *            错误行号
	 * @param errorInfo
	 *            错误信息
	 */
	public static void addErrorInfo(Map<Integer, Set<String>> errInfoBoxMap, Integer errorLine, String errorInfo) {
		if (errInfoBoxMap == null) {
			throw new IllegalArgumentException("参数异常，不符合规范的参数errInfoBoxMap");
		}
		if (errInfoBoxMap.containsKey(errorLine)) {
			errInfoBoxMap.get(errorLine).add(errorInfo);
		} else {
			Set<String> errorSet = new LinkedHashSet<>();
			errorSet.add(errorInfo);
			errInfoBoxMap.put(errorLine, errorSet);
		}
	}

	public static void addErrorInfo(Map<Integer, Set<String>> errInfoBoxMap, Integer errorLine,
			Set<String> errorInfoSet) {
		if (errInfoBoxMap == null) {
			throw new IllegalArgumentException("参数异常，不符合规范的参数errInfoBoxMap");
		}
		if (errInfoBoxMap.containsKey(errorLine)) {
			errInfoBoxMap.get(errorLine).addAll(errorInfoSet);
		} else {
			errInfoBoxMap.put(errorLine, errorInfoSet);
		}
	}

}
