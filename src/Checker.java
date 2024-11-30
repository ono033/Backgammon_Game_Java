

public class Checker {

    public CheckerProperties type;

    public  Checker(String type){
// hi
    if (CheckerProperties.X == CheckerProperties.valueOf(type)){
    this.type = CheckerProperties.X;
    }

        else if (CheckerProperties.O == CheckerProperties.valueOf(type)){
            this.type = CheckerProperties.O;
        }

        else{
            System.out.println("Invalid type, must be X or O");
        }

    }

    public Checker(int playerNumber){
        if(playerNumber == 1){
            this.type = CheckerProperties.O;
        }
        else if(playerNumber==2) this.type = CheckerProperties.X;

        else{
            System.out.println("Invalid type, must be X or O");
        }

    }

    public void printChecker() {
        if (type != null) {
            System.out.println("Checker type: " + type);
        } else {
            System.out.println("Invalid checker, no type assigned.");
        }
    }


    public int getPlayerNumber(){

        if(type == CheckerProperties.O){
            return 1;
        }


            return 2;


    }


    public CheckerProperties getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return type == CheckerProperties.X ? "X" : "O";
    }

    // public void isDifferntType(){}




}
