package service;

import animals.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ZooService {
    public static void saveAnimals(ArrayList<Animals> animals) throws IOException {
        FileWriter fileWriter = new FileWriter("animals.txt");
        for (Animals animal : animals) {
            fileWriter.write(animal.toString());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.flush();
        fileWriter.close();

    }

    public static ArrayList<Animals> loadAnimals(ArrayList<Animals> animals) {

        try (FileReader fr = new FileReader("animals.txt");
             Scanner scanner = new Scanner(fr)) {

            scanner.useDelimiter(", *");
            ArrayList<String> animalsStr = new ArrayList<>();
            while (scanner.hasNext()) {
                animalsStr.add(scanner.nextLine());
            }

            AnimalCreator.create(animalsStr, animals);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animals;
    }

    public static void selectAnimals(ArrayList<Animals> animals) {
        System.out.println("1 - Посмотреть Тигров " + "2 - Посмотреть Волков " + "3 - Посмотреть Пингвинов " + "4 - Посмотреть Кенгуру " + "5 - Посмотреть Медведей");

        Scanner scanner = new Scanner(System.in);
        int animalNum = scanner.nextInt();

        Class questionMark = switch (animalNum) {
            case 1 -> Tiger.class;
            case 2 -> Wolf.class;
            case 3 -> Penguin.class;
            case 4 -> Kangaroo.class;
            case 5 -> Bear.class;
            default -> throw new IllegalStateException("Unexpected value: " + animalNum);
        };

        animalsByType(animals, questionMark);
    }

    public static void selectSign(ArrayList<Animals> animals) {
        System.out.println("1 - Количество ног " + "2 - Хищник? " + "3 - Цвет " + "4 - Ареал обитания ");

        Scanner scanner = new Scanner(System.in);
        int sign = scanner.nextInt();

        switch (sign) {
            case 1 -> {
                System.out.println("Введите кол-во ног");
                int numOfLegs = scanner.nextInt();
                System.out.println(animals.stream().filter(p -> p.legsNumber == numOfLegs).toList());
            }
            case 2 -> {
                boolean predator = scanner.nextBoolean();
                System.out.println(animals.stream().filter(p -> p.isPredator == predator).toList());
            }
            case 3 -> {
                System.out.println("Введите цвет");
                String color = scanner.nextLine();
                System.out.println(animals.stream().filter(p -> p.color.equals(color)).toList());
            }
            case 4 -> {
                System.out.println("Введите ареал обитания");
                String area = scanner.nextLine();
                System.out.println(animals.stream().filter(p -> p.area.equals(area)).toList());
            }
        }
    }

    public static void animalsByType(ArrayList<Animals> animals, Class questionMark) {
        for (Animals animal : animals) {
            if (animal.getClass().equals(questionMark)) {
                System.out.println(animal);
            }
        }
    }

    public static void callAnimal(ArrayList<Animals> animals) {
        System.out.println("Введите имя");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println(animals.stream().filter(p -> p.name.equals(name)).toList());
    }

    public static void deleteAnimal(ArrayList<Animals> animals) throws IOException {
        System.out.println("Введите имя");
        Scanner scanner = new Scanner(System.in);
        String inputName = scanner.nextLine();

        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName().equals(inputName)) {
                animals.remove(i);
            }
        }

//        Не работает!
//        animals.removeIf(p -> p.getName().equals(inputName));

        saveAnimals(animals);
    }

    public static void addAnimal(ArrayList<Animals> animals) throws IOException {
        System.out.println("Добавьте животное");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> strAnimal = new ArrayList<>();
        strAnimal.add(scanner.nextLine());
        AnimalCreator.create(strAnimal, animals);
        System.out.println("Животное добавлено");
    }

    public static void renameAnimal(ArrayList<Animals> animals) throws IOException {
        System.out.println("Введите имя животного");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Введите новое имя животного");
        animals
                .stream()
                .filter(p -> p.name.equals(name))
                .forEach(p -> p.setName(scanner.nextLine()));
        saveAnimals(animals);
    }

    public static void equalAnimals(ArrayList<Animals> animals)  {
        System.out.println("Введите имя 1-го животного");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        var firstAnimal = animals
                .stream()
                .filter(p -> p.getName().equals(firstName)).toList();

        System.out.println("Введите имя 2-го животного");
        String secondName = scanner.nextLine();
        var secondAnimal = animals
                .stream()
                .filter(p -> p.getName().equals(secondName)).toList();

        String[] f = firstAnimal
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining())
                .split(", ");
        String[] s = secondAnimal
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining())
                .split(", ");

        System.out.println("Совпадающие признаки");
        for (String value : f) {
            for (String item : s) {
                if (value.equals(item)) {
                    System.out.println(value);
                }
            }
        }

        System.out.println("Несовпадающие признаки");
        for (int i = 0; i < f.length; i++) {
                if (!f[i].equals(s[i])) {
                    System.out.println(f[i] + " - " + s[i]);
                }
            }
        }
}
