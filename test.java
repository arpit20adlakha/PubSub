public class BuggySorter {

    public static void sort(int[] numbers) {
        // Intention is to implement Bubble Sort
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    // Swap numbers[j] and numbers[j + 1]
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 8, 4, 2};
        sort(data);
        for (int num : data) {
            System.out.print(num + " ");
        }
    }
}
