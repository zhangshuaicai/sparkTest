public class QuickSort {

    public static void main(String[] args) {
        int num[] = {3, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18};
        quickSort(num, 0, num.length - 1);
        String var = "";
        for (int n : num) {
            var += n + "\t";
        }
        System.out.println(var);
    }

    /**
     * @param num   数组
     * @param start 起始指针
     * @param end   结束指针
     * @return
     */
    public static void quickSort(int[] num, int start, int end) {
        //left等于right，即数组只有一个元素，或者，当对指针i指向第一个元素时，会出现end < start
        if (start >= end) {
            return;
        }
        int j = end;
        int i = start;
        //将指定指针作为key进行比对
        int key = num[start];

        //当左指针比右指针小进行比对
        while (i < j) {
            //当右指针的参数>=比对参数key时，数值位置不动，右指针左移，TODO 当不满足时，代表当前数值小于key，则记录指针j
            while (num[j] >= key && i < j) {
                j--;
            }
            //当左指针的参数<=比对参数key时，数值位置不动，左指针右移，TODO 当不满足时，代表当前数值大于key，则记录指针i
            while (num[i] <= key && i < j) {
                i++;
            }
            //如果左指针<右指针，TODO 将记录的不满足条件的两个指针的数值，进行位置交换
            //3, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18
            if (i < j) {
                int tmp = num[i];
                num[i] = num[j];
                num[j] = tmp;
            }
        }
        //TODO 在交换完成的数组中，指针i的位置上是满足<=key条件的从j位置上刚刚交换过来的数值，所以要和key再进行位置交换
        num[start] = num[i];
        num[i] = key;
        //TODO 缩小范围，对指针i位置的左边进行递归快排
        quickSort(num, start, i - 1);
        //TODO 缩小范围，对指针i位置的右边进行递归快排
        quickSort(num, i + 1, end);
    }
}