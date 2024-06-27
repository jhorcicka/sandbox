package nl.hi.kuba.docker;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileAlterationDemo {
    public static void main(String[] args) throws Exception {
        final FileAlterationListener listener = new FileAlterationListener() {
            @Override
            public void onStart(final FileAlterationObserver fileAlterationObserver) {
            }

            @Override
            public void onDirectoryCreate(final File file) {
            }

            @Override
            public void onDirectoryChange(final File file) {
            }

            @Override
            public void onDirectoryDelete(final File file) {
            }

            @Override
            public void onFileCreate(final File file) {
                System.err.println("MYTODO: onFileCreate: " + file.getAbsolutePath());
            }

            @Override
            public void onFileChange(final File file) {
                System.err.println("MYTODO: onFileChange: " + file.getAbsolutePath());
            }

            @Override
            public void onFileDelete(final File file) {
            }

            @Override
            public void onStop(final FileAlterationObserver fileAlterationObserver) {
            }
        };

        final File directory = new File("/tmp/hot_folder");
        final FileAlterationObserver observer = new FileAlterationObserver(directory);
        observer.addListener(listener);

        final int seconds = 2;
        final FileAlterationMonitor monitor = new FileAlterationMonitor(seconds * 1000);
        monitor.addObserver(observer);
        monitor.start();
    }
}
