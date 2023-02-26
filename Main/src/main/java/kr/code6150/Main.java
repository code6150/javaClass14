package kr.code6150;

import kr.code6150.database.DbManager;

import java.util.Scanner;

public class Main {

    private static final int KEY = 123456789;

    public static void main(String[] args) {

        DbManager db = DbManager.getInstance();

        // ^ -> xor = 두 값이 같으면 ( 0 ) 다르면 ( 1 )
        // 비트 연산자
        // byte 를 이루는 8개의 2진수가 있는데, 그 한자리를 bit 라고 부름.
        // 1byte => 8byte
        // 1024byte => 1kbyte
        // int ( 4byte ) => 32 bit
        // 0000 0000 0000 0000 0000 0000 0000 1010 => 10

        Scanner sc = new Scanner(System.in);

        System.out.print("비번 입력 부탁 : ");
        String password = sc.nextLine();

        // 문자열은 비트연산이 불가함.
        // char 는 비트연산이 가능함.
        // 문자열을 이루는 요소 ( char )
        char[] array = password.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) (array[i] ^ KEY);
        }
        System.out.println(array);

        char[] array1 = password.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) (array[i] ^ KEY);
        }
        System.out.println(array1);

    }

}
