package Main;
import java.util.ArrayList;
import java.util.List;

public class Dictionary  {

    protected static ArrayList<Word> array = new ArrayList<>();
    protected static List<String> listWordTarget = new ArrayList<>();
    public static void modifyword(int i,Word modify)
    {
        array.set(i,modify);
    }
}
