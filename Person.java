public class Person {
    private String Id;
    private String Name;

    // Constructor
    Person(String Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    // Getter for Id
    public String getId() {
        return Id;
    }

    // Setter for Id
    public void setId(String Id) {
        this.Id = Id;
    }

    // Getter for Name
    public String getName() {
        return Name;
    }

    // Setter for Name
    public void setName(String Name) {
        this.Name = Name;
    }
}
