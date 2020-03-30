package MainPackage;

/**
 * Author: Stefanos Kalogerakis
 * Date: March 30, 2020
 * Implementation: Missionaries and Cannibals problem using BFS algorithm
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Execution started. Below you can see the results");

        //Create our initial state
        State initialState = new State (3, 3, 0, 0, 0);
        BFS search = new BFS();
        search.printer(initialState);
    }

}