package Core;

public class AnimalKind {
    private int id;
    private String name;
    
    public AnimalKind(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
