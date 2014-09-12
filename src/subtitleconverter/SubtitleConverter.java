/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitleconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Staff
 */
public class SubtitleConverter {
    
    final Pattern SSAPattern = Pattern.compile("Dialogue: Marked=0,(.+?),(.+?),Default,,.+?,,(.+?)£");
    String SSAInput;
    ArrayList<SRTLine> lineList = new ArrayList<>();
    String SRTOutput;
    
    public SubtitleConverter(File file) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        while(br.ready()) {
            SSAInput += br.readLine() + "£";
        }
        Matcher SSAMatcher = SSAPattern.matcher(SSAInput);
        String ssaTimeStart;
        String ssaTimeEnd;
        String ssaText;
        while(SSAMatcher.find()) {
            ssaTimeStart = changeTimeFormat(SSAMatcher.group(1));
            ssaTimeEnd = changeTimeFormat(SSAMatcher.group(2));
            ssaText = SSAMatcher.group(3);
            lineList.add(new SRTLine(ssaText.replace("\\N","\n").replace("\\n", "\n"),ssaTimeStart,ssaTimeEnd));
        }
        int count = 1;
        
        for(SRTLine x : lineList) {
            SRTOutput += count + "\n" + x + "\n";
            count++;
        }
        
        PrintWriter pw = new PrintWriter("Test.srt");
        pw.println(SRTOutput);
        pw.flush();
    }
    
    public static String changeTimeFormat(String text) {
        return "0" + text.replace('.', ',') + "0";
    }
    
    public static void main(String[] args) throws IOException {
        new SubtitleConverter(new File("Test.ssa"));
    }

    public void go() {

    }
}
