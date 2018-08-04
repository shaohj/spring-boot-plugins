package com.java.stu.root.datastructure.algorithm.sort;

/**
 * 编  号：
 * 名  称：QuickSort
 * 描  述：快速排序,从小到大默认排序
 * 完成日期：2018/8/4 15:58
 * @author：felix.shao
 */
public class QuickSort {

	/**
	 * 快速排序递归实现,从小到大默认排序
	 * @param arr 要排序的数组
	 * @param low 搜索数组起始索引
	 * @param high 搜索数组末尾索引
	 */
	public static void quickSort(int[] arr,int low,int high){
		/* low<=high时,避免出现low为0,high为-1等异常 */
		if(low >= high){
			return ;
		}
		/* 数组中待存值的索引 */
		int idx = low;
		/* 搜索关键字 */
		int key = arr[low];
		
		int lowTemp = low; //要排序的数组最小值
		int highTemp = high; //要排序的数组最大值
		/* 从数组尾端high向前遍历,找到比key小的数,再从low向后遍历,找比key大的数,直到low==high */
		while(lowTemp<highTemp){
			/* 从high向前遍历,直到找到比key小的数字 */
			while(lowTemp<highTemp&&arr[highTemp]>key){
				highTemp--;  //若元素大于key,high--
			}
			arr[idx] = arr[highTemp]; // 保存值
			idx = highTemp;
			/* 从low向后遍历,直到找到比key大的数字 */
			while(lowTemp<highTemp&&arr[lowTemp]<key){
				lowTemp++; //若元素小于key,low++
			}
			arr[idx] = arr[lowTemp];
			idx = lowTemp;
		}
		arr[idx] = key;
		
		System.out.printf("%-40s",("begin  key: "+arr[idx]+"-low: "+low+"-high: "+high+"\n    end  :"));
		for(int num:arr){
			System.out.print(num+" ");
		}
		System.out.println();
		/* 下面注意,当idx=0时,idx-1=-1 */
		quickSort(arr, low, idx-1);  
		quickSort(arr, idx+1,high);
	}
	
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i=0;i<arr.length;i++){
			arr[i]=(int) Math.floor(Math.random()*1000);
		}
		System.out.print("arr原始数据: ");
		for(int num:arr){
			System.out.print(num+" ");
		}
		System.out.println();
		quickSort(arr,0,arr.length-1);
	}
	
}
