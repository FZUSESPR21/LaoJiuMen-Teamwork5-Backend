package team.ljm.secw.dto;

import org.springframework.web.multipart.MultipartFile;

public class StudentExcelDTO {

    private int clazzId;
    private MultipartFile file;

    @Override
    public String toString() {
        return "StudentExcelDTO{" +
                "clazzId=" + clazzId +
                ", file=" + file +
                '}';
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
