package cz.hk.gmc.panels;


import java.io.PrintWriter;
import java.util.Properties;

import com.izforge.izpack.api.adaptator.IXMLElement;
import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.data.Panel;
import com.izforge.izpack.api.factory.ObjectFactory;
import com.izforge.izpack.api.handler.AbstractUIHandler;
import com.izforge.izpack.api.resource.Resources;
import com.izforge.izpack.installer.console.ConsolePanel;
import com.izforge.izpack.installer.panel.AbstractPanelView;
import com.izforge.izpack.installer.panel.PanelView;
import com.izforge.izpack.panels.licence.AbstractLicenceConsolePanel;
import com.izforge.izpack.util.Console;

public class MyConsolePanel extends AbstractLicenceConsolePanel {

    private String licenceText = "license text...";

    public MyConsolePanel(Resources resources, PanelView<ConsolePanel> panel) {
        super(panel, resources);
    }

    @Override
    public boolean run(InstallData installData, Console console) {
        return super.run(installData, console);
    }

    @Override
    protected String getText() {
        return licenceText;
    }
}

/*
public class MyConsolePanel extends AbstractPanelView<ConsolePanel> implements ConsolePanel {
    public MyConsolePanel(final Panel panel, final Class<ConsolePanel> viewClass,
            final ObjectFactory factory, final InstallData installData) {
        super(panel, viewClass, factory, installData);
    }

    @Override
    protected AbstractUIHandler getHandler() {
        return null;
    }

    @Override
    public boolean generateProperties(final InstallData installData, final PrintWriter printWriter) {
        return false;
    }

    @Override
    public boolean run(final InstallData installData, final Properties properties) {
        return true;
    }

    @Override
    public boolean run(final InstallData installData, final Console console) {
        return true;
    }

    @Override
    public void createInstallationRecord(final IXMLElement ixmlElement) {
    }

    @Override
    public boolean handlePanelValidationResult(final boolean b) {
        return true;
    }
}
*/
