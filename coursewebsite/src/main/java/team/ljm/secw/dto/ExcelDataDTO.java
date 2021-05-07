package team.ljm.secw.dto;

public class ExcelDataDTO {

    private Object data;
    private Integer row;
    private Integer col;
    private String formula;

    public ExcelDataDTO(Object data, Integer row, Integer col) {
        this.data = data;
        this.row = row;
        this.col = col;
    }

    public ExcelDataDTO(Integer row, Integer col, String formula) {
        this.row = row;
        this.col = col;
        this.formula = formula;
    }

    @Override
    public String toString() {
        return "ExcelDataDTO{" +
                "data=" + data +
                ", row=" + row +
                ", col=" + col +
                ", formula='" + formula + '\'' +
                '}';
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }
}
