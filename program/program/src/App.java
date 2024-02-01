import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import Controller.Controller;
import Core.Animal;
import Core.AnimalKind;
import Core.AnimalType;

public class App {
    public static void main(String[] args) throws Exception {

        AnimalType packAnimal = new AnimalType(1, "Pack animal");
        AnimalType petAnimal = new AnimalType(2, "Pet animal");

        AnimalKind horse = new AnimalKind(1, "Horse");
        AnimalKind donkey = new AnimalKind(2, "Donkey");
        AnimalKind camel = new AnimalKind(3, "Camel");
        AnimalKind dog = new AnimalKind(4, "Dog");
        AnimalKind cat = new AnimalKind(5, "Cat");
        AnimalKind hamster = new AnimalKind(6, "Hamster");

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal(packAnimal, horse, "Варяг", new GregorianCalendar(2021, 01, 10).getTime()));
        animals.add(new Animal(packAnimal, horse, "Ясный", new GregorianCalendar(2021, 02, 02).getTime()));

        animals.add(new Animal(packAnimal, camel, "Бомбей", new GregorianCalendar(2023, 01, 31).getTime()));
        animals.add(new Animal(packAnimal, camel, "Зефир", new GregorianCalendar(2023, 05, 02).getTime()));

        animals.add(new Animal(packAnimal, donkey, "Мерлин", new GregorianCalendar(2022, 01, 02).getTime()));
        animals.add(new Animal(packAnimal, donkey, "Грей", new GregorianCalendar(2024, 01, 02).getTime()));

        animals.add(new Animal(petAnimal, dog, "Белка", new GregorianCalendar(2020, 01, 02).getTime()));
        animals.add(new Animal(petAnimal, dog, "Стрелка", new GregorianCalendar(2021, 05, 02).getTime()));

        animals.add(new Animal(petAnimal, cat, "Мурка", new GregorianCalendar(2023, 06, 07).getTime()));
        animals.add(new Animal(petAnimal, cat, "Машка", new GregorianCalendar(2020, 01, 02).getTime()));

        animals.add(new Animal(petAnimal, hamster, "Борис", new GregorianCalendar(2020, 01, 02).getTime()));
        animals.add(new Animal(petAnimal, hamster, "Зинаида", new GregorianCalendar(2020, 01, 02).getTime()));

        List<AnimalType> animalTypes = new ArrayList<>();
        animalTypes.add(packAnimal);
        animalTypes.add(petAnimal);

        List<AnimalKind> animalKinds = new ArrayList<>();
        animalKinds.add(horse);
        animalKinds.add(camel);
        animalKinds.add(donkey);
        animalKinds.add(dog);
        animalKinds.add(cat);
        animalKinds.add(hamster);

        Controller controller = new Controller(animals, animalTypes, animalKinds);
        controller.Run();

    }
}
