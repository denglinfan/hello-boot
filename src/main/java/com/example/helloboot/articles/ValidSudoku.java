package com.example.helloboot.articles;

/**
 *
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *  Each row must contain the digits 1-9 without repetition.
 *  Each column must contain the digits 1-9 without repetition.
 *  Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 *  Input:
 *  [
 *      ["5","3",".",".","7",".",".",".","."],
 *      ["6",".",".","1","9","5",".",".","."],
 *      [".","9","8",".",".",".",".","6","."],
 *      ["8",".",".",".","6",".",".",".","3"],
 *      ["4",".",".","8",".","3",".",".","1"],
 *      ["7",".",".",".","2",".",".",".","6"],
 *      [".","6",".",".",".",".","2","8","."],
 *      [".",".",".","4","1","9",".",".","5"],
 *      [".",".",".",".","8",".",".","7","9"]
 *  ]
 *  Output: true
 *
 *
 *  Input:
 *  [
 *      ["8","3",".",".","7",".",".",".","."],
 *      ["6",".",".","1","9","5",".",".","."],
 *      [".","9","8",".",".",".",".","6","."],
 *      ["8",".",".",".","6",".",".",".","3"],
 *      ["4",".",".","8",".","3",".",".","1"],
 *      ["7",".",".",".","2",".",".",".","6"],
 *      [".","6",".",".",".",".","2","8","."],
 *      [".",".",".","4","1","9",".",".","5"],
 *      [".",".",".",".","8",".",".","7","9"]
 *  ]
 *  Output: false
 *  Explanation: Same as Example 1, except with the 5 in the top left corner being
 *
 *
 *  Note:
 *  A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 *  Only the filled cells need to be validated according to the mentioned rules.
 *  The given board contain only digits 1-9 and the character '.'.
 *  The given board size is always 9x9.
 */
public class ValidSudoku {

    /**
     * valid whether the filled cells number follow by the rule
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        return true;
    }

    public static void main(String[] args){

    }
}
