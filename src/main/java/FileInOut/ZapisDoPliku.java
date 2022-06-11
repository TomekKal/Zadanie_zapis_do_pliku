package FileInOut;

import java.io.*;
import java.util.ArrayList;

/*mamy plik input.txt w którym są jakieś słowa/zdania (jedno słowo/zdanie w jednej linijce) np.
        kajak
        pies
        lol
        woda
        radar
        oko w oko
        it camp
        comarch
        kobyła ma mały bok
        zadanie polega na tym aby wczytać ten plik i wygenerować nowy plik wyniki.txt
        w którym są wypisane tylko te linijki które są palindromami.
        Palindrom to fraza która czytana od przodu i od tyłu brzmi tak samo np. kajak.
        Spacje oraz wielkości liter nie mają znaczenia.
        Zakłądamy że w linijce nie wystąpią inne znaki niż litery i spacje*/


public class ZapisDoPliku {

    public static void main(String[] args) {

        String str;
        boolean palindrom;
        ArrayList<String> arrayList = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            while ((str = br.readLine()) != null) {
                palindrom = true;
                for (int i = 0 ; i < str.length()/2-1; i++){
                    if (str.charAt(i) != str.charAt(str.length()-i-1) ){
                        palindrom = false;
                        break;
                    }
                }

                if( palindrom ) {
                    System.out.println(str);
                    arrayList.add(str);
                }
            }
        }catch (IOException exc){
            System.out.println("Błąd wejścia-wyjścia: " +exc);
        }

        System.out.println(arrayList);


       try (FileWriter fw = new FileWriter("input.txt")) {

           for (String strLine : arrayList){
               fw.write(strLine + System.lineSeparator());
           }

       } catch (IOException exc) {
            System.out.println("Błąd wejścia wyjścia: " +exc);
        }


    }
}
