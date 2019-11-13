public class MergeSort {
    public static void main(String[] args) {
        int num[] = {3, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18};
        int[] numSorted = sort(num, 0, num.length - 1);
        for (int n : numSorted) {
            System.out.print(n + "\t");
        }
    }

    /**
     * 用来对数据进行划分，分解
     * @param num
     * @param low
     * @param high
     * @return
     */
    public static int[] sort(int[] num, int low, int high) {
        //取中间的指针
        int mid = (high + low) / 2;
        if (low < high) {
            //递归归并左边的数值进行排序
            sort(num, low, mid);
            //递归归并右边的数值进行排序
            sort(num, mid + 1, high);
            //对数据进行归并排序
            merge(num, low, mid, high);
        }
        return num;
    }

    /**
     * 对数据进行归并操作
     * @param num
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(int[] num, int low, int mid, int high) {
        //定义一个临时数组
        int[] tmp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (num[i] < num[j]) {
                tmp[k++] = num[i++];
            } else {
                tmp[k++] = num[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            tmp[k++] = num[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            tmp[k++] = num[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int t = 0; t < tmp.length; t++) {
            num[t + low] = tmp[t];
        }
    }
}
