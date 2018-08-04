package com.java.stu.root.datastructure.algorithm.sort;

/**
 * 编  号：
 * 名  称：InsertSort
 * 描  述：插入排序,从小到大默认排序
 * 完成日期：2018/8/4 15:58
 * @author：felix.shao
 */
public class InsertSort {

	/**
	 * 插入排序,从小到大默认排序
	 * @param arr 要排序的数组
	 */
	public static void insertSort(int[] arr){
		/* 初始为2个元素(未排序的),每次循环在指定位置插入一个元素 */
		for(int out=1;out<arr.length;out++){
			int temp = arr[out]; //记录要插入的数据
			int in=out; //idx找到要插入元素的索引,默认为当前索引
			/* 从已排序的数组最后索引开始,向前遍历,待插入数据>当前遍历数据,则后移元素  */
			while(in>0&&arr[in-1]>temp){
				arr[in]=arr[in-1];
				in--;
			}
			/* 插入数据 */
			arr[in] = temp;
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
		insertSort(arr);
	}
	
}
