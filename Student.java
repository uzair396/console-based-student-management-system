public class Student extends Person {

    // Constructor
    Student(String Id, String Name) {
        super(Id, Name);
    }

    // Getter for Id (delegates to Person)
    public String getId() {
        return super.getId();
    }

    // Setter for Id
    public void setId(String Id) {
        super.setId(Id);
    }

    // Getter for Name
    public String getName() {
        return super.getName();
    }

    // Setter for Name
    public void setName(String Name) {
        super.setName(Name);
    }
}
