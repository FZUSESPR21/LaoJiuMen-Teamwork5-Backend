package team.ljm.secw.dto;

public class ExcelHeadDTO {

    private String headText;
    private Integer firstRow;
    private Integer lastRow;
    private Integer firstCol;
    private Integer lastCol;
    private Integer width;

    public ExcelHeadDTO(String headText, Integer firstRow, Integer lastRow, Integer firstCol, Integer lastCol, Integer width) {
        this.headText = headText;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.firstCol = firstCol;
        this.lastCol = lastCol;
        this.width = width;
    }

    @Override
    public String toString() {
        return "ExcelHeadDTO{" +
                "headText='" + headText + '\'' +
                ", firstRow=" + firstRow +
                ", lastRow=" + lastRow +
                ", firstCol=" + firstCol +
                ", lastCol=" + lastCol +
                ", width=" + width +
                '}';
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getHeadText() {
        return headText;
    }

    public void setHeadText(String headText) {
        this.headText = headText;
    }

    public Integer getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
        this.firstRow = firstRow;
    }

    public Integer getLastRow() {
        return lastRow;
    }

    public void setLastRow(Integer lastRow) {
        this.lastRow = lastRow;
    }

    public Integer getFirstCol() {
        return firstCol;
    }

    public void setFirstCol(Integer firstCol) {
        this.firstCol = firstCol;
    }

    public Integer getLastCol() {
        return lastCol;
    }

    public void setLastCol(Integer lastCol) {
        this.lastCol = lastCol;
    }
}
