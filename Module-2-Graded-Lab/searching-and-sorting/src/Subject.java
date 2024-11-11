public class Subject {
    String id;
    String name;
    public Subject(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toString(){
        return id + " " + name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {

    }
}
