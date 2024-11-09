

public class Checker {

    private CheckerProperties type;

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


    @Override
    public String toString() {
        return type == CheckerProperties.X ? "X" : "O";
    }

    // public void isDifferntType(){}




}
