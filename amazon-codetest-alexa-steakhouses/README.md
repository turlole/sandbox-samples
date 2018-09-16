# Project: codetest-alexa-steakhouses
* * *

## Build
* * *

```
cd amazon-codetest-alexa-steakhouses
mvn clean package
```

<br />

This ia an approach to take input a tuple list of (x,y) coordinates and returns a tuple of the closest (x, y) coordinates distance from (0, 0) to a steakhouse coordinate.  The steakhouse coordinate entries list contains at most 2 entries, representing the x and y coordinate values.

<br />

**Notes about the application**:

* This was a first pass at returning a list of sorted coordinates based on smallest distance to (0, 0).
* Implementation takes advantage of [Insertion sort](https://en.wikipedia.org/wiki/Insertion_sort) to order the result by closest coordinate.
* Best case performance is `O(n)` for comparisons and `O(1)` for swaps.
* Worse case performane is `O(n^2)` for comparisons and swaps.

**What can be improved?**

* Storage of the coordinate tuples can be a tree instead of a list.  Usage of a tree assists in better efficiency in keeping track of the largest value in the tuple of coordinates.
* More effort can be placed into the sorting implementation and use a more popular efficient sorting algorithm like [heapsort](https://en.wikipedia.org/wiki/Heapsort).
* Best case performance is `O(n log n)` if the keys are distinct or `O(n)` if the keys are equal.
* Worse case performance is `O(n log n)`.

-- End
