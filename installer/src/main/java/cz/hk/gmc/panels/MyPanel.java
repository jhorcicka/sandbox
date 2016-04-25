package cz.hk.gmc.panels;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import com.izforge.izpack.api.data.Panel;
import com.izforge.izpack.api.resource.Resources;
import com.izforge.izpack.gui.log.Log;
import com.izforge.izpack.installer.data.GUIInstallData;
import com.izforge.izpack.installer.gui.InstallerFrame;
import com.izforge.izpack.installer.gui.IzPanel;

public class MyPanel extends IzPanel {
    public MyPanel(Panel panel, InstallerFrame parent, GUIInstallData installData, Resources resources, Log log) {
        super(panel, parent, installData, resources);
        initialize();
    }

    private void initialize() {
        setLayout(new GridBagLayout());
        int yPosition = 0;
        add(new JLabel("Label one..."), makeConstraints(++yPosition));
        add(new JLabel("Label two..."), makeConstraints(++yPosition));
    }

    private GridBagConstraints makeConstraints(int yPosition) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = yPosition;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;

        return constraints;
    }

    @Override
    public void panelActivate() {
        super.panelActivate();
    }
}
