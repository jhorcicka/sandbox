package cz.hk.gmc.jrecord;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import net.sf.JRecord.Details.AbstractLine;
import net.sf.JRecord.External.CopybookLoader;
import net.sf.JRecord.IO.AbstractLineReader;
import net.sf.JRecord.JRecordInterface1;
import net.sf.JRecord.Numeric.ICopybookDialects;
import net.sf.JRecord.def.IO.builders.ICobolIOBuilder;

/**
 * http://jrecord.sourceforge.net/
 */
public class CobolFile {
    public static void main(String[] args) {
        try {
            CobolFile cobolFile = new CobolFile();
            cobolFile.simpleRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void simpleRead() throws Exception {
        URL url = getClass().getResource("/CobolCopybook.cbl");
        URI uri = url.toURI();
        String cobolBookPath = new File(uri).getAbsolutePath();
        ICobolIOBuilder ioBuilder = JRecordInterface1.COBOL
                .newIOBuilder(cobolBookPath)
                .setSplitCopybook(CopybookLoader.SPLIT_01_LEVEL)
                .setDialect(ICopybookDialects.FMT_FUJITSU);
        InputStream input = getClass().getResourceAsStream("/inputData.dat");
        AbstractLineReader reader = ioBuilder.newReader(input);
        AbstractLine line;

        while ((line = reader.read()) != null) {
            System.out.println("RBI-TEXT-1=" + line.getFieldValue("RBI-TEXT-1"));
            System.out.println("<line>" + line.getFullLine() + "</line>\n");
        }

        reader.close();
    }
}
