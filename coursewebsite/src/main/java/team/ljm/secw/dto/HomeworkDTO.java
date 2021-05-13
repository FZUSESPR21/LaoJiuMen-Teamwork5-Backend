package team.ljm.secw.dto;

public class HomeworkDTO {

    private int id;
    private String title;

    @Override
    public String toString() {
        return "HomeworkDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
