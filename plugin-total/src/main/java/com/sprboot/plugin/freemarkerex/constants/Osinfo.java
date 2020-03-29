package com.sprboot.plugin.freemarkerex.constants;

/**
 * 编  号：
 * 名  称：Osinfo
 * 描  述：
 * 完成日期：2020/3/29 20:13
 * @author：felix.shao
 */
public class Osinfo {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	private static Osinfo instance = new Osinfo();
	
	private EPlatform platform;
	
	private Osinfo(){}
	
	public static boolean isLinux(){
		return OS.indexOf("linux")>=0;
	}
	
	public static boolean isMacOS(){
		return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")<0;
	}
	
	public static boolean isMacOSX(){
		return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0;
	}
	
	public static boolean isWindows(){
		return OS.indexOf("windows")>=0;
	}
	
	public static boolean isOS2(){
		return OS.indexOf("os/2")>=0;
	}
	
	public static boolean isSolaris(){
		return OS.indexOf("solaris")>=0;
	}
	
	public static boolean isSunOS(){
		return OS.indexOf("sunos")>=0;
	}
	
	public static boolean isMPEiX(){
		return OS.indexOf("mpe/ix")>=0;
	}
	
	public static boolean isHPUX(){
		return OS.indexOf("hp-ux")>=0;
	}
	
	public static boolean isAix(){
		return OS.indexOf("aix")>=0;
	}
	
	public static boolean isOS390(){
		return OS.indexOf("os/390")>=0;
	}
	
	public static boolean isFreeBSD(){
		return OS.indexOf("freebsd")>=0;
	}
	
	public static boolean isIrix(){
		return OS.indexOf("irix")>=0;
	}
	
	public static boolean isDigitalUnix(){
		return OS.indexOf("digital")>=0&&OS.indexOf("unix")>0;
	}
	
	public static boolean isNetWare(){
		return OS.indexOf("netware")>=0;
	}
	
	public static boolean isOSF1(){
		return OS.indexOf("osf1")>=0;
	}
	
	public static boolean isOpenVMS(){
		return OS.indexOf("openvms")>=0;
	}
	
	/**
	 * 获取操作系统名字
	 * @return 操作系统名
	 */
	public static EPlatform getOSname(){
		if(isAix()){
			instance.platform = EPlatform.AIX;
		}else if (isDigitalUnix()) {
			instance.platform = EPlatform.Digital_Unix;
		}else if (isFreeBSD()) {
			instance.platform = EPlatform.FreeBSD;
		}else if (isHPUX()) {
			instance.platform = EPlatform.HP_UX;
		}else if (isIrix()) {
			instance.platform = EPlatform.Irix;
		}else if (isLinux()) {
			instance.platform = EPlatform.Linux;
		}else if (isMacOS()) {
			instance.platform = EPlatform.Mac_OS;
		}else if (isMacOSX()) {
			instance.platform = EPlatform.Mac_OS_X;
		}else if (isMPEiX()) {
			instance.platform = EPlatform.MPEiX;
		}else if (isNetWare()) {
			instance.platform = EPlatform.NetWare_411;
		}else if (isOpenVMS()) {
			instance.platform = EPlatform.OpenVMS;
		}else if (isOS2()) {
			instance.platform = EPlatform.OS2;
		}else if (isOS390()) {
			instance.platform = EPlatform.OS390;
		}else if (isOSF1()) {
			instance.platform = EPlatform.OSF1;
		}else if (isSolaris()) {
			instance.platform = EPlatform.Solaris;
		}else if (isSunOS()) {
			instance.platform = EPlatform.SunOS;
		}else if (isWindows()) {
			instance.platform = EPlatform.Windows;
		}else{
			instance.platform = EPlatform.Others;
		}
		return instance.platform;
	}

	public static void main(String[] args) {
		System.out.println(Osinfo.getOSname());
	}

}