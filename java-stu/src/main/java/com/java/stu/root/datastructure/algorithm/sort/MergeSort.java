package com.java.stu.root.datastructure.algorithm.sort;

/**
 * 编  号：
 * 名  称：MergeSort
 * 描  述：归并排序递归实现,从小到大顺序排序实现
 * 完成日期：2018/8/4 15:58
 * @author：felix.shao
 */
public class MergeSort {

	/**
	 * 归并排序递归实现
	 * @param arr 需要归并排序的数组
	 * @param low 归并排序数组的起始索引
	 * @param high 归并排序数组的结束索引
	 */
	public static void mergeSort(int[] arr,int low,int high){
		/* 如果数组排序的数组长度为1,则返回 */
		if(low == high){
			return ; //当前排序OK
		}else{
			int mid = (low+high)/2;  //找到中间位置,分割数组
			mergeSort(arr, low, mid); //对左边进行排序
			mergeSort(arr,mid+1,high); //对右边进行排序
			merge(arr, low, mid, high); //将左、右边有序的数组进行合并。
			System.out.print("merge(low "+low+",mid "+mid+",high "+high+")");
			for(int num:arr){
				System.out.print(num+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 将有序的数组进行合并
	 * @param arr
	 * @param low
	 * @param mid
	 * @param high
	 */
	public static void merge(int[] arr,int low,int mid,int high){
		/* 保存排序后结果的数组 */
		int[] newArr=new int[high-low+1];
		
		int idx1 = low; //遍历第1个数组元素索引
		int idx2 = mid+1; //遍历第2个数组元素索引
		int nIdx = 0; //保存结果的数组存储元素的索引
		/* 比较第1个数组与第2个数组元素,将其有序的添加至新数组 */
		while(idx1<=mid&&idx2<=high){
			if(arr[idx1]<=arr[idx2]){
				newArr[nIdx++]=arr[idx1++];
			}else{
				newArr[nIdx++]=arr[idx2++];
			}
		}
		
		/* 如果第1个数组元素还未全部添加,则添加至newArr中 */
		while(idx1<=mid){
			newArr[nIdx++]=arr[idx1++];
		}
		
		/* 如果第2个数组元素还未全部添加,则添加至newArr中 */
		while(idx2<=high){
			newArr[nIdx++]=arr[idx2++];
		}
		
		/* 将排完序的数组元素存放至arr中 */
		System.arraycopy(newArr, 0, arr, low, newArr.length);
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
		mergeSort(arr,0,arr.length-1);
	}
	
}
