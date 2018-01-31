package in.project.com.graph;

public class Model {

    private String edgeString;
    private boolean checked;
    private int from;
    private int to;

    public Model(String edgeString, int from, int to, boolean checked) {
        this.edgeString = edgeString;
        this.checked = checked;
        this.from = from;
        this.to = to;
    }

    public String getEdgeString() {
        return edgeString;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}