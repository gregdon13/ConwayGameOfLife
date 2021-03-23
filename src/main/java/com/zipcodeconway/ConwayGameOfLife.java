package com.zipcodeconway;

import java.util.ArrayList;
import java.util.List;

public class ConwayGameOfLife {
    int dim;
    int[][] startMatrix = new int[dim][dim];
    List<Integer> neighborList;

    public ConwayGameOfLife(Integer dimension) {
        this.dim = dimension;
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.dim = dimension;
        this.startMatrix = startmatrix;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        return new int[dimension][dimension]; }

    public int[][] simulate(Integer maxGenerations) {
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
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
            } else if (counter == 2 || counter == 3) {
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
