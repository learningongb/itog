package Controller;

import java.util.ArrayList;
import java.util.List;

import Core.Animal;
import Core.AnimalKind;
import Core.AnimalType;
import Counter.Counter;
import View.Menu;

public class Controller {

    private List<AnimalKind> animalKinds;
    private List<AnimalType> animalTypes;
    private List<Animal> animals;
    private Menu menu;
    private Counter counter;

    public Controller(List<Animal> animals, List<AnimalType> animalTypes, List<AnimalKind> animalKinds) {
        this.animals = animals;
        this.animalTypes = animalTypes;
        this.animalKinds = animalKinds;
        this.menu = new Menu();
        this.counter = new Counter();
    }

    public Controller() {
        this(new ArrayList<Animal>(),
                new ArrayList<AnimalType>(),
                new ArrayList<AnimalKind>());
    }

    public void Run() throws Exception {
        Commands command = Commands.NONE;
        boolean getNewIter = true;
        while (getNewIter) {
            menu.PrintMenu();
            command = menu.getMenuChoise("Выберите действие");
            switch (command) {
                case EXIT:
                    getNewIter = false;
                    break;
                case PRINTANIMALS:
                    menu.PrintAnimals(animals);
                    break;
                case ADDANIMAL:
                    counter.add(menu, animalTypes, animalKinds, animals);
                    break;

                case ADDCOMMAND:
                    menu.AddCommand(animals);
                    break;

                case PRINTCOMMANDS:
                    menu.PrintCommands(animals);
                    break;

                case REMOVECOMMAND:
                    menu.RemoveCommand(animals);
                    break;

                case REMOVEANIMAL:
                    menu.RemoveAnimal(animals);
                    break;

                case PRINTCOUNTER:
                    counter.printCount();
                    break;

                default:
                    menu.PrintMenu();
            }
        }
    }

}
