import java.util.Arrays;

public class BinaraySearch {

    public static void main(String[] args) {
        int[] arr = {3,2,4,5};
        int target = 6;
    }
    // return index
    // return -1 if not find

    static int binarySearch(int[] arr , int target)
    {
        int start = 0;
        int end = arr.length - 1;
        while(start <= end)
        {
            // find the middle element
            // int mid = (start + end)/2; // this line gives overfollow so follow this line
            int mid = start + (end - start)/2;

            if(target < arr[mid])
                end = mid - 1;
            else if (target >  arr[mid])
                start = mid + 1;
            else return mid;
        }
        return  start;
    }
    
    
}
