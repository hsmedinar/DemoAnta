package tawa.stbig.com.demotawa.helper;

import android.os.Environment;

import java.io.File;

public class Files {

    private String IMAGEN_SERVICES;
    private static final String SERVICES_IMAGEN_FOLDER = "DemoThumts";
    private File file;

    public Files() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        file = new File(filepath, SERVICES_IMAGEN_FOLDER);

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void setNameFiles(String fileServices) {
        IMAGEN_SERVICES = fileServices;
    }


    public String getService() {
        return IMAGEN_SERVICES;
    }


    public String getPathService() {
        return (file.getAbsolutePath() + "/" + IMAGEN_SERVICES);
    }

}
