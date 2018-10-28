package GoogleTranslate;

import com.darkprograms.speech.translator.GoogleTranslate;

import java.io.IOException;

public class GoogleTTS_Translate {
    public static String google_Tranlate(String target)//Hàm in ra từ dịch nghĩa qua api google translate
    {
        String target_translated="";
        try {
            target_translated= GoogleTranslate.translate("vi",target);

        }catch (IOException e)
        {
            System.out.println("Try Again");
        }
        return target_translated;
    }
}
