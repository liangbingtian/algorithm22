package algorithm;

import java.util.Arrays;

/**
 * 排序相关的练习
 *
 * @author liangbingtian
 * @date 2021/01/26 下午1:55
 */
interface SortInterface {

  int[] sort(int[] arrays);
}

public class SortPractice {

  public static void main(String[] args) {
    int[] array = new int[]{42, 3, 2, 40, 63, 38, 29, 22, 12};
    System.out.println(Arrays.toString(new SortPractice().mergeSort(array)));
  }

  /**
   * 冒泡排序 （1）比较相邻的元素，如果第一个比第二个大，就交换他们。 （2）针对每一对相邻的元素做同样的工作，从开始一对到最后一对。这部做完后，最后一个元素将会是最大的元素。
   * （3）针对所有的元素重复以上步骤，除了最后一个元素。 （4）针对越来越少的元素重复以上步骤，直到没有一对数字需要进行比较。
   */
  private int[] bubbleSort(int[] arrays) {
    SortInterface sortInterface = arrays1 -> {
      int[] arr = Arrays.copyOf(arrays1, arrays1.length);
      boolean isFlag = true;
      for (int i = 1; i < arr.length; i++) {
        for (int j = 0; j < arr.length - i; j++) {
          if (arr[j] > arr[j + 1]) {
            int tmp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = tmp;
            isFlag = false;
          }
        }
        if (isFlag) {
          break;
        }
      }
      return arr;
    };
    return sortInterface.sort(arrays);
  }


  /**
   * 选择排序： （1）首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。 （2）再从剩余未排序序列中找到最小（大）元素，存放到排序序列的末尾位置。
   * （3）重复以上步骤直到所有元素都排序完毕。
   */
  private int[] selectionSort(int[] arrays) {
    SortInterface sortInterface = arrays1 -> {
      int[] result = Arrays.copyOf(arrays1, arrays1.length);
      for (int i = 0; i < result.length - 1; ++i) {
        int min = i;
        for (int j = i + 1; j < result.length; ++j) {
          if (result[i] > result[j]) {
            min = j;
          }
        }
        if (min != i) {
          int tmp = result[i];
          result[i] = result[min];
          result[min] = tmp;
        }
      }
      return result;
    };
    return sortInterface.sort(arrays);
  }


  /**
   * 插入排序 （1）将待排序序列的第一个元素看作一个有序序列，将第二个元素到最后一个元素看作是未排序序列。 （2）从头到尾扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置，如果插入的元素与有序序列的某个元素相等，
   * 则插入的元素方在有序序列中某个元素的后面。
   */
  private int[] insertSort(int[] source) {
    SortInterface sortInterface = arrays -> {
      int[] result = Arrays.copyOf(arrays, arrays.length);
      for (int i = 1; i < result.length; ++i) {
        int j = i;
        int tmp = result[i];
        while (j > 0 && result[j - 1] > tmp) {
          result[j] = result[j - 1];
          j--;
        }
        if (j != i) {
          result[j] = tmp;
        }
      }
      return result;
    };
    return sortInterface.sort(source);
  }

  /**
   * 归并排序 （1）找到待排序数组的中位元素，将其分成两个待排序数组。 （2）递归的对每个待排序数组进行步骤（1），直到待排序数组中不足两个元素。
   * （3）递归结束后对每两个待排序数组进行回溯计算。首先申请空间，使其大小为两个已排序数组的大小(单个元素认为是已排序)。 （4）设定两个指针，最初的位置都是指向两个已排序数组的起始位置。
   * （5）比较两个指针指向的元素，选择较小的一个元素加入合并空间，并且移动指针到下一个位置。 （6）重复步骤（5）直到某一指针到达序列尾。 （7）将另一序列剩下的元素直接复制到合并序列尾
   */
  private int[] mergeSort(int[] source) {
    SortInterface sortInterface = new SortInterface() {
      @Override
      public int[] sort(int[] arrays) {
        sort(arrays, 0, arrays.length-1);
        return arrays;
      }

      public void merge(int[] nums, int left, int middle, int right) {
        int[] tmpArray = new int[right - left + 1];
        int leftIndex = left;
        int rightIndex = middle + 1;
        int index = 0;
        while(leftIndex<=middle && rightIndex<=right) {
          if(nums[leftIndex]<=nums[rightIndex]) {
            tmpArray[index++] = nums[leftIndex++];
          }else{
            tmpArray[index++] = nums[rightIndex++];
          }
        }
        while(leftIndex<=middle) {
          tmpArray[index++] = nums[leftIndex++];
        }
        while(rightIndex<=right) {
          tmpArray[index++] = nums[rightIndex++];
        }
        for (int value : tmpArray) {
          nums[left++] = value;
        }
      }

      public void sort(int[] nums, int left, int right) {
        if(left<right) {
          int middle = left + ((right - left) >> 1);
          sort(nums, left, middle);
          sort(nums, middle + 1,  right);
          merge(nums, left, middle, right);
        }

      }

      };
    return sortInterface.sort(source);
  }

  /**
   * 快速排序 （1）从数列中挑出一个元素，成为基准，一般是挑出最左边的或最右边的。(pivot) （2）重新排列数列，所有元素中，比基准小的放在基准前边，比基准大的放在基准后边。在这个分区退出后，基准就位于数列的中间位置，
   * 左边的都比基准小，右边的都比基准大，这个操作叫做分区。 （3）递归的把小于基准的数列和大于基准的数列进行分区
   */
  private int[] quickSort(int[] source) {
    SortInterface sortInterface = new SortInterface() {
      @Override
      public int[] sort(int[] arrays) {
        int[] result = Arrays.copyOf(arrays, arrays.length);
        quickSort(result, 0, arrays.length - 1);
        return result;
      }

      private void quickSort(int[] array, int left, int right) {
        if (left < right) {
          int pivot = partiton(array, left, right);
          quickSort(array, left, pivot - 1);
          quickSort(array, pivot + 1, right);
        }
      }

      private int partiton(int[] array, int left, int right) {
        int pivot = left;
        int index = left + 1;
        for (int i = index; i <= right; ++i) {
          if (array[pivot] > array[i]) {
            swap(array, index, i);
            index++;
          }
        }
        swap(array, index - 1, pivot);
        return index - 1;
      }

      private void swap(int[] array, int i, int j) {
        if (i == j) {
          return;
        }
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
      }
    };
    return sortInterface.sort(source);
  }


}
