package nl.hi.kuba.jemmy;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.WindowOperator;

public class DataCleaner implements Scenario {
    private static final String APP = "org.datacleaner.Main";

    public int runIt(Object param) {
        try {
            new ClassReference(APP).startApplication();
            final JFrameOperator mainFrame = new JFrameOperator("Welcome | DataCleaner");
            final JDialogOperator dialog = new JDialogOperator(mainFrame, "Sign in to DataCloud | DataCleaner");
            new JButtonOperator(dialog, "Close").push();
            new JButtonOperator(mainFrame, "Build new job").push();
            new WindowOperator().close();

            Thread.sleep(60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return (1);
        }

        return (0);
    }

    public static void main(String[] argv) {
        String[] params = {"nl.hi.kuba.jemmy.DataCleaner"};
        org.netbeans.jemmy.Test.main(params);
    }
}

