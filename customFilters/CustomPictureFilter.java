/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author valeri
 */
public class CustomPictureFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getAbsolutePath().endsWith(".png") || file.getAbsolutePath().endsWith(".jpeg")|| file.getAbsolutePath().endsWith(".jpg");
    }

    @Override
    public String getDescription() {
        return "Pictures (*.png, *.jpeg, *.jpg)";
    }
    
}
