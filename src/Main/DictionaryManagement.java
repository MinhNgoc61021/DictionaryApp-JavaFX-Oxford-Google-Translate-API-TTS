package Main;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public  class DictionaryManagement extends Dictionary {
     public static List<String> addup=new ArrayList<>();

    /*
    public void insertFromCommandline() {
        array = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int s = input.nextInt();
        int i = 0;

        while (i < s) {

            array.add(word_);
            i++;
        }
    } */
    public static void showAllWords()
    {
        for(int i=0;i<array.size();i++)
        {
            //Word getword_=array.get(i);
           // System.out.println(i+1+"\t|"+getword_.print());}

            System.out.println(array.get(i).getWord_target() + " " + array.get(i).getWord_explain());}
    }
    public static  String dictionaryLookup(String wordToLookup){
        for(int i=0;i<array.size();i++) {

            if (array.get(i).getWord_target().contains(wordToLookup)) {
                return array.get(i).getWord_explain();
            }
            //if (array.get(i).getWord_target().equals(wordToLookup)) return array.get(i).getWord_explain();
        }

        return "";
    }

    public static void  removeWordFromDitionary(String id)
    {

        for(int i=array.size()-1;i>=0;i--) {
            Word word = array.get(i);
            String removing=word.getWord_target();
            if (removing.equals(id)) {
                array.remove(i);
            }
        }
    }
    public static void InsertFromFile() throws IOException {
        //FileReader file=new FileReader(S);
        Scanner sc = null;
        try {

            sc = new Scanner(new File("dictionaries.txt"));

            while (sc.hasNext()) {
                String word = sc.next();
                String word_mean = sc.nextLine();
                array.add(new Word(word, word_mean));
            }
            for (int i = 0; i < array.size(); i++) {
                listWordTarget.add(array.get(i).getWord_target());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryExportToFile()
    {
        try{
            FileWriter write=new FileWriter("dictionaries.txt");
            Writer output=new BufferedWriter(write);
            for(int i=0;i<array.size();i++ )
            {
                Word outfile=array.get(i);
                output.write(outfile.getWord_target()+"\t"+outfile.getWord_explain()+"\n");
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> dictionarySearcher(String wordSearch) {

            for(int i =0; i < array.size(); i++)
            {
                //wordSearch = wordSearch + input.next();

                if(array.get(i).getWord_target().toLowerCase().contains(wordSearch.toLowerCase()))
                {
                    addup.add(array.get(i).getWord_target());
                }
                else
                    continue;
            }
            return addup;

    }
}