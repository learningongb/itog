package Counter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Core.Animal;
import Core.AnimalKind;
import Core.AnimalType;
import Exceptions.ExceptionEmptyField;
import View.Menu;

public class Counter {
    static private int count;

    public void add(Menu menu, List<AnimalType> animalTypes, List<AnimalKind> animalKinds, List<Animal> animals)
            throws Exception {
        try {
            AnimalType type = menu.GetAnimalType(animalTypes);
            AnimalKind kind = menu.GetAnimalKind(animalKinds);
            String name = menu.prompt("Введите кличку:");
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
            Date birthday = formater.parse(menu.prompt("Введите дату рождения в формате ДД-ММ-ГГГГ"));
            if (name.isEmpty() || type == null || kind == null) {
                throw new ExceptionEmptyField();
            }
            animals.add(new Animal(type, kind, name, birthday));
            Counter.count++;
        } catch (ExceptionEmptyField e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Ошибка при вводе данных");
        }
    }

    public void printCount() {
        System.out.println(count);
    }
}
