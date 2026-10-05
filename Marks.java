public class Marks {
    private String StudentId;
    private String TeacherId;
    private String CourseId;
    private double ObtainedMarks;

    Marks(String StudentId, String TeacherId, String CourseId, double ObtainedMarks) {
    this.StudentId = StudentId;
    this.TeacherId = TeacherId;
    this.CourseId = CourseId;
    this.ObtainedMarks = ObtainedMarks;
}

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String TeacherId) {
        this.TeacherId = TeacherId;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String CourseId) {
        this.CourseId = CourseId;
    }

    public double getObtainedMarks() {
        return ObtainedMarks;
    }

    public void setObtainedMarks(double ObtainedMarks) {
        this.ObtainedMarks = ObtainedMarks;
    }
}
