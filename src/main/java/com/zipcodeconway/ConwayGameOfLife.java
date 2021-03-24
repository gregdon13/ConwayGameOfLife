package com.zipcodeconway;

import java.util.ArrayList;
import java.util.List;

public class ConwayGameOfLife {
    private SimpleWindow displayWindow;
    int dim;
    int[][] startMatrix;
    List<Integer> neighborList;
    int generations;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.dim = dimension;
        this.generations = 0;
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.dim = dimension;
        this.startMatrix = startmatrix;
        this.generations = 0;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] ranStart = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                ranStart[i][j] = oneOrZero();
            }
        }
        return ranStart;
    }

    private int oneOrZero() {
        Double num = Math.random();
        if (num < 0.5) {
            return 0;
        } else {
            return 1;
        }
    }

    public int[][] simulate(Integer maxGenerations) {
        startMatrix = createRandomStart(dim);
        for (int k = 0; k <= maxGenerations; k++) {
            this.displayWindow.display(startMatrix, generations);
            int[][] nextGen = new int[dim][dim];
            copyAndZeroOut(startMatrix, nextGen);
            generations++;
            this.displayWindow.sleep(125);
        }
        return startMatrix;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                current[i][j] = isAlive(i, j, next);
            }
        }
        startMatrix = current;
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private int isAlive(int row, int col, int[][] world) {
        makeNeighborList(row, col, world);
        int counter = 0;
        for (int i : neighborList) {
            if (i == 1) {
                counter++;
            }
        }
        if (world[row][col] == 1) {
            if (counter < 2) {
                return 0;
            } else if (counter == 2) {
                return 1;
            } else if (counter == 3) {
                return 1;
            } else {
                //catches more than 3
                return 0;
            }
        } else {
            //catches if current index is 0
            if (counter == 3) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public void makeNeighborList (int row, int col, int[][] world) {
        neighborList = new ArrayList<Integer>();
        int upLeft = world[(row - 1 + dim)%dim][(col - 1 + dim)%dim];
        int up = world[(row - 1 + dim)%dim][col];
        int upRight = world[(row-1+dim)%dim][(col+1+dim)%dim];
        int left = world[row][(col-1+dim)%dim];
        int right = world[row][(col+1+dim)%dim];
        int downLeft = world[(row+1+dim)%dim][(col-1+dim)%dim];
        int down = world[(row+1+dim)%dim][(col)];
        int downRight = world[(row+1+dim)%dim][(col+1+dim)%dim];
        neighborList.add(upLeft);
        neighborList.add(up);
        neighborList.add(upRight);
        neighborList.add(left);
        neighborList.add(right);
        neighborList.add(downLeft);
        neighborList.add(down);
        neighborList.add(downRight);
    }
}
