public class Checker {

    private CheckerProperties type;

    public void Checker(String type){
// hi
    if (CheckerProperties.X == CheckerProperties.valueOf(type)){
    this.type = CheckerProperties.X;
    }

        if (CheckerProperties.O == CheckerProperties.valueOf(type)){
            this.type = CheckerProperties.O;
        }

        else{
            System.out.println("Invalid type, must be X or O");
        }

    }

    // public void isDifferntType(){}




}
