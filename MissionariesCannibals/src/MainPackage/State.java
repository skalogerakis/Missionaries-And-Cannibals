package MainPackage;

import java.util.ArrayList;

public class State {

    private int canLeft;
    private int misLeft;
    private int canRight;
    private int misRight;
    private int boat;

    private State parentState;

    //------------------Constructor---------------------
    public State(int canLeft, int misLeft, int boat,  int canRight, int misRight) {
        this.canLeft = canLeft;
        this.misLeft = misLeft;
        this.boat = boat;
        this.canRight = canRight;
        this.misRight = misRight;
    }

    //------------------Validity-Checkers---------------------
    public boolean isGoal() {
        //our final target check
        if(this.misLeft ==0 && this.canLeft ==0 && this.boat==1 && this.misRight ==3 && this.canRight ==3){
            return true;
        }
        return false;
    }

    public boolean isValid() {
        //our state check validity
        if ((this.misLeft >= 0 && this.misRight >= 0 && this.canLeft >= 0 && this.canRight >= 0 && (this.misLeft >= this.canLeft) && (this.misRight >= this.canRight))
                || this.misLeft == 0 || this.misRight == 0) {
            return true;
        }
        return false;
    }

    public boolean isInitState() {
        //our init state check
        if(this.misLeft ==3 && this.canLeft ==3 && this.boat==0 && this.misRight ==0 && this.canRight ==0){
            return true;
        }
        return false;
    }

    //------------------Other functionality---------------------
    public ArrayList<State> childrenGen() { ArrayList<State> successors = new ArrayList<State>();

        int flag = 0;
        int pos = 0;
        /**
         * Boat modes:
         * 0 on the left side
         * 1 on the right
         */

        //Use a trick here with a flag to simplify my code
        if (boat == 0) {
            flag=-1;
            pos=1;
        }else{
            flag = 1;
            pos=0;
        }

        //Loop over the available states.
        for(int m=0;m<3;m++){
            for(int c=0;c<3;c++){
                //No more than two people can travel and also don't check the same condition all over
                if(c+m>2||(c==0 && m ==0)){
                    continue;
                }

                //Check if the new state is a valid one and proceed
                State newState = new State(canLeft +(c*flag), misLeft +(m*flag), pos, canRight -(c*flag), misRight -(m*flag));
                if(newState.isValid()){
                    newState.setParentState(this);
                    successors.add(newState);
                }
            }
        }

        return successors;
    }

    //------------------Printers---------------------
    public String myMsg(){
        String boatDir = boat == 0 ? "Left " : "Right";
        return "Cannibals Left:" + this.canLeft + ",\tMissionaries Left:" + this.misLeft + ",\tBoat Side:" + boatDir + ",\tCannibals Right:" + this.canRight + ",\tMissionaries Right:" + this.misRight;
    }


    //------------------Setters-Getters---------------------
    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }


}