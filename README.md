# Duplicate Methods: experimental comparison of algorithms

**Algorithms · Week 2** project ("Class challenge: experimental table").

## Team members

- Gabriela Chuquimarca y Paul Cumbal

This project compares two algorithms that work on an integer array to find information about repeated values. For each one, it measures how many **operations** it performs and how much **time** it takes.

## Algorithms

| Algorithm | Idea | Result | Complexity |
|---|---|---|---|
| **A** | Sorts a copy of the array (bubble sort, ascending or descending) and compares each element with its previous neighbor. | Number of repeated positions | O(n²) |
| **B** | Counts how many times each value appears in a frequency array and looks for the highest count. | The **mode** (most frequent value) and how many times it appears | O(n) |

The code uses only arrays and loops, with no `HashMap`, `Arrays.sort`, or other built-in helpers that automate the work.

## Files

- `duplicateMethods.java`: program that runs both algorithms and prints the table.
- `Comparacion_algoritmos.xlsx`: Excel table with the comparison ("Comparación" sheet) and the format from the class slide ("Tabla experimental" sheet).

## How to run

Requires the JDK to be installed.

```bash
javac duplicateMethods.java
java duplicateMethods
```

To test descending order, change `boolean upward = true;` to `false`.

## Methodology

- **Test sizes:** n = 100 and n = 1,000.
- **Data:** array of random integers between 0 and n-1, generated with `new Random(42)`. The fixed seed makes the experiment repeatable with the same data.
- **Repetitions:** each measurement is repeated **3 times** and the **average** is reported, as required by the challenge.
- **Time:** measured with `System.nanoTime()` and expressed in milliseconds.
- **Operations counted:**
  - **A:** bubble sort comparisons + neighbor comparisons.
  - **B:** one count per element + comparisons while searching for the maximum frequency.

## Results

| Algorithm | n | operations | time (ms) |
|---|---|---|---|
| A | 100 | 5,049 | 0.209 |
| A | 1,000 | 500,499 | 5.360 |
| B | 100 | 199 | 0.003 |
| B | 1,000 | 1,999 | 0.008 |

Additional program output with seed 42:

- n = 100: A finds 37 repeated positions; B reports that the mode is 93 (appears 5 times).
- n = 1,000: A finds 378 repeated positions; B reports that the mode is 391 (appears 5 times).

> The number of operations is always the same, but **times vary** between runs and machines. The values in the table come from a sample run.

## Conclusion

Algorithm B is much more efficient than Algorithm A. When going from n = 100 to n = 1,000, the operations of A grow by about 99 times (O(n²) behavior), while those of B grow by about 10 times (O(n) behavior). With n = 1,000, A performs about 250 times more operations than B and is more than 600 times slower in execution time.

The trade-off is that B is less general: its frequency array only works if the values are between 0 and n-1, and it does not leave the array sorted. So if you only need to know which value appears most often, B is the better choice. If you also need the sorted array, A is still useful, preferably with a sorting algorithm more efficient than bubble sort.

## Limitations

- With bubble sort, sizes such as n = 1,000,000 would take too long, so only 100 and 1,000 were tested.
- With small sizes, time is measured in microseconds and is affected by JVM warm-up and the operating system.
