package MainPackage;

import java.util.*;

// Similar logic to this but included my state https://javatutorial.net/breadth-first-search-example-java
public class BFS {

    public State BFS_execution(State initialState) {

        //Create our FIFO queue first and then create an arraylist to store visited state
        LinkedList<State> queue = new LinkedList<State>();
        ArrayList<State> visited = new ArrayList<State>();

        //Add our state to our queue and our visited arraylist
        queue.add(initialState);
        visited.add(initialState);

        //check that we have elements in our queue. Our queue however should never finish before successful execution
        while (queue.size()!=0) {

            State state;
            try{
                //use poll method to remove the head of the list
                state = queue.poll();
            }catch (NullPointerException ne){
                System.out.println("Something went wrong");
                return null;
            }

            //generate children for father node
            List<State> children = state.childrenGen();


            //when we find our target return
            for (State child : children) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    if (child.isGoal()) {
                        return child;
                    }
                    queue.add(child);
                }
            }
        }

        System.out.println("You should not be here. There is solution to this problem.SOMETHING WENT WRONG");
        return null;

    }


    public void printer(State initialState) {

        //take the termination state
        State termState = BFS_execution(initialState);

        //In case something goes wrong
        if (null == termState) {
            System.out.println("Something went wrong.No solution found.");
            return;
        }

        List<State> path = new ArrayList<State>();
        State state = termState;

        //We are starting to backtrack from goal state to initial state.
        while(true) {
            path.add(state);
            state = state.getParentState();
            if(state.isInitState()){
                path.add(state);
                break;
            }
        }


        //Use Java 8 functions to make our lives easier.
        //Reverse order of our list to show everything in the right way, and then print everything using lambdas
        Collections.reverse(path);
        path.forEach(child -> {
            System.out.println(child.myMsg());
        });

        int steps = path.size() - 1;
        System.out.println("\nNumber of steps until goal: " + steps);

    }


}