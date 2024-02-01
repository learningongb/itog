package Core;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Animal {
    private AnimalType type;
    private AnimalKind kind;
    private String name;
    private Date birthday;
    private List<String> commands;

    public Animal(AnimalType type, AnimalKind kind, String name, Date birthday, List<String> commands) {
        this.type = type;
        this.kind = kind;
        this.name = name;
        this.birthday = birthday;
        this.commands = commands;
    }

    public Animal(AnimalType type, AnimalKind kind, String name, Date birthday) {
        this(type, kind, name, birthday, new ArrayList<String>());
    }

    public void AddCommand(String command) {
        if (!this.commands.contains(command))
            this.commands.add(command);
    }

    public Boolean RemoveCommand(String command) {
        if (this.commands.contains(command)) {
            this.commands.remove(command);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        return "Животное (класс " + type + ", вид " + kind + "), кличка \"" + name + "\", дата рождения "
                + formater.format(birthday) + ", знает команды " + commands;
    }

}
