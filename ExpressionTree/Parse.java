import java.util.Stack;

/***********************************************************************
 * This class is a subclass to the main class calc.java. In its constructor
 * it passes in a String object which will be the infix from the main class
 * and then converts it to a postfix string in the toPostFix method.
 *************************************************************************/

public class Parse {
	//Initializes two variables. A string and a Stack.
	public String infixStr;
    public Stack<Character> oprStack;

    //Constructor that takes in a String and initializes a Stack.
    public Parse(String infixStr){
        oprStack = new Stack<Character>();
        this.infixStr = infixStr;
    }

    //Method that converts the infix to postfix.
    public String toPostFix(){
    	//Initializes a Stringbuffer that is the length of the Infix
    	//string.
        StringBuffer buf = new StringBuffer(infixStr.length());

        //Goes through the length of the infix string.
        for(int i=0;i<infixStr.length();i++){
        	//scans each character of the infix and determines
        	//the value of it using the ValueofOpr method
            char str = (infixStr.charAt(i));            
            if(valueOfOpr(str) == 0){
                buf.append(str);

                //Creates the conversion of infix to postfix
                if(i != infixStr.length()-1 && !oprStack.isEmpty()){
                    char nextChar = (infixStr.charAt(i+1));
                    char opStack = oprStack.peek();

                    int compare =  compareOperators (opStack,nextChar); 

                    if(compare >0 && valueOfOpr(opStack) != 6) {
                        opStack = oprStack.pop();
                        buf.append(opStack);
                    }
                }               
            }   

            if(i == infixStr.length()-1){
                if(valueOfOpr(str) > 0 && valueOfOpr(str) != 7) throw new RuntimeException("Invalid Expression");
                while(!oprStack.isEmpty()){
                    char temp = oprStack.pop();
                    if(valueOfOpr(temp) != 6)buf.append(temp);
                }
            }else if (valueOfOpr(str) > 0){

                if(oprStack.isEmpty()) {
                    oprStack.push(str);
                }else{ 

                    if(valueOfOpr(str) == 7){
                        while(!oprStack.isEmpty()){
                            char temp = oprStack.pop();
                            if(valueOfOpr(temp) != 6)buf.append(temp);
                        }                   
                    }else{
                        char opStack = oprStack.pop();
                        int compare =  compareOperators (opStack,str);  

                        if(compare >0) {
                            oprStack.push(str);oprStack.push(opStack);                  
                        }else{
                            oprStack.push(opStack);oprStack.push(str);
                        }   
                    }
                }                   
            }



        }

        return buf.toString();
    }


    //Compares the two operators to determine which one should be
    //pushed or popped.
    public int compareOperators(char op1 , char op2){       
        return valueOfOpr( op1) - valueOfOpr( op2) ;
    }


    //Determines the value of the operators so that it can 
    //be used in the compareOperators function.
    public int valueOfOpr(char op){
        int value = 0;

        switch (op) {
        case '%':
            value = 5;
            break;
        case '*':
            value = 4;
            break;
        case '/':
            value = 3;
            break;
        case '+':
            value = 2;
            break;          
        case '-':
        case '–':
            value = 1;
            break;  
        case '[':           
        case ']':
        case '(':       
            value = 6;  
            break;
        case ')':
            value = 7;  
            break;          
        default:
            break;
        }

        return value;
    }



}


