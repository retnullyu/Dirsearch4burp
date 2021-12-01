package burp;

public class Config {
    private static final String EXTENDER_NAME = "burp2dirsearch";
    private static final String EXTENDER_VERSION = "0.1";
    private static String PYTHON_NAME = "python3";
    private static String DIRSEARCH_PATH = "dirsearch";
    private static String REQUST_FILE_PATH = "";
    private static String DIRSEARCH_OPTIONS_COMMAND = "";
    private static String OS_TYPE;
    private static boolean IS_INJECT = false;


    public static String getExtenderName() {
        return EXTENDER_NAME;
    }

    public static String getExtenderVersion() {
        return EXTENDER_VERSION;
    }

    public static String getPythonName() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("PYTHON_NAME");
            if(val == null){
                return Config.PYTHON_NAME;
            }else{
                return val;
            }
        }catch(Exception e){
            return Config.PYTHON_NAME;
        }
    }

    public static void setPythonName(String pythonName) {
        BurpExtender.callbacks.saveExtensionSetting("PYTHON_NAME", String.valueOf(pythonName));
        Config.DIRSEARCH_PATH = pythonName;
    }

    public static String getDirsearch() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("DIRSEARCH_PATH");
            if(val == null){
                return Config.DIRSEARCH_PATH;
            }else{
                return val;
            }
        }catch(Exception e){
            return Config.DIRSEARCH_PATH;
        }
    }

    public static void setDirsearchPath(String dirsearch) {
        BurpExtender.callbacks.saveExtensionSetting("DIRSEARCH_PATH", String.valueOf(dirsearch));
        Config.DIRSEARCH_PATH = dirsearch;
    }

    public static String getRequstFilePath() {
        return REQUST_FILE_PATH;
    }

    public static void setRequstFilePath(String requstFilePath) {
        REQUST_FILE_PATH = requstFilePath;
    }

    public static String getDirsearchOptionsCommand() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("DIRSEARCH_OPTIONS_COMMAND");
            if(val == null){
                return Config.DIRSEARCH_OPTIONS_COMMAND;
            }else{
                return val;
            }
        }catch(Exception e){
            return Config.DIRSEARCH_OPTIONS_COMMAND;
        }
    }

    public static void setDirsearchOptionsCommand(String dirsearchOptionsCommand) {
        BurpExtender.callbacks.saveExtensionSetting("DIRSEARCH_OPTIONS_COMMAND", String.valueOf(dirsearchOptionsCommand));
        Config.DIRSEARCH_OPTIONS_COMMAND = dirsearchOptionsCommand;
    }

    public static String getOsType() {
        return OS_TYPE;
    }

    public static void setOsType(String osType) {
        OS_TYPE = osType;
    }

    public static boolean isIsInject() {
        return IS_INJECT;
    }

    public static void setIsInject(boolean isInject) {
        IS_INJECT = isInject;
    }
}
