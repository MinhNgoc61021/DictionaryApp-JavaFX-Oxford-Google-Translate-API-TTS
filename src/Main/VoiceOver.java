package Main;
import VoiceModule.TextToSpeech;
public class VoiceOver {
    public static void TextToSpeech ( String word){
        try
        {
            TextToSpeech TTS=new TextToSpeech();
            TTS.setVoice("dfki-poppy-hsmm");
            TTS.speak(word,2.0f,false,false);
        }catch (NullPointerException e)
        {
            System.out.println("Type Smt");
        }

    }
}
