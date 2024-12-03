package hyeonseo.ai.a2024project;

public class DrugItem {
    private String name;
    private String description;
    private int count;

    public DrugItem(String name, String description) {
        this.name = name;
        this.description = description;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
