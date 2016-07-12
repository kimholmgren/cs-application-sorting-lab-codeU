/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // account for base case - if given list with one element return it
			//split the list in half
		int length = list.size();
		if(length<=1) {
			return list;
		}
		LinkedList<T> a = new LinkedList<T>(list.subList(0, length/2));
		LinkedList<T> b = new LinkedList<T>(list.subList(length/2, length));
		List<T> firsthalf = mergeSort(a, comparator);
		List<T> secondhalf = mergeSort(b, comparator);

			//sort the halves using COllections.sort or Insertion sort

			//merge the sorted half into a complete sorted list

		//modify solution so it makes 2 recursive calls to each half of array


     return merge(firsthalf, secondhalf, comparator);
	}



	public List<T> merge(List<T> list, List<T> secondlist, Comparator<T> comparator) {
		int size = list.size() + secondlist.size();
		List<T> ret = new ArrayList<T>();
		for(int i=0; i<size; i++) {
			if(list.size()==0) {
				ret.add(secondlist.remove(0));
			} else if(secondlist.size()==0) {
				ret.add(list.remove(0));
			} else if(comparator.compare(list.get(0), secondlist.get(0))<0) {
				ret.add(list.remove(0));
			} else {
				ret.add(secondlist.remove(0));
			}
		}
		return ret;

	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
       PriorityQueue<T> queue = new PriorityQueue<T>(list.size(), comparator);
       queue.addAll(list);
       list.clear();
       while(!queue.isEmpty()) {
       	list.add(queue.poll());
       }

	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> queue = new PriorityQueue<T>(list.size(), comparator);
        List<T> ret = new ArrayList<T>();
        for(T curr: list) {
        	if(queue.size()<k) {
        		queue.offer(curr);
        	} else {
        		T smallest = queue.peek();
        		int cmp = comparator.compare(smallest, curr);
        		if(cmp<0) {
        			queue.poll();
        			queue.offer(curr);
        		}
        	}

        }
        for(int i=0; i<k; i++) {
        	ret.add(queue.poll());
        }
        return ret;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
