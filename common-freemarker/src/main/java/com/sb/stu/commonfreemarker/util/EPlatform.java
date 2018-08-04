package com.sb.stu.commonfreemarker.util;

/**
 * 编  号：
 * 名  称：EPlatform
 * 描  述：平台
 * 完成日期：2018/8/4 15:26
 * @author：felix.shao
 */
public enum EPlatform {
	
	Any("any"),
	Linux("Linux"),
	Mac_OS("Mac OS"),
	Mac_OS_X("Mac OS X"),
	Windows("Windows"),
	OS2("OS/2"),
	Solaris("Solaris"),
	SunOS("SunOS"),
	MPEiX("MPE/iX"),
	HP_UX("HP-UX"),
	AIX("AIX"),
	OS390("OS/390"),
	FreeBSD("FreeBSD"),
	Irix("Irix"),
	Digital_Unix("Digital Unix"),
	NetWare_411("NetWare"),
	OSF1("OSF1"),
	OpenVMS("OpenVMS"),
	Others("Others");
	
	private EPlatform(String desc){
		this.description = desc;
	}

	@Override
	public String toString(){
		return description;
	}
	
	private String description;
}
