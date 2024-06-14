package org.example.lesson14juni;

public class Calculator {
    //Написать программу "Калькулятор" таким образом,
    // что бы она принимала от пользователя вычислеяемое
    // математическое выражение в виде одной строки.
    //Результат вычислений необходимо вывести с точностью до двух чисел после
    // запятой в формате <значение переменной argumentOne> <знак математической операции>
    // <значение переменной argumentTwo> = <значение переменной результата>.
//Поддерживаемые операции + - * /
    // Например, так 10.5*5+1-7.1 и последовательно выполняла бы указанные арифметические операции
    public String calculateResult(String inputString) {
        int countOperations = getCounterOperations(inputString); // количество операций, чтобы знать кол-во чисел в выражении
        double[] numbers = getNumbers(inputString, countOperations + 1);
        char[] operators = getOperators(inputString, countOperations);
        double result =numbers[0];
        for (int i=0; i< numbers.length-1; i++){
            result=calculate(result,operators[i],numbers[i+1]);
        }
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0; i<numbers.length-1; i++){
            stringBuilder.append(numbers[i])
                    .append(" ")
                    .append(operators[i])
                    .append(" ");
        }
        stringBuilder.append(numbers[numbers.length-1]).append(" = ").append(result);
        return stringBuilder.toString();
    }

// метод делает операцию между двумя числами
    private double calculate(double number1, char operator, double number2) {
        switch (operator){
            case '+':
                return number1+number2;
            case  '-':
                return number1-number2;
            case  '*':
                return number1*number2;
            case '/':
                if (number2==0){
                    System.out.println("деление на 0");
                    System.exit(0);
                }
                return number1/number2;
            default:
                System.out.println("неподдерживаемая матем. операция");
                System.exit(0);
                return 0;
        }
    }

    private char[] getOperators(String inputString, int countOperations) {
        char[] operators = new char[countOperations];
        countOperations = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (isOperation(inputString.charAt(i))) {
                operators[countOperations] = inputString.charAt(i); // заполняем массив мат. операт// оров
                countOperations++;
            }
        }
        return operators;
    }

    // метод getNumbers создает массив нужного размера и заполняет его числами

    private double[] getNumbers(String inputString, int countNumbers) {
        double[] numbers = new double[countNumbers];
        int startIndex = 0;
        countNumbers = 0; // обнуляем счетчик массива для чисел
        for (int i = 0; i < inputString.length(); i++) {
            // вырезаем число до !!! матем знака
            if (isOperation(inputString.charAt(i))) {
                String stringNumber = inputString.substring(startIndex, i); // вырезаем число в виде подстроки
                double number = Double.parseDouble(stringNumber); // преобразуем подстроку в число типа Дабл
                numbers[countNumbers] = number; // заносим число в массив
                countNumbers++; // изменяем счетчик массива
                startIndex = i + 1;
            }
            if (i == inputString.length() - 1) { // это условие для последнего числа
                String stringNumber = inputString.substring(startIndex);
                double number = Double.parseDouble(stringNumber);
                numbers[countNumbers] = number;
            }
        }
        return numbers;
    }

    // метод getCounterOperations считает количество математических операций (+ ,- ,*, / )
    private int getCounterOperations(String inputString) {
        int counter = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (isOperation(inputString.charAt(i))) {  // проверяем является ли символ по индексу
                counter++;                              // i математич. операцией
            }
        }
        return counter;
    }

    // проверяем является ли символ по индексу i математич. операцией
    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }


}
