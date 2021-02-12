package ru.mtusi;

public class Palindrome {
    public static void main(String[] args) {
        for (int i=0; i<args.length; i++) {
            String s = args[i];
            if(isPalidrome(s)){
                System.out.println(s+" is Palidrome.");
            } else{
                System.out.println(s+" is not Palidrome.");
            }
        }
    }

    public static String reverseString (String s){
        String resultString = new String();
        resultString = "";
        for (int i=s.length()-1; i>=0;i--){
            resultString += s.charAt(i);
        }
        return resultString;
    }

    public static boolean isPalidrome(String s){
        String reverseString = reverseString(s);
        boolean result = s.equals(reverseString);
        return result;
    }
}
