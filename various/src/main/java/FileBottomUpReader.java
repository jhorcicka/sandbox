import java.io.*;
import java.nio.channels.*;
import java.nio.*;

/**
 * @author j.horcicka (GMC)
 * @since 1.6.15
 */
public class FileBottomUpReader {
    private static final String path = "/home/kuba/tmp/file.txt";

    public static void main(String[] args) {
        try {
            //method1();
            method2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void method2() throws IOException {
        RandomAccessFile f = new RandomAccessFile(path,"rw");
        FileChannel fc = f.getChannel();

        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, f.length());
        int len = (int)f.length();

        for (int i = 0, j = len - 1; i < j; i++, j--)
        {
            byte b = mbb.get(i);
            mbb.put(i, mbb.get(j));
            mbb.put(j, b);
        }

        System.out.println("BUFFER=" + mbb.toString());

        fc.close();
    }

    private static void method1() throws  IOException {
        //method 1
        File f = new File(path);
        FileReader fr = new FileReader(f);

        char[] c = new char[(int) f.length()];
        char[] cnew = new char[(int) f.length()];
        StringBuffer sbuf = new StringBuffer();
        fr.read(c, 0, (int) f.length());
        int len = (int) f.length();

        for (int i = 0, j = len - 1; i < len; i++, j--) {
            cnew[i] = c[j];
            sbuf.append(cnew[i]);
        }

        System.out.println("BUFFER=" + sbuf.toString());
        fr.close();
    }
}
