package com.java.stu.root.datastructure.algorithm.sort;

/**
 * 编  号：
 * 名  称：SelectSort
 * 描  述：选择排序,从小到大排序
 * 完成日期：2018/8/4 15:58
 * @author：felix.shao
 */
public class SelectSort {

	/**
	 * 选择排序,从小到大进行排序
	 * @param arr 需要排序的数组
	 */
	public static void selectSort(int[] arr){
		/* 进行n趟排序,每趟排序选择最小的数字与未排序的最左端数据进行交换 */
		for(int out=0;out<arr.length;out++){
			/* 最小的数索引 */
			int minIdx=out;
			/* 每趟排序选择最小的数字与未排序的最左端数据进行交换 */
			for(int in=out;in<arr.length;in++){
				/* 找到最小的数并记录其索引 */
				if(arr[minIdx]>arr[in]){
					minIdx=in;
				}
			}
			/* 若最小值索引与未排序的最左端索引不相等交换数据 */
			if(minIdx!=out){ 
				int temp=arr[minIdx];
				arr[minIdx] = arr[out];
				arr[out] = temp;
			}
			System.out.print("第"+out+"趟排序为: ");
			for(int num:arr){
				System.out.print(num+" ");
			}
			System.out.println();
		}
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
		selectSort(arr);
	}
	
}
