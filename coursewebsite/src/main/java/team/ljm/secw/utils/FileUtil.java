package team.ljm.secw.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtil {

    public FileUtil() {
    }
    public static String createFileTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        return String.valueOf(time);
    }
    public static void writeFileToUrl(MultipartFile file, String fileUrl) throws IOException {
        File file1 = new File(fileUrl);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file1);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
    }
    public static void main(String[] args) {
        System.out.println(createFileTimestamp());
    }

}
