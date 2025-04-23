package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver2 implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyr) {
        Pyramid pyramid = validate(pyr);

        // Return zero for empty pyramids
        if (pyramid.getRows() == 0) {
            return 0;
        }

        int rowCount = pyramid.getRows();

        long[][] table = new long[rowCount][rowCount];
        //fill the last row(bottom) of the table with the value
        table[rowCount - 1][0] = pyramid.get(rowCount - 1, 0);
        //start from the 2.nd row from the bottom
        for (int i = rowCount - 2; i >= 0; i--) {
            for (int j = 0; j <= rowCount; j++) {
                //left edge
                if (j == 0) {
                    table[i][j] = pyramid.get(i, j) + table[i + 1][0];
                }
                //right edge
                else if (rowCount - i - 1 == j) {
                    table[i][j] = pyramid.get(i, j) + table[i + 1][rowCount - i - 2];
                    //last value of the row
                    break;
                }
                //the rest of the table
                else {
                    //compare the values of the two neighboring cells from the row bellow
                    table[i][j] = pyramid.get(i, j) + Math.max(table[i + 1][j - 1], table[i + 1][j]);
                }
            }

        }

        //find the maximum sum of the table
        long maxSum = table[0][0];
        for (long value : table[0]) {
            if (value > maxSum) maxSum = value;
        }
        return maxSum;
    }

    /**
     * Validates the given {@code pyramid} and re
     *
     * @param pyramid the pyramid object
     * @return the {@code pyramid} object itself
     * @throws IllegalArgumentException if the {@code pyramid} is {@code null}
     */
    private Pyramid validate(Pyramid pyramid) throws IllegalArgumentException {
        if (pyramid == null) {
            throw new IllegalArgumentException();
        }
        return pyramid;
    }

}
