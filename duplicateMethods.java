
import java.util.Random;
 
public class duplicateMethods {
 
    public static void main(String[] args) {
        int[] size = {100, 1000};
        boolean upward = true;   
        int repetitions = 3;
 
        System.out.println("Algorithm\tn\toperations\ttime");
 
        for (int t = 0; t < size.length; t++) {
            int n = size[t];

            Random rnd = new Random(42);
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = rnd.nextInt(n);
            }
 
            // ================= ALGORITHM A: sort and check for duplicates =================
            long totalOpsA = 0;
            long totalNsA = 0;
            int duplicates = 0;
 
            for (int r = 0; r < repetitions; r++) {
                int[] a = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = data[i];
                }
 
                long ops = 0;
                long start = System.nanoTime();
 

                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - 1 - i; j++) {
                        ops++;
                        boolean swap;
                        if (upward) {
                            swap = a[j] > a[j + 1];
                        } else {
                            swap = a[j] < a[j + 1];
                        }
                        if (swap) {
                            int temp = a[j];
                            a[j] = a[j + 1];
                            a[j + 1] = temp;
                        }
                    }
                }
 
                duplicates = 0;
                for (int i = 1; i < n; i++) {
                    ops++;
                    if (a[i] == a[i - 1]) {
                        duplicates++;
                    }
                }
 
                totalNsA += System.nanoTime() - start;
                totalOpsA += ops;
            }
 
            // ================= ALGORITHM B: mode (most frequent value) =================
            long totalOpsB = 0;
            long totalNsB = 0;
            int mode = 0;
            int modeFrequency = 0;
 
            for (int r = 0; r < repetitions; r++) {
                long ops = 0;
                long start = System.nanoTime();
 
                int[] frequency = new int[n];
                for (int i = 0; i < n; i++) {
                    ops++;
                    frequency[data[i]]++;
                }
 
                mode = 0;
                modeFrequency = frequency[0];
                for (int v = 1; v < n; v++) {
                    ops++; 
                    if (frequency[v] > modeFrequency) {
                        modeFrequency = frequency[v];
                        mode = v;
                    }
                }
 
                totalNsB += System.nanoTime() - start;
                totalOpsB += ops;
            }
 

            double msA = (totalNsA / repetitions) / 1000000.0;
            double msB = (totalNsB / repetitions) / 1000000.0;
 
            System.out.println("A\t\t" + n + "\t" + (totalOpsA / repetitions) + "\t\t" + msA + " ms");
            System.out.println("B\t\t" + n + "\t" + (totalOpsB / repetitions) + "\t\t" + msB + " ms");
            System.out.println("   A -> repeated positions: " + duplicates);
            System.out.println("   B -> mode: " + mode + " (appears " + modeFrequency + " times)");
        }
    }
}
 