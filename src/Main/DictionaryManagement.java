package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public  class DictionaryManagement extends Dictionary {

    public static List<String> add_up =new ArrayList<>();//mảng list dùng riêng cho chức nức thêm từ
    public static List<String> removeout=new ArrayList<>();//mảng list dùng riêng cho chức năng xóa từ
    public static  String dictionaryLookup(String wordToLookup){//hàm chức năng tìm kiếm từ cụ thể/sử dụng cho hàm wordlookup
        for(int i=0;i<array.size();i++) {

            if (array.get(i).getWord_target().toLowerCase().equals(wordToLookup.toLowerCase())) {
                return array.get(i).getWord_explain();
            }
        }

        return "";
    }
    public static List<String> addmoreword(String addword_target,String addword_explain)//hàm thêm từ trả lại list bao gồm cả từ mới
    {
        Word addnewword=new Word(addword_target,addword_explain);
        array.add(addnewword);
        add_up.add(addnewword.getWord_target());
        return add_up;
    }
    public static List<String> removeWordFromDitionary(String id)//hàm xóa từ trả lại list tất cả các từ trừ từ đã xóa
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
            //Collections.sort(removeout);

        }
        return removeout;
    }
    public static void InsertFromFile() throws IOException {//hàm đọc file Database
        //FileReader file=new FileReader(S);
        Scanner sc = null;
        try {

            sc = new Scanner(new File("src/Database/Database.txt"));
            while (sc.hasNext()) {
                String word = sc.nextLine();
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

    public static void dictionaryExportToFile()//hàm viết vào file Database//phục vụ chức năng thêm bớt tù
    {
        PrintWriter writer=null;
        try{
            FileWriter write=new FileWriter("src/Database/Database.txt");
            writer=new PrintWriter(write);
            for(int i=0;i<array.size();i++ )
            {
                Word outfile=array.get(i);
                writer.println(outfile.getWord_target());
                writer.println(outfile.getWord_explain());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> dictionarySearcher(String wordSearch) {//hàm tra từ và in ra danh sách những từ được đánh vào

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
        Collections.sort(add_up);
        return add_up;

    }
    public static String modified(String change_target,String change_explain)//hàm chỉnh sửa nghĩa từ//
    {
        for(int i=0;i<array.size();i++)
        {
            if(array.get(i).getWord_target().toLowerCase().equals(change_target.toLowerCase()))
            {
                Dictionary.modifyword(i,new Word(change_target,change_explain));
            }
        }
        dictionaryExportToFile();
        return change_explain;
    }
}