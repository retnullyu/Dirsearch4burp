package burp;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @program: burp2dirsearch
 * @description: start dirsearch
 * @author: luoyu
 * @create: 2021-12-01 16:27
 **/

public class DirsearchStarter implements Runnable {
    @Override
    public void run() {
        try {
            String command = String.format("%s \"%s\" --raw \"%s\" %s",Config.getPythonName(),Config.getDirsearch(),Config.getRequstFilePath(),Config.getDirsearchOptionsCommand());
            List<String> cmds = new ArrayList();
            int osType = Util.getOSType();
            if(osType == Util.OS_WIN){
                cmds.add("cmd.exe");
                cmds.add("/c");
                cmds.add("start");
                String batFilePath = Util.makeBatFile("burp2dirsearch.bat",command);
                if(!batFilePath.equals("Fail")){
                    cmds.add(batFilePath);
                }else{
                    String eMsg = "make burp2dirsearch.bat fail!";
                    JOptionPane.showMessageDialog(null,eMsg,"burp2dirsearch++ alert",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }else if(osType == Util.OS_MAC){
                String optionCommand = Config.getDirsearchOptionsCommand();
                //将参数数中的"转译为\"
                optionCommand = optionCommand.replace("\"","\\\"");
                command = String.format("%s \\\"%s\\\" --raw \\\"%s\\\" %s",Config.getPythonName(),Config.getDirsearch(),Config.getRequstFilePath(),optionCommand);
                cmds.add("osascript");
                cmds.add("-e");
                String cmd = "tell application \"Terminal\" \n" +
                        "        activate\n" +
                        "        do script \"%s\"\n" +
                        "end tell";
                cmds.add(String.format(cmd,command));
                //BurpExtender.stdout.println(String.format(cmd,command));
            }else if(osType == Util.OS_LINUX){
                cmds.add("/bin/sh");
                cmds.add("-c");
                cmds.add("gnome-terminal");
                Util.setSysClipboardText(command);
                JOptionPane.showMessageDialog(null,"The command has been copied to the clipboard. Please paste it into Terminal for execution","burp2dirsearch++ alert",JOptionPane.OK_OPTION);
            }else{
                cmds.add("/bin/bash");
                cmds.add("-c");
                cmds.add(command);
            }

            ProcessBuilder processBuilder = new ProcessBuilder(cmds);
            Process process = processBuilder.start();
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            BufferedReader input = new BufferedReader (ir);
            String line;
            while ((line = input.readLine()) != null) {
                BurpExtender.stdout.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            BurpExtender.stderr.println("[*]" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        new Thread(new SqlmapStarter()).start();
        Properties properties = System.getProperties();
        System.out.println(properties.get("java.io.tmpdir"));
    }
}
