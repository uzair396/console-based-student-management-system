public class RegisterdCourse {
    private String studentId;
    private String courseId;
    private String assignedTeacherId;

    // Constructor
    public RegisterdCourse(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public RegisterdCourse(String studentId, String courseId,String teacherIs) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.assignedTeacherId=teacherIs;
    }

    // Getter for studentId
    public String getStudentId() {
        return studentId;
    }

    // Setter for studentId
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // Getter for courseId
    public String getCourseId() {
        return courseId;
    }

    // Setter for courseId
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

   public void setTeacherId(String teacherId) {
    this.assignedTeacherId = (teacherId == null) ? "" : teacherId;
}


    String getTeacherId()
    {
        return assignedTeacherId;
    }
}
