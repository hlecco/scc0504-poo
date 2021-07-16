package ex2;

import java.util.Arrays;

public class Ex2 {

    public static <T extends Comparable> void InsertionSort(T[] vec) {
        T temp;
        int i;

        for (int j = 0; j < vec.length; j++) {
            temp = vec[j];
            i = j - 1;
            while (i > -1 && vec[i].compareTo(temp) > 0) {
                vec[i + 1] = vec[i];
                i -= 1;
            }
            vec[i + 1] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] vecInt = new Integer[5];
        vecInt[0] = 3;
        vecInt[1] = -2;
        vecInt[2] = 1;
        vecInt[3] = -6;
        vecInt[4] = 80;

        System.out.println(Arrays.toString(vecInt));
        InsertionSort(vecInt);
        System.out.println(Arrays.toString(vecInt));

        Float[] vecFloat = new Float[5];
        vecFloat[0] = 3.0F;
        vecFloat[1] = -2.42F;
        vecFloat[2] = 1.31F;
        vecFloat[3] = -6.123F;
        vecFloat[4] = 80.1901F;

        System.out.println(Arrays.toString(vecFloat));
        InsertionSort(vecFloat);
        System.out.println(Arrays.toString(vecFloat));

        MyString[] vecString = new MyString[10];
        vecString[0] = new MyString("um");
        vecString[1] = new MyString("");
        vecString[2] = new MyString("tres");
        vecString[3] = new MyString("dez");
        vecString[4] = new MyString("dois");
        vecString[5] = new MyString("nove");
        vecString[6] = new MyString("cinco");
        vecString[7] = new MyString("sete");
        vecString[8] = new MyString("quatro");
        vecString[9] = new MyString("nove");

        System.out.println(Arrays.toString(vecString));
        InsertionSort(vecString);
        System.out.println(Arrays.toString(vecString));
    }

}
