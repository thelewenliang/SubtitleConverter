/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitleconverter;

/**
 *
 * @author Staff
 */
public class SRTLine {

    String text;
    String timeStart;
    String timeEnd;

    public SRTLine(String text, String timeStart, String timeEnd) {
        this.text = text;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getFormattedLine() {
        return timeStart + " --> " + timeEnd + "\n" + text + "\n";
    }
    
    @Override
    public String toString() {
        return getFormattedLine();
    }
}
