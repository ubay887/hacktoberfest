 

public class infinityRangeBinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,2,3,5,7,8,9,10,12,13,15,16,17,19,30,43,45,47,60,65,69,79};
        int target = 65;
        System.out.println(findingRange(arr, target));
        System.out.println(indexOfFirstOne(arr,0,20));
    }
    static int findingRange(int[] arr , int target)
    {
        //find the range
        // first start with box size 2
        int start = 0;
        int end = 1;
        // target find conditions
        while(target > arr[end])
        {
            int newStart = end + 1;  // my start , old  start can not update  before formula
            //end = previous end + size box * 2
            if( (end = end + (end - start + 1) * 2) < arr.length -1)
                end = (end = end + (end - start + 1) * 2);
            else end = arr.length -1;
            start = newStart;

        }
        return  binarySearch(arr, target, start, end);
    }
    static int binarySearch(int[] arr , int target , int start , int end)
    {
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
        return  -1;
    }

    //----------------------- i should check it also
   static int indexOfFirstOne(int arr[], int low, int high)
    {
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid] == 1)
                high = mid - 1;
            else
                low = mid + 1;
        }
        if(low == arr.length)
            return -1;
        return low;
    }
}
