package ru.mtusi;

/*
этот класс выводит простые числа от 2 до 100
 */

public class Primes {

    public static void main(String[] args) {
        for (int i=2 ;i<100; i++){
            // Проверка простое число.
            if (isPrime(i)){
                System.out.println(i);
            }
        }
    }

    // Этот метод отвечает является ли число простым.
    public static boolean isPrime(int n){
        for (int i=2; i<n; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
