package tcc_zanza.no_ip.biz.thirtyminutes.VO;

import java.util.Random;

/**
 * Created by VINICIUS on 9/14/2015.
 */
public class Generator {

    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static final String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static final String[] simbols = {"!", "@", "#", "$", "%", "¨", "&", "*", "(", ")", "-", "=", "_", "+", "/", "*", "-", ".", ",", ";", ".", "<", ">", "{", "}", "^"};

    private static int size = 0;

    private static boolean flegLETTERS = false;
    private static boolean flegLetters = true;
    private static boolean flegNumbers = false;
    private static boolean flegSimbols = false;

    public static void setSize(int size){
        Generator.size = size;
    }

    public static int getSize(){
        return Generator.size;
    }

    public static boolean isFlegLETTERS() {
        return flegLETTERS;
    }

    public static void setFlegLETTERS(boolean flegLETTERS) {
        Generator.flegLETTERS = flegLETTERS;
    }

    public static boolean isFlegLetters() {
        return flegLetters;
    }

    public static void setFlegLetters(boolean flegLetters) {
        Generator.flegLetters = flegLetters;
    }

    public static boolean isFlegNumbers() {
        return flegNumbers;
    }

    public static void setFlegNumbers(boolean flegNumbers) {
        Generator.flegNumbers = flegNumbers;
    }

    public static boolean isFlegSimbols() {
        return flegSimbols;
    }

    public static void setFlegSimbols(boolean flegSimbols) {
        Generator.flegSimbols = flegSimbols;
    }

    public static String generatePassword() {

        String pass = new String();

        //random category(letter, number, simbols...)
        Random randCat = new Random();

        //0-LETTER, 1-letter, 2-number, 3-simbol
        int category;

        //pass size
        Random randQuantity = new Random();

        int quantity = randQuantity.nextInt(50);

        //LETTER's position
        Random randLETTER = new Random();

        //letter's position
        Random randLetter = new Random();

        //number's position
        Random randNumber = new Random();

        //simbol's position
        Random randSimbol = new Random();

        if(size > 0){
            quantity = size;
        }

        for (int i = 0; i < quantity; i++) {

            category = randCat.nextInt(4);

            if (category == 0 && Generator.isFlegLETTERS()) {

                pass += Generator.LETTERS[randLETTER.nextInt(26)];

            }

            if (category == 1 && Generator.isFlegLetters()) {

                pass += Generator.letters[randLetter.nextInt(26)];

            }

            if (category == 2 && Generator.isFlegNumbers()) {

                pass += Generator.numbers[randNumber.nextInt(10)];

            }

            if (category == 3 && Generator.isFlegSimbols()) {

                pass += Generator.simbols[randSimbol.nextInt(26)];

            }

        }


        return pass;
    }

}