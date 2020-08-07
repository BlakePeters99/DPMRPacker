package DPMRPacker;

import java.util.ArrayList;
import java.util.List;

public class DPMRPacker implements Packer {
    public Result packItems(Item[] items, int maxWeight, boolean verbose) {
        // items.length = row
        // maxWeight = column
        int table[][] = new int[items.length + 1][maxWeight + 1];

        // first row is 0
        for (int i = 0; i < maxWeight + 1; i++) {
                table[0][i] = 0;
                //  System.out.print(table[0][i] + "\t");
        }
        //System.out.print("\n");

        // Create table
        for (int item = 1; item <= items.length; item++) {
            for (int weight = 0; weight <= maxWeight; weight++) {
                // take the slot above the current slot
                table[item][weight] = table[item-1][weight];

                // if item can fit in slot and the current slot is less than table[previous row] [current column - item's weight ] + item's value
                if (weight >= items[item-1].getWeight() && table[item][weight] < table[item-1][weight-items[item-1].getWeight()]+items[item-1].getValue()) {
                    // then make slot the new greater value
                    table[item][weight] = table[item-1][weight-items[item-1].getWeight()] + items[item-1].getValue();
                }
                //System.out.print(table[item][weight] + "\t");
            }
            //System.out.print("\n");
        }
        System.out.println("Max Value:\t" + table[items.length][maxWeight]);

        // tracing the breadcrumbs to find all items in optimal solution
        // starting with bottom right of table
        List<Item> solution = new ArrayList<Item>();
        int numItems = items.length, currentWeight;
        int newWeight = 0, breadcrumbWeight = maxWeight;
        while (numItems > 0) {
            if (table[numItems][breadcrumbWeight] != table[numItems-1][breadcrumbWeight]) {
                currentWeight = items[numItems-1].getWeight();
                solution.add((Item) new PackerTester.MyItem(items[numItems-1].getValue(), currentWeight));
                newWeight += currentWeight;
                breadcrumbWeight -= currentWeight;
            }
            numItems--;
        }
        Stats stats = new Stats(table[items.length][maxWeight], newWeight, items.length, 0);

        Result result;
        if (solution.isEmpty())
            result = new Result(new Item[0], stats);
        else {
            Item[] arr = solution.toArray(new Item[solution.size()]);
            result = new Result(arr, stats);
        }

        return result;
    }

}

