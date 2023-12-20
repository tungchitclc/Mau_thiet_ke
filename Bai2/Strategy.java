class SelectionSort implements SortingStrategy {
  @Override
  public void sort(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      int minIndex = findMinIndex(array, i);
      if (minIndex != i) {
        swap(array, i, minIndex);
      }
    }
  }

  private int findMinIndex(int[] array, int start) {
    int minIndex = start;
    for (int j = start + 1; j < array.length; j++) {
      if (array[j] < array[minIndex]) {
        minIndex = j;
      }
    }
    return minIndex;
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}

// Các cài đặt khác có thể thêm sau này
