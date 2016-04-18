package cz.hk.gmc.presentation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

public class Main {
    public static void main(String args[]) throws IOException{
        XMLSlideShow ppt = new XMLSlideShow();
        ppt.createSlide();
        ppt.createSlide();

        File file =new File("example.pptx");
        FileOutputStream out = new FileOutputStream(file);

        ppt.write(out);
        System.out.println("Presentation created successfully");
        out.close();
    }
}
