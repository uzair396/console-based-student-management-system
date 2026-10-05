public class Course {
    private String id;
    private String name;
    private int creditHours;

    public Course(String id, String name, int creditHours) {
        this.id = id;
        this.name = name;
        this.creditHours = creditHours;
    }

    public String getCourseId() { return id; }
    public void setCourseId(String id) { this.id = id; }

    public String getCourseName() { return name; }
    public void setCourseName(String name) { this.name = name; }

    public int getCourseCreditHours() { return creditHours; }
    public void setCourseCreditHours(int creditHours) { this.creditHours = creditHours; }
}
