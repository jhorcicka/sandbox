package nl.hi.kuba.jemmy;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

public class WaitWindowSample implements Scenario {
    public int runIt(Object param) {
        try {
            new ClassReference("org.netbeans.jemmy.explorer.GUIBrowser").startApplication();
            JFrameOperator mainFrame = new JFrameOperator("GUI Browser");
            new JButtonOperator(mainFrame, "Reload In").push();
            new JButtonOperator(mainFrame, "Dump").push();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
            return (1);
        }

        return (0);
    }

    public static void main(String[] argv) {
        String[] params = {"nl.hi.kuba.jemmy.WaitWindowSample"};
        org.netbeans.jemmy.Test.main(params);
    }
}

