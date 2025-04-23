package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.PyramidGenerator;
import com.ncr.test.pyramid.data.impl.RandomPyramidGenerator;
import com.ncr.test.pyramid.solver.impl.YourSolver;
import com.ncr.test.pyramid.solver.impl.YourSolver2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class YourSolverTest {
    private static final int MAX_DEPTH = 100;

    private static final int[][] SAMPLE_DATA = {
            { 5, 9, 8, 4 },
            { 6, 4, 5, 0 },
            { 6, 7, 0, 0 },
            { 3, 0, 0, 0 }
    };
    private static final int[][] DEMO_DATA = {
            { 59, 207, 98, 95 },
            { 87,   1, 70,  0 },
            { 36,  41,  0,  0 },
            { 23,   0,  0,  0 }
    };

    private static final int[][] NEGATIVE_DATA = {
            {-7, -8, -3, -10},
            {-4, -5, -6, 0},
            {-2, -3, 0, 0},
            {-1, 0, 0, 0}
    };

    private static final int[][] MIXED_NEGATIVE_POSITIVE_DATA = {
            {-7, -8, -3, 10},
            {-4, -5, -9, 0},
            {-2, -3, 0, 0},
            {-1, 0, 0, 0}
    };

    private static final int[][] DEMO_DATA_WITH_NON_ZERO_NEUTRAL_VALUES   = {
            {59, 207, 98, 95},
            {87, 1, 70, -80},
            {36, 41, 0, 1000},
            {23, 13, 12, 500}
    };

    protected PyramidSolver solver;
    protected PyramidSolver solver2;

    @BeforeEach
    public void setUp() {
        solver = new YourSolver();
        solver2 = new YourSolver2();
    }

    @Test
    public void solverHandlesSampleData() {
        Pyramid pyramid = new Pyramid(SAMPLE_DATA);
        //"Max path in Sample pyramid"
        assertEquals(24, solver.pyramidMaximumTotal(pyramid));
        assertEquals(24, solver2.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverHandlesDemoData() {
        Pyramid pyramid = new Pyramid(DEMO_DATA);
        //"Max path in Demo pyramid"
        assertEquals(353, solver.pyramidMaximumTotal(pyramid));
        assertEquals(353, solver2.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverSurvivesLargeData() {
        PyramidGenerator generator = new RandomPyramidGenerator(MAX_DEPTH, 1000);
        Pyramid pyramid = generator.generatePyramid();
        //"Max path in a large pyramid not positive",
        assertTrue(solver.pyramidMaximumTotal(pyramid) > 0L);
        assertTrue(solver2.pyramidMaximumTotal(pyramid) > 0L);
    }

    @Test
    public void solverHandlesRandomData() {
        RandomPyramidGenerator.setRandSeed(25321L);  // ensure pyramid contents
        final PyramidGenerator generator = new RandomPyramidGenerator(5, 99);
        final Pyramid pyramid = generator.generatePyramid();
        //"Max path in 'random' pyramid"
        assertEquals( 398, solver.pyramidMaximumTotal(pyramid));
        assertEquals( 398, solver2.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void givenPyramidWithNegativeValues_shouldReturnCorrectMaximumValue() {
        final Pyramid pyramid = new Pyramid(NEGATIVE_DATA);

        assertEquals(-11, solver.pyramidMaximumTotal(pyramid));
        assertEquals(-11, solver2.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void givenPyramidWithMixedValues_shouldReturnCorrectMaximumValue() {
        final Pyramid pyramid = new Pyramid(MIXED_NEGATIVE_POSITIVE_DATA);

        assertEquals(-3, solver.pyramidMaximumTotal(pyramid));
        assertEquals(-3, solver2.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void givenDemoDataWithNonZeroNeutralValues_shouldReturnTheCorrectResult() {
        Pyramid pyramid = new Pyramid(DEMO_DATA_WITH_NON_ZERO_NEUTRAL_VALUES);
        assertEquals( 353, solver.pyramidMaximumTotal(pyramid));
        assertEquals( 353, solver2.pyramidMaximumTotal(pyramid));
    }

}
