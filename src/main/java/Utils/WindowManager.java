package Utils;

import javax.swing.*;

public class WindowManager {
    public static String getPath(){
        String path = "";
        JFrame frame = new JFrame();
        int result;
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        frame.setAlwaysOnTop(true);

        fileChooser.setDialogTitle("Select the path to save the data");

        result = fileChooser.showOpenDialog(frame);

        if(result==JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        } else{
            System.out.println("Process has been canceled, root project directory will be used.");
        }

        return path;
    }




}
