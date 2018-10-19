package Main;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public  class DictionaryManagement extends Dictionary {
    public static List<String> add_up =new ArrayList<>();
    public static List<String> removeout=new ArrayList<>();
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
    /*public static void showAllWords()
    {
        for(int i=0;i<array.size();i++)
        {
            //Word getword_=array.get(i);
           // System.out.println(i+1+"\t|"+getword_.print());}
            System.out.println(array.get(i).getWord_target() + " " + array.get(i).getWord_explain());}
    }*/
    public static  String dictionaryLookup(String wordToLookup){
        for(int i=0;i<array.size();i++) {

            if (array.get(i).getWord_target().equals(wordToLookup)) {
                return array.get(i).getWord_explain();
            }
        }

        return "";
    }
    public static List<String> addmoreword(String addword_target,String addword_explain)
    {
        Word addnewword=new Word(addword_target,addword_explain);
        array.add(addnewword);
        add_up.add(addnewword.getWord_target());
        return add_up;
    }
    public static List<String> removeWordFromDitionary(String id)
    {

        for(int i=array.size()-1;i>=0;i--) {
            Word word = array.get(i);
            String removing=word.getWord_target();
            if (removing.toLowerCase().equals(id.toLowerCase())) {
                array.remove(i);
            }
            else
            {
                removeout.add(array.get(i).getWord_target());
            }
            Collections.sort(removeout);

        }
        return removeout;
    }
    public static void InsertFromFile() throws IOException {
        //FileReader file=new FileReader(S);
        Scanner sc = null;
        try {

            sc = new Scanner(new File("src/Database/dictionaries.txt"));

            while (sc.hasNext()) {
                String word = sc.next();
                String space=sc.next();
                String word_mean = sc.nextLine();
                array.add(new Word(word, word_mean));
            }
            for (int i = 0; i < array.size(); i++) {
                listWordTarget.add(array.get(i).getWord_target());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryExportToFile()
    {
        try{
            FileWriter write=new FileWriter("src/Database/dictionaries.txt");
            Writer output=new BufferedWriter(write);
            for(int i=0;i<array.size();i++ )
            {
                Word outfile=array.get(i);
                output.write(outfile.getWord_target()+outfile.getWord_explain()+"\n");
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
                add_up.add(array.get(i).getWord_target());
            }
            else
                continue;
        }
        return add_up;

    }
}