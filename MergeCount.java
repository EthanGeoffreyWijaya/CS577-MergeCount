import java.util.Scanner;
import java.util.ArrayList;

public class MergeCount {
	
	public static int countSort(ArrayList<Integer> list, int startIndex, int endIndex) {
		int inversions = 0;
		if (startIndex >= endIndex) {
			return 0;
		}
		int start1 = startIndex;
		int end1 = startIndex + (endIndex - startIndex)/ 2;
		int start2 = startIndex + (endIndex - startIndex)/ 2 + 1;
		int end2 = endIndex;
		//System.out.println(start1 + " " + end1 + "|" + start2 + " " + end2);
		
		inversions += countSort(list, start1, end1);
		inversions += countSort(list, start2, end2);
		inversions += mergeCount(list, start1, end1, start2, end2);
		
		return inversions;
	}
	
	public static int mergeCount(ArrayList<Integer> list, int startIndex1, int endIndex1, int startIndex2, int endIndex2) {
		int inversions = 0;
		ArrayList<Integer> full = new ArrayList<Integer>();
		int curr1 = startIndex1;
		
		while (curr1 <= endIndex1 || startIndex2 <= endIndex2) {
			
			if (startIndex2 <= endIndex2 && curr1 <= endIndex1) {
				if (list.get(startIndex2) < list.get(curr1)) {
					full.add(list.get(startIndex2++));
					inversions += endIndex1 - curr1 + 1;
				} else {
					full.add(list.get(curr1++));
				}
			} else if (startIndex2 <= endIndex2 && curr1 > endIndex1) {
				full.add(list.get(startIndex2++));
			} else if (startIndex2 > endIndex2 && curr1 <= endIndex1) {
				full.add(list.get(curr1++));
			}
			//System.out.println(full);
		}
		//System.out.println(full);
		for (int i = 0; i < full.size(); i++) {
			list.set(i + startIndex1, full.get(i));
		}
		
		return inversions;
	}

	public static void main(String[] args) {
		Scanner scnr =  new Scanner(System.in);
		int instances = scnr.nextInt();
		ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < instances; i++) {
			lists.add(new ArrayList<Integer>());
			int elements = scnr.nextInt();
			for (int j = 0; j < elements; j++) {
				lists.get(i).add(scnr.nextInt());
			}
		}
		scnr.close();
		
		//System.out.println(lists);
		for (int i = 0; i < instances; i++) {
			//System.out.println(lists.get(i));
			System.out.println(countSort(lists.get(i), 0, lists.get(i).size() - 1));
		}

	}

}
