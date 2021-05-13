package team.ljm.secw.dto;

public class ClazzDTO {

    private int id;
    private String clazzName;

    @Override
    public String toString() {
        return "ClazzDTO{" +
                "id=" + id +
                ", clazzName='" + clazzName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }
}
