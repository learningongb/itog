package View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Controller.Commands;
import Core.Animal;
import Core.AnimalKind;
import Core.AnimalType;

public class Menu {

    public void PrintMenu() {
        System.out.println("");
        System.out.println("==================================");
        System.out.println("1. Вывести список животных");
        System.out.println("2. Добавить животное");
        System.out.println("3. Обучить командам");
        System.out.println("4. Вывести список команд");
        System.out.println("5. Забыть команду");
        System.out.println("6. Удалить животное");
        System.out.println("7. Вывести значение счетчика");

        System.out.println("0. Выход");
    }

    public String prompt(String s) {
        Scanner in = new Scanner(System.in);
        System.out.println(s);
        return in.nextLine();
    }

    public Commands getMenuChoise(String s) {
        String command = prompt(s);

        switch (command) {
            case "0":
                return Commands.EXIT;

            case "1":
                return Commands.PRINTANIMALS;

            case "2":
                return Commands.ADDANIMAL;

            case "3":
                return Commands.ADDCOMMAND;

            case "4":
                return Commands.PRINTCOMMANDS;

            case "5":
                return Commands.REMOVECOMMAND;

            case "6":
                return Commands.REMOVEANIMAL;

            case "7":
                return Commands.PRINTCOUNTER;
        }
        return Commands.NONE;

    }

    public AnimalType GetAnimalType(List<AnimalType> animalTypes) {
        System.out.println("Типы животных:");
        int i = 1;
        for (AnimalType animalType : animalTypes) {
            System.out.println(i + "." + animalType);
            i++;
        }
        i = Integer.decode(prompt("Выберите тип животного"));
        if (i < 0 || i >= animalTypes.size())
            return null;
        return animalTypes.get(i - 1);
    }

    public AnimalKind GetAnimalKind(List<AnimalKind> animalKinds) {
        System.out.println("Виды животных:");
        int i = 1;
        for (AnimalKind animalKind : animalKinds) {
            System.out.println(i + "." + animalKind);
            i++;
        }
        i = Integer.decode(prompt("Выберите вид животного"));
        if (i < 0 || i >= animalKinds.size())
            return null;
        return animalKinds.get(i - 1);
    }

    public void PrintAnimals(List<Animal> animals) {
        int i = 1;
        for (Animal animal : animals) {
            System.out.println(i + ". " + animal);
            i++;
        }

    }

    public void PrintCommands(List<Animal> animals) {
        System.out.println("PrintCommands");
    }

    public void AddAnimal(List<AnimalType> animalTypes, List<AnimalKind> animalKinds, List<Animal> animals)
            throws Exception {
        AnimalType type = GetAnimalType(animalTypes);
        AnimalKind kind = GetAnimalKind(animalKinds);
        String name = prompt("Введите кличку:");
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        Date birthday = formater.parse(prompt("Введите дату рождения в формате ДД-ММ-ГГГГ"));
        animals.add(new Animal(type, kind, name, birthday));
    }

    public void AddCommand(List<Animal> animals) {
        int index = Integer.parseInt(prompt("Введите номер животного:"));
        String newcommand = prompt("Введите новую команду:");
        animals.get(index - 1).AddCommand(newcommand);
    }

    public void RemoveCommand(List<Animal> animals) {
        int index = Integer.parseInt(prompt("Введите номер животного:"));
        String newcommand = prompt("Введите удаляемую команду:");
        animals.get(index - 1).RemoveCommand(newcommand);
    }

    public void RemoveAnimal(List<Animal> animals) {
        int index = Integer.parseInt(prompt("Введите номер животного:"));
        animals.remove(index - 1);
    }

}
