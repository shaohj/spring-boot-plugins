package com.java.stu.root.datastructure.search;

/**
 * 二分查找算法的实现,也称为折半查找算法
 * @author shj
 *
 */
public class BinarySearch {

	/**
	 * 在指定数组中查找key元素
	 *     示例：仅模拟从1~100数字不间断时的排序,间断的还需修改代码
	 * @param arr 数组
	 * @param key 查找元素
	 * @return int 返回查找的数组索引号,返回-1代表未查找到元素
	 */
	public static int find(int[] arr,int key){
		/* 处理数据为null,以及数组长度为1的情况 */
		if(arr==null){
			return -1;
		}else if(arr.length==1){
			return 0;
		}
		
		/** 保存查找元素的索引 */
		int eleIdx = -1;
		
		int count = 0; // 计算查找的次数
		int low=0; //首元素
		int high=arr.length-1; //尾元素
		int opt ; //当前选择的数
		
		boolean isfind = false; //指是否搜索到key
		
		/* 当没有找到key且low<high时,继续在数组中查找key */
		while(!isfind&&low<=high){
System.out.print("第"+count+"次查找： ");			
			opt = (low+high)/2; //取选择数索引
System.out.print("所选的数索引:"+opt+"、值:"+arr[opt]);	
System.out.println("可能值范围: "+arr[low]+"~"+arr[high]);
			if(arr[opt] == key){
System.out.println("找到此元素");					
				isfind = true;
			}
			/* opt<key */
			else if(arr[opt] < key){
				low = opt+1; 
			}
			/* opt>key */
			else {
				high = opt - 1;
			}
			count++;
		}
		return eleIdx;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[100];
		for(int i=0;i<arr.length;i++){
			arr[i] = i+1;  //不间断元素模拟
//			arr[i] = i+2;  //间断元素模拟
		}
		find(arr, 33);
	}
	
}
