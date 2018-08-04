package com.java.stu.root.datastructure.algorithm.sort;

/**
 * 编  号：
 * 名  称：HeapSort
 * 描  述：堆排序,从小到大顺序排序实现
 * 完成日期：2018/8/4 15:58
 * @author：felix.shao
 */
public class HeapSort {

	/**
	 * 堆排序,从小到大
	 * @param arr 需排序的数组
	 */
	public static void heapSort(int[] arr){
		/* 分配大顶堆的长度 */
		int n = arr.length;
		/* 创建大顶堆,每次将最大的数放置大顶堆末尾 */
		while(n>1){
			/* out从最后一个非叶节点开始,在父节点、左右子节点中找寻最大的数并交换给父节点 */
			for(int root=n/2-1;root>=0;root--){
				int lchird = 2*root+1; //左孩子节点
				int rchird = 2*root+2; //右孩子节点
				int max = root; //最大元素为根节点
				/* 记录父节点与左孩子节点中的最大值索引 */
				if(arr[lchird]>arr[root]){
					max = lchird;
				}
				/* 若右孩子节点存在,则记录父节点与左、右孩子节点的最大值索引 */
				if(rchird<n&&arr[rchird]>arr[max]){
					max = rchird;
				}
				/* 若最大值节点索引不为父节点,则交换值 */
				if(max!=root){
					int temp = arr[root];
					arr[root] = arr[max];
					arr[max] = temp;
				}
			}
			/* 将树父节点与最后一个元素交换值 */
			int temp = arr[0];
			arr[0] = arr[n-1];
			arr[n-1] = temp;
			System.out.print("创建"+n+"大顶堆: ");
			for(int num:arr){
				System.out.print(num+" ");
			}
			System.out.println();
			n--;
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
		heapSort(arr);		
	}
	
}
