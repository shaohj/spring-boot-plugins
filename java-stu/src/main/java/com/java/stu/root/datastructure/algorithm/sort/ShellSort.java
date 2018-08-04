package com.java.stu.root.datastructure.algorithm.sort;

/**
 * 编  号：
 * 名  称：ShellSort
 * 描  述：希尔排序,从小到大顺序排序实现
 * 完成日期：2018/8/4 15:58
 * @author：felix.shao
 */
public class ShellSort {

	/**
	 * 希尔排序递归实现
	 * @param arr 数组
	 * @param n 增量
	 */
	public static void shellSort(int[] arr){
		/* 设置初始增量 */
		int n = arr.length/2; 
		while(n>=1){ //增量为1时,最后一趟排序OK
			/* 增量为n,则为n组,循环为每组进行插入排序 */
			for(int out=0;out<n;out++){
				/* 对每组的元素进行插入排序 */
				for(int i=n+out;i<arr.length;i+=n){
					int temp = arr[i]; //记录当前需插入的元素
					int in=i;  //记录当前元素索引
					/* 从后面遍历,若元素大于当前元素,则后移元素 */
					while(in>=n&&arr[in-n]>temp){
						arr[in]=arr[in-n];
						in-=n;
					}
					/* 插入元素 */
					arr[in] = temp;
				}
			}
			System.out.print("增量为"+n+"的排序结果: ");
			for(int num:arr){
				System.out.print(num+" ");
			}
			System.out.println();
			n/=2;  //增量除2
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
		shellSort(arr);
	}
	
}
