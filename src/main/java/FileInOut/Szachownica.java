package FileInOut;

/*2. Mamy plik szachownica.txt w którym zaprezentowana jest szachownica na której jest jeden król i dowolna liczba królowych np.
        -Q------
        ----Q---
        --------
        --------
        -K----Q-
        --------
        ---Q----
        --------
        - oznacza puste pole, K oznacza króla, Q oznacza królową, oczywiście szachownica zawsze jest wielkości 8x8.
        Program ma wypisywać Y jeśli król może zostać zbity przez którąkolwiek królową, N jeśli nie.
        Zgodnie z zasadami w szachach królowa może poruszać się dowolną ilość pól w poziomie lub dowolną ilość pól w pionie lub dowolną ilość pól po przekątnej.
        W powyższym przykłądzie król może zostać zbity przez każda z królowych.
        Program ma wypisać Y (wystarczyła by jedna aby program wypisywał Y)
        W poniższym przykłądzie król nie może zostać zbity przez żadną królową i program ma wypisywać N.
        --------
        ------Q-
        --------
        ---Q----
        --Q-----
        --------
        -----K--
        -Q------*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Szachownica {

    public static void main(String[] args) {

        String str;
        ArrayList<String> stringArrayList = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader("chess.txt"))) {
            while ((str = br.readLine()) != null) {
                stringArrayList.add(str);
            }
            System.out.println(stringArrayList);
        } catch (IOException exc) {
            System.out.println("Błąd wejścia-wyjścia: " + exc);
        }

        System.out.println("Pozycja króla, wiersz: " + findKing(stringArrayList)[0] + " pozycja: " + findKing(stringArrayList)[1]);

        System.out.println("\nCzy król został zbity: " + ifKingKilled(stringArrayList));
    }

    private static boolean ifKingKilled(ArrayList<String> stringArrayList) {

        boolean kingKilled = false;
        String line;
        String axis;

        // If Queen is in King line

        line = stringArrayList.get(findKing(stringArrayList)[0]);

        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == 'Q') {
                kingKilled = true;
            }
        }

        // If Queen is in King column

        for (String column : stringArrayList) {
            if (column.charAt(findKing(stringArrayList)[1]) == 'Q') {
                kingKilled = true;
            }
        }

        // If Queen is in axis

        for (int i = 0; i < stringArrayList.get(i).length() -1 ; i++) {
            axis = stringArrayList.get(i);
            for (int j = 0; j < stringArrayList.get(i).length() - 1; j++) {
                System.out.print(axis.charAt(j));

                if ((j == (findKing(stringArrayList)[1] + Math.abs(findKing(stringArrayList)[0] - i))
                        || j == (findKing(stringArrayList)[1] - Math.abs(findKing(stringArrayList)[0] - i))) && (axis.charAt(j) == 'Q')) {
                    kingKilled = true;
                    System.out.println("\nBicie z linii: " + axis);
                    break;
                }
            }
        }


        return kingKilled;
    }

    private static int[] findKing(ArrayList<String> stringArrayList) {
        int[] kingPositon = new int[2];
        kingPositon[0] = 0;
        boolean lineSet = false;
        for (String line : stringArrayList) {
            for (int i = 0; i < line.length() ; i++) {
                if (line.charAt(i) == 'K') {
                    kingPositon[1] = i;
                    lineSet = true;
                    break;
                }
            }

            if (lineSet == true) {
                break;
            } else kingPositon[0] = kingPositon[0] + 1;

        }

        return kingPositon;
    }
}
