package DPMRPacker;

public interface Packer {

    // Item to be knapsack-packed
    public interface Item {
        public int getWeight();  // Get positive weight for this job
        public int getValue();   // Get positive value for this job
    }

    public class Stats {
        public int value;     // Total value of the pack
        public int weight;    // Total weight of the pack
        public int numSlns;   // Number of computed subsolutions
        public long runTime;  // Execution time in mS

        public Stats(int value, int weight, int numSlns, long rT) {
            this.value = value;
            this.weight = weight;
            this.numSlns = numSlns;
            this.runTime = rT;
        }
    }

    public class Result {
        public Item[] items; // Solution
        public Stats stats;  // Data re solution

        public Result(Item[] items, Stats stats) {
            this.items = items;
            this.stats = stats;
        }
    }

    // Given a list of Items and a weight limit, determine the highest-value
    // choice of Items whose weight total is within the weight limit.  Report
    // this via a PackResult.  When two equally good packings exist, either one is
    // acceptable.
    //
    // Preconditions:
    // 1. MaxWeight >= 0.
    // 2. Items array is nonnull but may have zero elements.
    // 3. Item weights and values are positive.  Item weights may exceed maxWeight.
    //
    // Postconditions:
    // 1. Items array is unchanged.
    // 2. Returned items array is nonnull, but may have zero elements.  Any elements
    //    in the returned array are the same as elements in the original array,
    //    shallow copied.
    // 3. Total weight of selected items is <= maxWeight and has highest possible
    //    value.
    // 4. Reasonable running time even for weight/value in the 100,000,000 range, as
    //    long as length of items is not over 20.
    // 5. Reasonable running time even for item counts in the 100,000 range as long as
    //    maxWeight is not over 1000.
    // 6. Reported number of subsolutions computed is consistent with the algorithm
    //    used.
    public Result packItems(Item[] items, int maxWeight, boolean verbose);
}
