package com.java.stu.root.datastructure.algorithm.sort;

/**
 * 编  号：
 * 名  称：BubbleSort
 * 描  述：冒泡排序,从小到大顺序排序实现
 * 完成日期：2018/8/4 15:57
 * @author：felix.shao
 */
public class BubbleSort {

	/**
	 * 冒泡排序
	 * @param arr 需要排序的数组
	 * @return int[]
	 */
	public static void bubbleSort(int[] arr){
		/* 进行i趟排序,每趟排序选择最大的数移至最后 */
		for(int i=0;i<arr.length;i++){
			/* 每趟排序时,将未排序字段进行排序,选择最大的一个数移至未排序的末尾 */
			for(int j=0;j<arr.length-i-1;j++){
				/* 如果前面的数大于后面的数,则交换数据 */
				if(arr[j]>arr[j+1]){
					int temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
			System.out.print("第"+i+"趟排序为: ");
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
		bubbleSort(arr);
	}
	
}
