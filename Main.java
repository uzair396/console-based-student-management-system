import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Teacher> teachers = new ArrayList<>();
    static ArrayList<Course> offeredCourses = new ArrayList<>();
    static ArrayList<AssignedCourse> assignedCourses = new ArrayList<>();
    static ArrayList<RegisterdCourse> registerdCourses = new ArrayList<>();
    static ArrayList<Marks> obtainedMarksOfCourse = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        int menuOption = -1;

        System.out.println("******Welcome to CUI Portal******\n");

        loadAllDataFromFiles();
        coursesOffered();
        while (menuOption != 0) {
            System.out.println("-----Who are you-----");
            System.out.println("1. Department Manager");
            System.out.println("2. Teacher");
            System.out.println("3. Student");
            System.out.println("0. Exit");

            System.out.print("Enter choice : ");

            try {
                menuOption = sc.nextInt();
                sc.nextLine();

                if (menuOption < 0 || menuOption > 3) {
                    System.out.println("Invalid choice! Please enter only 0-3.\n");
                    menuOption = -1;
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter only 0-3.\n");
                sc.nextLine();
                continue;
            }

            switch (menuOption) {
                case 1:
                    int depOption = -1;
                    while (depOption != 0) {
                        System.out.println("------Computer Science Department------");
                        System.out.println("1. Add new Teacher");
                        System.out.println("2. Add new Student");
                        System.out.println("3. Edit Teacher data");
                        System.out.println("4. Edit Student data");
                        System.out.println("5. Delete Teacher");
                        System.out.println("6. Delete Student");
                        System.out.println("7. Offered Courses by Department");
                        System.out.println("8. Assign Course to Teacher");
                        System.out.println("9. Assign teacher to student in specific course");
                        System.out.println("10. All Reports");
                        System.out.println("0. Exit");

                        System.out.print("Enter choice: ");

                        try {
                            depOption = sc.nextInt();
                            sc.nextLine();

                            if (depOption < 0 || depOption > 10) {
                                System.out.println("Invalid choice! Please enter only 0-10.\n");
                                depOption = -1;
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter only 0-10.\n");
                            sc.nextLine();
                            continue;
                        }

                        switch (depOption) {
                            case 1:
                                addTeacher();
                                break;
                            case 2:
                                addStudent();
                                break;
                            case 3:
                                editTeacher();
                                break;
                            case 4:
                                editStudent();
                                break;
                            case 5:
                                deleteTeacher();
                                break;
                            case 6:
                                deleteStudent();
                                break;
                            case 7:
                                displayCourses();
                                break;
                            case 8:
                                AssignCourseToTeacher();
                                break;
                            case 9:
                                assignTeacherToStudentCourse();
                                break;
                            case 10:
                                int reportOption = -1;
                                while (reportOption != 0) {
                                    System.out.println("------Reports Menu------");
                                    System.out.println("1. All Teachers with Assigned Courses");
                                    System.out.println("2. All Students with Courses Registered");
                                    System.out.println("3. Result Card of a Student");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter choice: ");

                                    try {
                                        reportOption = sc.nextInt();
                                        sc.nextLine();

                                        if (reportOption < 0 || reportOption > 3) {
                                            System.out.println("Invalid choice! Please enter only 0-3.\n");
                                            reportOption = -1;
                                            continue;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input! Please enter only 0-3.\n");
                                        sc.nextLine();
                                        continue;
                                    }

                                    switch (reportOption) {
                                        case 1:
                                            for (Teacher t : teachers) {
                                                displayAssignedCoursesForTeacher(t);
                                            }
                                            break;
                                        case 2:
                                            for (Student s : students) {
                                                displayStudentCourseDetails(s);
                                            }
                                            break;
                                        case 3:
                                            Student s = selectStudentById();
                                            if (s != null) {
                                                generateResultCard(s);
                                            }
                                            break;
                                        case 0:
                                            System.out.println("Exiting Reports Menu...");
                                            break;
                                    }
                                }
                                break;
                            case 0:
                                System.out.println("Exiting Department Menu...");
                                break;
                        }
                    }
                    break;

                case 2:
                    int teacherOpt = -1;
                    Teacher selectedTeacher = selectTeacher();
                    if (selectedTeacher != null) {
                        System.out.println("Teacher with Id " + selectedTeacher.getId() + " logined.");
                    } else {
                        teacherOpt = 0;
                    }

                    while (teacherOpt != 0) {
                        System.out.println("------Teacher Menu------");
                        System.out.println("1. Award Marks");
                        System.out.println("2. Edit Marks");
                        System.out.println("3. View Marks");
                        System.out.println("0. Exit");

                        System.out.print("Enter Choice : ");

                        try {
                            teacherOpt = sc.nextInt();
                            sc.nextLine();

                            if (teacherOpt < 0 || teacherOpt > 3) {
                                System.out.println("Invalid choice! Please enter only 0-3.\n");
                                teacherOpt = -1;
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter only 0-3.\n");
                            sc.nextLine();
                            continue;
                        }

                        switch (teacherOpt) {
                            case 1:
                                awardMarksToStudent(selectedTeacher);
                                break;
                            case 2:
                                editMarks(selectedTeacher);
                                break;
                            case 3:
                                viewStudentMarksByTeacher(selectedTeacher);
                                break;
                            case 0:
                                System.out.println("Exiting Teacher Menu...");
                                break;
                        }
                    }

                    break;

                case 3:
                    int studentOpt = -1;

                    Student selectedStudent = selectStudentById();
                    if (selectedStudent != null) {
                        System.out.println("Student with Id " + selectedStudent.getId() + " logined.");
                    } else {
                        studentOpt = 0;
                    }

                    while (studentOpt != 0) {
                        System.out.println("------Student Menu------");
                        System.out.println("1. Register Course");
                        System.out.println("2. Drop Course");
                        System.out.println("3. View Course Marks");
                        System.out.println("4. Registered Courses");
                        System.out.println("5. Result Card");
                        System.out.println("0. Exit");

                        System.out.print("Enter Choice : ");

                        try {
                            studentOpt = sc.nextInt();
                            sc.nextLine();

                            if (studentOpt < 0 || studentOpt > 5) {
                                System.out.println("Invalid choice! Please enter only 0-5.\n");
                                studentOpt = -1;
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter only 0-5.\n");
                            sc.nextLine();
                            continue;
                        }

                        switch (studentOpt) {
                            case 1:
                                registerCourseByStudent(selectedStudent);
                                break;
                            case 2:
                                dropCourseForStudent(selectedStudent);
                                break;
                            case 4:
                                displayStudentCourseDetails(selectedStudent);
                                break;
                            case 3:
                                viewMarksForStudent(selectedStudent);
                                break;
                            case 5:
                                calculateCGPA(selectedStudent);
                                break;

                            case 0:
                                System.out.println("Exiting Student Menu...");
                                break;
                        }
                    }

                    break;

                case 0:
                    System.out.println("Exiting Department Menu...");
                    break;

            }
        }
    }

    public static void assignTeacherToStudentCourse() {
        if (registerdCourses.isEmpty()) {
            System.out.println("No registered courses found.");
            return;
        }

        viewAllStudents();
        String studentId = getStudentId();

        List<String> studentCourses = getStudentRegisteredCourses(studentId);
        if (studentCourses.isEmpty()) {
            System.out.println("This student has no registered courses.");
            return;
        }

        String courseId = getCourseIdForAssignment(studentCourses);
        if (courseId == null)
            return;

        List<AssignedCourse> available = getAvailableTeachersForCourse(courseId);
        if (available.isEmpty()) {
            System.out.println("No teachers assigned to this course.");
            return;
        }

        displayAvailableTeachers(available);
        String teacherId = getTeacherId();

        if (isTeacherAlreadyAssigned(studentId, courseId))
            return;

        assignTeacher(studentId, courseId, teacherId);
        System.out.println("Teacher assigned successfully for student-course.");
        saveAllDataToFiles();
    }

    private static String getStudentId() {
        System.out.print("Enter Student ID: ");
        return sc.nextLine();
    }

    private static List<String> getStudentRegisteredCourses(String studentId) {
        System.out.println("Registered Courses:");
        List<String> studentCourses = new ArrayList<>();
        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(studentId)) {
                for (Course c : offeredCourses) {
                    if (c.getCourseId().equals(rc.getCourseId())) {
                        System.out.println("Course ID: " + c.getCourseId() + ", Name: " + c.getCourseName());
                        studentCourses.add(c.getCourseId());
                    }
                }
            }
        }
        return studentCourses;
    }

    private static String getCourseIdForAssignment(List<String> studentCourses) {
        System.out.print("Enter Course ID to assign teacher: ");
        String courseId = sc.nextLine();
        if (!studentCourses.contains(courseId)) {
            System.out.println("This student is not registered for the selected course.");
            return null;
        }
        return courseId;
    }

    private static List<AssignedCourse> getAvailableTeachersForCourse(String courseId) {
        List<AssignedCourse> available = new ArrayList<>();
        for (AssignedCourse ac : assignedCourses) {
            if (ac.getCourseId().equals(courseId)) {
                available.add(ac);
            }
        }
        return available;
    }

    private static void displayAvailableTeachers(List<AssignedCourse> available) {
        System.out.println("Available Teachers for this Course:");
        for (AssignedCourse ac : available) {
            for (Teacher t : teachers) {
                if (t.getId().equals(ac.getTeacherId())) {
                    System.out.println("Teacher ID: " + t.getId() + ", Name: " + t.getName());
                }
            }
        }
    }

    private static String getTeacherId() {
        System.out.print("Enter Teacher ID to assign for this student-course: ");
        return sc.nextLine();
    }

    private static boolean isTeacherAlreadyAssigned(String studentId, String courseId) {
        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(studentId) && rc.getCourseId().equals(courseId)) {
                String assignedTeacherId = rc.getTeacherId();
                if (assignedTeacherId != null && !assignedTeacherId.isEmpty()) {
                    System.out.println("Teacher already assigned for this student-course.");
                    return true;
                }
            }
        }
        return false;
    }

    private static void assignTeacher(String studentId, String courseId, String teacherId) {
        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(studentId) && rc.getCourseId().equals(courseId)) {
                rc.setTeacherId(teacherId);
                break;
            }
        }
        obtainedMarksOfCourse.add(new Marks(studentId, teacherId, courseId, 0.0));
    }

    public static void calculateCGPA(Student selectedStudent) {
        if (selectedStudent == null) {
            System.out.println("No student selected.");
            return;
        }

        String studentId = selectedStudent.getId();
        double totalWeightedPoints = 0;
        double totalCreditHours = 0;
        boolean allCoursesMarked = true;

        System.out.println("\nCourse-wise GPA for " + selectedStudent.getName() + " (" + studentId + "):");

        displayGPAForStudent(studentId);

        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(studentId)) {
                Course course = findCourseById(rc.getCourseId());
                if (course != null) {
                    Marks marks = findMarks(studentId, course.getCourseId());
                    double creditHours = course.getCourseCreditHours();

                    if (marks != null && marks.getObtainedMarks() != 0.0) {
                        double gpa = convertMarksToGPA(marks.getObtainedMarks());
                        totalWeightedPoints += gpa * creditHours;
                        totalCreditHours += creditHours;
                    } else {
                        allCoursesMarked = false;
                    }
                }
            }
        }

        if (allCoursesMarked && totalCreditHours > 0) {
            double cgpa = totalWeightedPoints / totalCreditHours;
            System.out.printf("Final CGPA: %.2f\n\n", cgpa);
        } else {
            System.out.println("All courses must be marked to calculate CGPA.");
        }
    }

    private static void displayGPAForStudent(String studentId) {
        System.out.printf("+------------+----------------------------------------------+--------+-------+------+\n");
        System.out.printf("| Course ID  | Course Title                                 | Credit | Marks | GPA  |\n");
        System.out.printf("+------------+----------------------------------------------+--------+-------+------+\n");

        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(studentId)) {
                Course course = findCourseById(rc.getCourseId());
                if (course != null) {
                    double creditHours = course.getCourseCreditHours();
                    Marks marks = findMarks(studentId, course.getCourseId());

                    if (marks != null && marks.getObtainedMarks() != 0.0) {
                        double gpa = convertMarksToGPA(marks.getObtainedMarks());
                        System.out.printf("| %-10s | %-44s |  %-5.0f |  %-4.0f | %.2f |\n",
                                course.getCourseId(),
                                course.getCourseName(),
                                creditHours,
                                marks.getObtainedMarks(),
                                gpa);
                    } else if (marks != null) {
                        System.out.printf("| %-10s | %-44s |  %-5.0f |  %-16s |\n",
                                course.getCourseId(),
                                course.getCourseName(),
                                creditHours,
                                "Not Awarded");
                    } else {
                        System.out.printf("| %-10s | %-44s |  %-5.0f |  %-11s |\n",
                                course.getCourseId(),
                                course.getCourseName(),
                                creditHours,
                                "No Record");
                    }
                }
            }
        }

        System.out.printf("+------------+----------------------------------------------+--------+-------+------+\n");
    }

    private static Course findCourseById(String courseId) {
        for (Course c : offeredCourses) {
            if (c.getCourseId().equals(courseId)) {
                return c;
            }
        }
        return null;
    }

    private static Marks findMarks(String studentId, String courseId) {
        for (Marks m : obtainedMarksOfCourse) {
            if (m.getStudentId().equals(studentId) && m.getCourseId().equals(courseId)) {
                return m;
            }
        }
        return null;
    }

    public static double convertMarksToGPA(double marks) {
        if (marks >= 90)
            return 4.0;
        if (marks >= 80)
            return 3.7;
        if (marks >= 70)
            return 3.0;
        if (marks >= 60)
            return 2.0;
        if (marks >= 50)
            return 1.0;
        return 0.0;
    }

    public static void addTeacher() {
        System.out.print("Enter Teacher ID: ");
        String id = sc.nextLine();

        // Check for duplicate ID
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                System.out.println("Teacher with ID " + id + " already exists.\n");
                return;
            }
        }

        System.out.print("Enter Teacher Name: ");
        String name = sc.nextLine();

        Teacher newTeacher = new Teacher(id, name);
        teachers.add(newTeacher);
        System.out.println("Teacher added successfully.\n");

        // saveTeachersToFile();

        saveAllDataToFiles();
    }

    public static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        // Check for duplicate ID
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Student with ID " + id + " already exists.\n");
                return;
            }
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        Student newStudent = new Student(id, name);
        students.add(newStudent);
        System.out.println("Student added successfully.\n");

        // saveStudentsToFile();
        saveAllDataToFiles();
    }

    public static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }

        System.out.println("------ List of All Students ------");
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
        }
        System.out.println("----------------------------------\n");
    }

    public static void viewAllTeachers() {
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.\n");
            return;
        }

        System.out.println("------ List of All Teachers ------");
        for (Teacher teacher : teachers) {
            System.out.println("ID: " + teacher.getId() + ", Name: " + teacher.getName());
        }
        System.out.println("----------------------------------\n");
    }

    public static void deleteTeacher() {
        if (teachers.isEmpty()) {
            System.out.println("No teachers available to delete.\n");
            return;
        }

        System.out.print("Enter Teacher ID to delete: ");
        String id = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                teachers.remove(i);
                found = true;
                System.out.println("Teacher with ID " + id + " deleted successfully.\n");
                break;
            }
        }

        if (!found) {
            System.out.println("Teacher with ID " + id + " not found.\n");
        }
        // saveStudentsToFile();
        saveAllDataToFiles();
    }

    public static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No students available to delete.\n");
            return;
        }

        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                found = true;
                System.out.println("Student with ID " + id + " deleted successfully.\n");
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.\n");
        }
        // saveStudentsToFile();
        saveAllDataToFiles();
    }

    public static void editTeacher() {
        viewAllTeachers();

        System.out.print("Enter Teacher ID to edit: ");
        String id = sc.nextLine();

        Teacher teacher = findTeacherById(id);

        if (teacher == null) {
            System.out.println("Teacher not found.\n");
            return;
        }

        System.out.println("Current Name: " + teacher.getName());
        System.out.println("1. Edit ID");
        System.out.println("2. Edit Name");
        System.out.println("3. Edit Both");

        int choice = getEditChoice();
        if (choice == -1)
            return;

        String oldId = teacher.getId();

        if (choice == 1 || choice == 3) {
            System.out.print("Enter new ID: ");
            String newId = sc.nextLine();
            teacher.setId(newId);
            updateTeacherIdReferences(oldId, newId);
        }

        if (choice == 2 || choice == 3) {
            System.out.print("Enter new Name: ");
            String newName = sc.nextLine();
            teacher.setName(newName);
        }

        System.out.println("Teacher details updated successfully.\n");
        saveAllDataToFiles();
    }

    private static Teacher findTeacherById(String id) {
        for (Teacher t : teachers) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    private static int getEditChoice() {
        try {
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            sc.nextLine(); // clear input buffer
            return -1;
        }
    }

    private static void updateTeacherIdReferences(String oldId, String newId) {
        for (AssignedCourse ac : assignedCourses) {
            if (ac.getTeacherId().equals(oldId)) {
                ac.setTeacherId(newId);
            }
        }

        for (Marks mark : obtainedMarksOfCourse) {
            if (mark.getTeacherId().equals(oldId)) {
                mark.setTeacherId(newId);
            }
        }

        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getTeacherId().equals(oldId)) {
                rc.setTeacherId(newId);
            }
        }
    }

    public static boolean isTeacherIdExists(String id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void editStudent() {
        viewAllStudents();

        System.out.print("Enter Student ID to edit: ");
        String id = sc.nextLine();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.\n");
            return;
        }

        System.out.println("Current Name: " + student.getName());
        System.out.println("1. Edit ID");
        System.out.println("2. Edit Name");
        System.out.println("3. Edit Both");

        int choice = getEditChoice();
        if (choice == -1)
            return;

        String oldId = student.getId();

        if (choice == 1 || choice == 3) {
            System.out.print("Enter new ID: ");
            String newId = sc.nextLine();

            if (isStudentIdExists(newId)) {
                System.out.println("A student with this ID already exists.");
                return;
            }

            student.setId(newId);
            updateStudentIdReferences(oldId, newId);
        }

        if (choice == 2 || choice == 3) {
            System.out.print("Enter new Name: ");
            String newName = sc.nextLine();
            student.setName(newName);
        }

        System.out.println("Student details updated successfully.\n");
        saveAllDataToFiles();
    }

    private static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private static boolean isStudentIdExists(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private static void updateStudentIdReferences(String oldId, String newId) {
        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(oldId)) {
                rc.setStudentId(newId);
            }
        }

        for (Marks mark : obtainedMarksOfCourse) {
            if (mark.getStudentId().equals(oldId)) {
                mark.setStudentId(newId);
            }
        }
    }

    public static void coursesOffered() {
        offeredCourses.clear();
        offeredCourses.add(new Course("CS101", "Programming Fundamentals", 4));
        offeredCourses.add(new Course("CS102", "Object Oriented Programming", 4));
        offeredCourses.add(new Course("ICT101", "Application for Information and Communication Technology", 3));
        offeredCourses.add(new Course("MTH103", "Discrete Structures", 3));
        offeredCourses.add(new Course("CS201", "Data Structures and Algorithms", 4));
        offeredCourses.add(new Course("SE201", "Software Engineering", 3));
        offeredCourses.add(new Course("CS203", "Database Systems", 4));
        offeredCourses.add(new Course("CS204", "Operating Systems", 3));
        offeredCourses.add(new Course("CS205", "Web Development", 3));
        offeredCourses.add(new Course("CS206", "Computer Architecture", 3));
    }

    public static void displayCourses() {
        System.out.println("-----All Courses-----\n");
        for (Course c : offeredCourses) {
            System.out.print("ID :" + c.getCourseId());
            System.out.print(" , Name : " + c.getCourseName());
            System.out.println(" , CreditHours : " + c.getCourseCreditHours());
            System.out.println("---------------------------");
        }
    }

    public static void AssignCourseToTeacher() {
        viewAllTeachers();

        System.out.print("Enter Teacher ID to assign course: ");
        String teacherId = sc.nextLine();

        Teacher selectedTeacher = findTeacherById(teacherId);
        if (selectedTeacher == null) {
            System.out.println("Teacher not found.\n");
            return;
        }

        displayCourses();

        System.out.print("Enter Course ID to assign to this teacher: ");
        String courseId = sc.nextLine();

        Course selectedCourse = findCourseById(courseId);
        if (selectedCourse == null) {
            System.out.println("Course not found.\n");
            return;
        }

        if (isCourseAlreadyAssignedToTeacher(courseId, teacherId)) {
            System.out.println("This course is already assigned to this teacher.\n");
            return;
        }

        assignedCourses.add(new AssignedCourse(courseId, teacherId));
        System.out.println("Course assigned successfully to " + selectedTeacher.getName() + ".\n");

        saveAllDataToFiles();
    }

    private static boolean isCourseAlreadyAssignedToTeacher(String courseId, String teacherId) {
        for (AssignedCourse ac : assignedCourses) {
            if (ac.getCourseId().equals(courseId) && ac.getTeacherId().equals(teacherId)) {
                return true;
            }
        }
        return false;
    }

    public static Student selectStudentById() {
        viewAllStudents();
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Student found: " + student.getName());
                return student;
            }
        }

        System.out.println("Student with ID " + id + " not found.");
        return null;
    }

    public static void registerCourseByStudent(Student selectedStudent) {

        displayCourses();

        System.out.print("Enter Course ID to register: ");
        String courseId = sc.nextLine();

        Course selectedCourse = null;
        for (Course c : offeredCourses) {
            if (c.getCourseId().equals(courseId)) {
                selectedCourse = c;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Course not found");
            return;
        }

        // Check if already registered
        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(selectedStudent.getId()) && rc.getCourseId().equals(courseId)) {
                System.out.println("Student already registered in this course.\n");
                return;
            }
        }

        RegisterdCourse newRegistration = new RegisterdCourse(selectedStudent.getId(), courseId);
        registerdCourses.add(newRegistration);
        System.out.println("Course registered successfully for " + selectedStudent.getName() + ".\n");
        saveAllDataToFiles();
        // saveRegisterdCoursesToFile();
    }

    public static Teacher selectTeacher() {
        viewAllTeachers();
        System.out.print("Enter Teacher ID: ");
        String id = sc.nextLine();

        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                return teacher;
            }
        }

        System.out.println("Teacher not found.");
        return null;
    }

    public static void viewRegisteredCoursesForStudent(Student selectedStudent) {
        String studentId = selectedStudent.getId();
        boolean found = false;

        System.out
                .println("\nRegistered Courses for Student: " + selectedStudent.getName() + " (ID: " + studentId + ")");

        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(studentId)) {
                found = true;

                // Get course name from offeredCourses
                String courseName = "Unknown";
                for (Course c : offeredCourses) {
                    if (c.getCourseId().equals(rc.getCourseId())) {
                        courseName = c.getCourseName();
                        break;
                    }
                }

                System.out.println("Course ID: " + rc.getCourseId() + " | Course Name: " + courseName);
            }
        }

        if (!found) {
            System.out.println("No courses registered for this student.\n");
        } else {
            System.out.println();
        }
    }

    public static void dropCourseForStudent(Student selectedStudent) {

        viewRegisteredCoursesForStudent(selectedStudent);

        System.out.print("\nEnter Course ID to drop: ");
        String courseIdToDrop = sc.nextLine();

        boolean removed = false;

        for (int i = 0; i < registerdCourses.size(); i++) {
            RegisterdCourse rc = registerdCourses.get(i);
            if (rc.getStudentId().equals(selectedStudent.getId()) && rc.getCourseId().equals(courseIdToDrop)) {
                registerdCourses.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Course dropped successfully.\n");
            // saveRegisterdCoursesToFile();
            saveAllDataToFiles();
        } else {
            System.out.println("Course not found or not registered by this student.\n");
        }
    }

    public static void awardMarksToStudent(Teacher selectedTeacher) {
        if (selectedTeacher == null) {
            System.out.println("No teacher selected.");
            return;
        }

        String teacherId = selectedTeacher.getId();

        List<String> teacherCourses = getCoursesAssignedToTeacher(teacherId);
        if (teacherCourses.isEmpty()) {
            System.out.println("You are not assigned to any courses.");
            return;
        }

        System.out.print("\nEnter Course ID to award marks in: ");
        String courseId = sc.nextLine();

        if (!teacherCourses.contains(courseId)) {
            System.out.println("You are not assigned to this course.");
            return;
        }

        List<String> eligibleStudents = getUnmarkedStudentsInCourse(teacherId, courseId);
        if (eligibleStudents.isEmpty()) {
            System.out.println("No unmarked students for this course.");
            return;
        }

        System.out.print("\nEnter Student ID to award marks: ");
        String studentId = sc.nextLine();

        if (!eligibleStudents.contains(studentId)) {
            System.out.println("This student is not assigned to you for this course or already marked.");
            return;
        }

        awardMarksToStudent(teacherId, courseId, studentId);
    }

    private static List<String> getCoursesAssignedToTeacher(String teacherId) {
        List<String> courses = new ArrayList<>();
        System.out.println("\nCourses assigned to you:");
        for (AssignedCourse ac : assignedCourses) {
            if (ac.getTeacherId().equals(teacherId)) {
                System.out.println("Course ID: " + ac.getCourseId());
                courses.add(ac.getCourseId());
            }
        }
        return courses;
    }

    private static List<String> getUnmarkedStudentsInCourse(String teacherId, String courseId) {
        List<String> students = new ArrayList<>();
        System.out.println("\nStudents assigned to you for this course (not yet marked):");
        for (Marks m : obtainedMarksOfCourse) {
            if (m.getTeacherId().equals(teacherId) &&
                    m.getCourseId().equals(courseId) &&
                    m.getObtainedMarks() == 0.0) {
                System.out.println("Student ID: " + m.getStudentId());
                students.add(m.getStudentId());
            }
        }
        return students;
    }

    private static void awardMarksToStudent(String teacherId, String courseId, String studentId) {
        try {
            System.out.print("Enter obtained marks (out of 100): ");
            double marks = sc.nextDouble();
            sc.nextLine(); // clear buffer

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Must be between 0 and 100.");
                return;
            }

            for (Marks m : obtainedMarksOfCourse) {
                if (m.getTeacherId().equals(teacherId) &&
                        m.getCourseId().equals(courseId) &&
                        m.getStudentId().equals(studentId)) {

                    m.setObtainedMarks(marks);
                    saveAllDataToFiles();
                    System.out.println("Marks awarded successfully to Student ID: " + studentId);
                    return;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric marks.");
            sc.nextLine(); // clear buffer
        }
    }

    public static void editMarks(Teacher selectedTeacher) {
        if (selectedTeacher == null) {
            System.out.println("No teacher selected.");
            return;
        }

        displayAssignedCourses(selectedTeacher.getId());

        System.out.print("Enter Course ID to edit marks for: ");
        String courseId = sc.nextLine();

        if (!isCourseAssignedToTeacher(courseId, selectedTeacher.getId())) {
            System.out.println("You are not assigned to this course.");
            return;
        }

        displayStudentsWithMarks(courseId, selectedTeacher.getId());

        System.out.print("Enter Student ID to edit marks for: ");
        String studentId = sc.nextLine();

        Marks markRecord = findMarkRecord(studentId, courseId, selectedTeacher.getId());

        if (markRecord == null) {
            System.out.println("Student not found or not assigned to you for this course.");
            return;
        }

        if (markRecord.getObtainedMarks() == -1) {
            System.out.println("Marks have not been awarded yet. Please use award function.");
            return;
        }

        try {
            System.out.print("Enter new marks (0 to 100): ");
            double newMarks = sc.nextDouble();
            sc.nextLine(); // consume leftover newline

            if (newMarks < 0 || newMarks > 100) {
                System.out.println("Invalid marks. Must be between 0 and 100.");
                return;
            }

            markRecord.setObtainedMarks(newMarks);
            System.out.println("Marks updated successfully.");
            saveAllDataToFiles();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric marks.");
            sc.nextLine();
        }
    }

    private static void displayAssignedCourses(String teacherId) {
        System.out.println("Courses assigned to you:");
        for (AssignedCourse ac : assignedCourses) {
            if (ac.getTeacherId().equals(teacherId)) {
                System.out.println("Course ID: " + ac.getCourseId());
            }
        }
    }

    private static boolean isCourseAssignedToTeacher(String courseId, String teacherId) {
        for (AssignedCourse ac : assignedCourses) {
            if (ac.getCourseId().equals(courseId) && ac.getTeacherId().equals(teacherId)) {
                return true;
            }
        }
        return false;
    }

    private static void displayStudentsWithMarks(String courseId, String teacherId) {
        System.out.println("Students assigned to you for course " + courseId + ":");
        for (Marks m : obtainedMarksOfCourse) {
            if (m.getCourseId().equals(courseId) && m.getTeacherId().equals(teacherId)) {
                String marksStr = (m.getObtainedMarks() == -1) ? "Not marked yet"
                        : String.valueOf(m.getObtainedMarks());
                System.out.println("Student ID: " + m.getStudentId() + " | Current Marks: " + marksStr);
            }
        }
    }

    private static Marks findMarkRecord(String studentId, String courseId, String teacherId) {
        for (Marks m : obtainedMarksOfCourse) {
            if (m.getStudentId().equals(studentId) &&
                    m.getCourseId().equals(courseId) &&
                    m.getTeacherId().equals(teacherId)) {
                return m;
            }
        }
        return null;
    }

    public static void viewMarksForStudent(Student selectedStudent) {
        if (selectedStudent == null) {
            System.out.println("No student selected.");
            return;
        }

        String studentId = selectedStudent.getId();

        // Step 1: Show registered courses
        List<String> registeredCourseIds = new ArrayList<>();
        System.out.println("\nCourses you are registered in:");
        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(studentId)) {
                for (Course c : offeredCourses) {
                    if (c.getCourseId().equals(rc.getCourseId())) {
                        System.out.println("Course ID: " + c.getCourseId() + ", Name: " + c.getCourseName());
                        registeredCourseIds.add(c.getCourseId());
                    }
                }
            }
        }

        if (registeredCourseIds.isEmpty()) {
            System.out.println("You are not registered in any courses.");
            return;
        }

        // Step 2: Select course
        System.out.print("\nEnter Course ID to view marks: ");
        String courseId = sc.nextLine();

        if (!registeredCourseIds.contains(courseId)) {
            System.out.println("Invalid Course ID or not registered in this course.");
            return;
        }

        // Step 3: Find and display marks
        for (Marks m : obtainedMarksOfCourse) {
            if (m.getStudentId().equals(studentId) && m.getCourseId().equals(courseId)) {
                if (m.getObtainedMarks() == -1) {
                    System.out.println("Marks not awarded yet for this course.");
                } else {
                    System.out.println("Obtained Marks in " + courseId + ": " + m.getObtainedMarks() + "/100");
                }
                return;
            }
        }

        System.out.println("No marks record found for this course.");
    }

   public static void viewStudentMarksByTeacher(Teacher selectedTeacher) {
    if (selectedTeacher == null) {
        System.out.println("No teacher selected.");
        return;
    }

    String teacherId = selectedTeacher.getId();
    List<String> assignedCourseIds = displayAssignedCoursesAndGetIds(teacherId);

    if (assignedCourseIds.isEmpty()) {
        System.out.println("You have not been assigned to any courses.");
        return;
    }

    String courseId = getCourseIdFromTeacher(assignedCourseIds);
    if (courseId == null) return;

    List<String> studentIds = displayStudentsInCourseAndGetIds(teacherId, courseId);

    if (studentIds.isEmpty()) {
        System.out.println("No students assigned to you for this course.");
        return;
    }

    String studentId = getStudentIdFromTeacher(studentIds);
    if (studentId == null) return;

    displayStudentMarks(teacherId, courseId, studentId);
}

private static List<String> displayAssignedCoursesAndGetIds(String teacherId) {
    List<String> assignedCourseIds = new ArrayList<>();
    System.out.println("\nCourses assigned to you:");
    for (AssignedCourse ac : assignedCourses) {
        if (ac.getTeacherId().equals(teacherId)) {
            for (Course c : offeredCourses) {
                if (c.getCourseId().equals(ac.getCourseId())) {
                    System.out.println("Course ID: " + c.getCourseId() + ", Name: " + c.getCourseName());
                    assignedCourseIds.add(c.getCourseId());
                }
            }
        }
    }
    return assignedCourseIds;
}


private static String getCourseIdFromTeacher(List<String> validCourseIds) {
    System.out.print("\nEnter Course ID to view student marks: ");
    String courseId = sc.nextLine();
    if (!validCourseIds.contains(courseId)) {
        System.out.println("Invalid Course ID or not assigned to this course.");
        return null;
    }
    return courseId;
}
private static List<String> displayStudentsInCourseAndGetIds(String teacherId, String courseId) {
    List<String> studentIds = new ArrayList<>();
    System.out.println("\nStudents assigned to you in course " + courseId + ":");
    for (Marks m : obtainedMarksOfCourse) {
        if (m.getTeacherId().equals(teacherId) && m.getCourseId().equals(courseId)) {
            System.out.println("Student ID: " + m.getStudentId());
            studentIds.add(m.getStudentId());
        }
    }
    return studentIds;
}
private static String getStudentIdFromTeacher(List<String> validStudentIds) {
    System.out.print("\nEnter Student ID to view marks: ");
    String studentId = sc.nextLine();
    if (!validStudentIds.contains(studentId)) {
        System.out.println("This student is not assigned to you for this course.");
        return null;
    }
    return studentId;
}
private static void displayStudentMarks(String teacherId, String courseId, String studentId) {
    for (Marks m : obtainedMarksOfCourse) {
        if (m.getTeacherId().equals(teacherId) &&
            m.getCourseId().equals(courseId) &&
            m.getStudentId().equals(studentId)) {

            if (m.getObtainedMarks() == -1) {
                System.out.println("Marks have not been awarded yet.");
            } else {
                System.out.println("Marks for Student ID " + studentId + " in Course ID " + courseId + ": "
                        + m.getObtainedMarks() + "/100");
            }
            return;
        }
    }
    System.out.println("No marks record found.");
}


    public static void saveAllDataToFiles() {
        try (BufferedWriter teacherWriter = new BufferedWriter(new FileWriter("teachers.txt"));
                BufferedWriter studentWriter = new BufferedWriter(new FileWriter("students.txt"))) {

            // Save teachers and their assigned courses
            for (Teacher t : teachers) {
                teacherWriter.write("ID: " + t.getId() + ", Name: " + t.getName() + ", AssignedCourses: [");
                List<String> assigned = new ArrayList<>();
                for (AssignedCourse ac : assignedCourses) {
                    if (ac.getTeacherId().equals(t.getId())) {
                        assigned.add("CourseID: " + ac.getCourseId());
                    }
                }
                teacherWriter.write(String.join(" , ", assigned));
                teacherWriter.write("]");
                teacherWriter.newLine();
            }

            // Save students and their registered courses with marks
            for (Student s : students) {
                studentWriter.write("ID: " + s.getId() + ", Name: " + s.getName() + ", RegisteredCourses: [");
                List<String> courses = new ArrayList<>();
                for (RegisterdCourse rc : registerdCourses) {
                    if (rc.getStudentId().equals(s.getId())) {
                        String courseStr = "CourseID: " + rc.getCourseId() +
                                ", TeacherID: " + rc.getTeacherId();
                        for (Marks mark : obtainedMarksOfCourse) {
                            if (mark.getStudentId().equals(s.getId()) &&
                                    mark.getCourseId().equals(rc.getCourseId()) &&
                                    mark.getTeacherId().equals(rc.getTeacherId())) {
                                courseStr += ", Marks: " + mark.getObtainedMarks();
                                break;
                            }
                        }
                        courses.add(courseStr);
                    }
                }
                studentWriter.write(String.join(" | ", courses));
                studentWriter.write("]");
                studentWriter.newLine();
            }

        } catch (IOException e) {
            // System.out.println("Error saving data to files.");
            e.printStackTrace();
        }
    }

    public static void loadAllDataFromFiles() {
        teachers.clear();
        students.clear();
        assignedCourses.clear();
        registerdCourses.clear();
        obtainedMarksOfCourse.clear();

        loadTeachersFromFile();
        loadStudentsFromFile();
    }

    private static void loadTeachersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("teachers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseTeacherLine(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading teachers data.");
        }
    }

    private static void parseTeacherLine(String line) {
        String[] parts = line.split(", AssignedCourses: \\[");
        String[] idName = parts[0].split(", Name: ");
        String id = idName[0].replace("ID: ", "").trim();
        String name = idName[1].trim();
        teachers.add(new Teacher(id, name));

        if (parts.length > 1) {
            String[] courses = parts[1].replace("]", "").split(" , ");
            for (String c : courses) {
                if (!c.trim().isEmpty()) {
                    String courseId = c.replace("CourseID: ", "").trim();
                    assignedCourses.add(new AssignedCourse(courseId, id));
                }
            }
        }
    }

    private static void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseStudentLine(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading students data.");
        }
    }

    private static void parseStudentLine(String line) {
        String[] parts = line.split(", RegisteredCourses: \\[");
        String[] idName = parts[0].split(", Name: ");
        String id = idName[0].replace("ID: ", "").trim();
        String name = idName[1].trim();
        students.add(new Student(id, name));

        if (parts.length > 1) {
            String[] entries = parts[1].replace("]", "").split(" \\| ");
            for (String entry : entries) {
                parseRegisteredCourseEntry(id, entry);
            }
        }
    }

    private static void parseRegisteredCourseEntry(String studentId, String entry) {
        String[] tokens = entry.split(", ");
        String courseId = "", teacherId = "";
        double marks = -1;

        for (String token : tokens) {
            if (token.startsWith("CourseID: ")) {
                courseId = token.replace("CourseID: ", "").trim();
            } else if (token.startsWith("TeacherID: ")) {
                teacherId = token.replace("TeacherID: ", "").trim();
            } else if (token.startsWith("Marks: ")) {
                try {
                    marks = Double.parseDouble(token.replace("Marks: ", "").trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid mark for student: " + studentId);
                }
            }
        }

        if (!courseId.isEmpty() && !teacherId.isEmpty()) {
            registerdCourses.add(new RegisterdCourse(studentId, courseId, teacherId));
            if (marks != -1) {
                obtainedMarksOfCourse.add(new Marks(studentId, teacherId, courseId, marks));
            }
        }
    }

    public static void generateResultCard(Student selectedStudent) {
        System.out.println("Result Card for " + selectedStudent.getName() + " (" + selectedStudent.getId() + ")");
        calculateCGPA(selectedStudent);
    }

    public static void viewCoursesAndAssignedTeachers() {
        System.out.println("Courses and Their Assigned Teachers:");
        System.out.println("-------------------------------------");

        for (AssignedCourse ac : assignedCourses) {
            System.out.println("Course ID: " + ac.getCourseId() + ", Assigned Teacher ID: " + ac.getTeacherId());
        }
    }

    public static void displayStudentCourseDetails(Student student) {
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student: " + student.getName() + " (ID: " + student.getId() + ")");

        System.out.printf("+------------+------------------------------+----------------+\n");
        System.out.printf("| %-10s | %-28s | %-14s |\n", "Course ID", "Course Title", "Teacher Name");
        System.out.printf("+------------+------------------------------+----------------+\n");

        boolean hasCourses = false;

        for (RegisterdCourse rc : registerdCourses) {
            if (rc.getStudentId().equals(student.getId())) {
                String courseId = rc.getCourseId();
                String courseName = "N/A";
                String teacherId = rc.getTeacherId();
                String teacherName = "N/A";

                for (Course c : offeredCourses) {
                    if (c.getCourseId().equals(courseId)) {
                        courseName = c.getCourseName();
                        break;
                    }
                }

                for (Teacher t : teachers) {
                    if (t.getId().equals(teacherId)) {
                        teacherName = t.getName();
                        break;
                    }
                }

                System.out.printf("| %-10s | %-28s | %-14s |\n",
                        courseId, courseName, teacherName);
                hasCourses = true;
            }
        }

        if (!hasCourses) {
            System.out.println("| No courses registered for this student.                    |");
        }

        System.out.printf("+------------+------------------------------+----------------+\n");
    }

    public static void displayAssignedCoursesForTeacher(Teacher teacher) {
        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        System.out.println("Teacher: " + teacher.getName() + " (ID: " + teacher.getId() + ")");

        System.out.printf("+------------+------------------------------+\n");
        System.out.printf("| %-10s | %-28s |\n", "Course ID", "Course Title");
        System.out.printf("+------------+------------------------------+\n");

        boolean hasCourses = false;

        for (AssignedCourse ac : assignedCourses) {
            if (ac.getTeacherId().equals(teacher.getId())) {
                String courseId = ac.getCourseId();
                String courseName = "N/A";

                for (Course c : offeredCourses) {
                    if (c.getCourseId().equals(courseId)) {
                        courseName = c.getCourseName();
                        break;
                    }
                }

                System.out.printf("| %-10s | %-28s |\n", courseId, courseName);
                hasCourses = true;
            }
        }

        if (!hasCourses) {
            System.out.println("| No courses assigned to this teacher.      |");
        }

        System.out.printf("+------------+------------------------------+\n");
    }
}