package ru.lantan.program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ru.lantan.program.Validate;

import javax.swing.*;

public class Calculator {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String msgError = ANSI_RED + "Неверная команда" + ANSI_RESET;
    public static int format = 0;
    public static String[] answer = new String[2];
    public Validate validate = new Validate();
    public static double sum = 0;

    public void greeting(){
        System.out.println("==============================================");
        System.out.println( ANSI_GREEN +  " Приветствуем Вас в программе \"Калькулятор\" " + ANSI_RESET);
        System.out.println("==============================================");
        System.out.println("Доступные команды:");
        System.out.println( ANSI_GREEN + "ADD *число*");
        System.out.println("SUBTRACT *число*");
        System.out.println("MULTIPLY *число*");
        System.out.println("DIVIDE *число*" + ANSI_RESET);
        System.out.println("Для получения результата введите: " + ANSI_GREEN + "RESULT" + ANSI_RESET);
        System.out.println("==============================================" );
    }

    public void  getFormat(){
        do {
            try{
                if( !(validate.isIntNumber(reader.readLine())) ) throw new NumberFormatException();
                int n = Integer.parseInt(reader.readLine());
                if( (n > 0) && (n < 9)  )
                    format = n ;
                else
                    throw new NumberFormatException();
            }catch (NumberFormatException | IOException  e){
                System.out.println(msgError);
            }
        }while( format == 0 );
    }

    public void getOperation() throws IOException {
        do{
            answer = reader.readLine().split(" ");
            if(validate.isValidOperation(answer) && answer[0].equalsIgnoreCase("Result") )
                System.out.println(sum);
            else if( validate.isValidOperation(answer) && answer.length == 2 )
                addOperation(answer[0], Double.parseDouble(answer[1]));
            else
                System.out.println(msgError );
        }while( !(validate.isValidOperation(answer) && answer[0].equalsIgnoreCase("Result")) );
    }

    private void addOperation(String s, double num){
        switch (s.toUpperCase()){
            case "ADD": {
                sum += num;
                System.out.println("ADD " +  num + " for result");
                break;
            }
            case "SUBTRACT":{
                sum -= num;
                System.out.println("SUBTRACT " +  num + " for result");
                break;
            }
            case "MULTIPLY": {
                sum *= num;
                System.out.println("MULTIPLY " +  num + " for result");
                break;
            }
            case "DIVIDE": {
                if (num != 0) {
                    sum /= num;
                    System.out.println("DIVIDE " +  num + " for result");
                } else {
                    System.out.println(ANSI_RED + "На ноль делить нельзя" + ANSI_RESET);
                }
                break;
            }
            default:{
                System.out.println(msgError);
            }
        }
    }
}
