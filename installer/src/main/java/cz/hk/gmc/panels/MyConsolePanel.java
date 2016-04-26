package cz.hk.gmc.panels;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.factory.ObjectFactory;
import com.izforge.izpack.api.handler.Prompt;
import com.izforge.izpack.api.resource.Resources;
import com.izforge.izpack.api.rules.RulesEngine;
import com.izforge.izpack.installer.console.ConsolePanel;
import com.izforge.izpack.installer.panel.PanelView;
import com.izforge.izpack.panels.userinput.UserInputConsolePanel;
import com.izforge.izpack.util.Console;
import com.izforge.izpack.util.PlatformModelMatcher;

public class MyConsolePanel extends UserInputConsolePanel {
    private InstallData _installData;

    public MyConsolePanel(final Resources resources, final ObjectFactory factory,
            final RulesEngine rules, final PlatformModelMatcher matcher,
            final Console console, final Prompt prompt,
            final PanelView<ConsolePanel> panelView, final InstallData installData) {
        super(resources, factory, rules, matcher, console, prompt, panelView, installData);
        this._installData = installData;
    }

    @Override
    public boolean run(InstallData installData, Console console) {
        boolean parentRun = super.run(installData, console);

        System.out.println("variable=" + _installData.getVariable("license.file.path"));

        return parentRun;
    }
}
