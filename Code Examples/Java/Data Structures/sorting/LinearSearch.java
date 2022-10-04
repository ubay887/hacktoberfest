 public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {4,5,6,7,4,3,2,43,88};
        System.out.println(linerSeach(arr, 88));
    }

    static  int linerSeach(int[] arr, int target)
    {
        for(int i = 0 ; i < arr.length ; i++)
        {
            if(arr[i] ==  target)
                return  i;
        }
        return  -1;
    }
}
