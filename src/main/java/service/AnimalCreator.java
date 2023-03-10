package service;

import animals.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AnimalCreator {

    public static ArrayList<Animals> create(ArrayList<String> stringsAnimals, ArrayList<Animals> animals) throws IOException {
        String[] newAnimal;
        for (String stringsAnimal : stringsAnimals) {
            newAnimal = stringsAnimal.split(", ");

            String name = newAnimal[1];


            //boolean isUnic = animals.stream().findFirst().filter(p -> p.getName().equals(name)).isEmpty();
            boolean isUnic = animals
                    .stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst()
                    .isEmpty();


            if (isUnic) {
                switch (newAnimal[0]) {
                    case ("Tiger") -> animals.add(new Tiger(
                            newAnimal[1],
                            Integer.parseInt(newAnimal[2]),
                            Boolean.parseBoolean(newAnimal[3]),
                            newAnimal[4],
                            newAnimal[5]
                    ));
                    case ("Wolf") -> animals.add(new Wolf(
                            newAnimal[1],
                            Integer.parseInt(newAnimal[2]),
                            Boolean.parseBoolean(newAnimal[3]),
                            newAnimal[4],
                            newAnimal[5]
                    ));
                    case ("Penguin") -> animals.add(new Penguin(
                            newAnimal[1],
                            Integer.parseInt(newAnimal[2]),
                            Boolean.parseBoolean(newAnimal[3]),
                            newAnimal[4],
                            newAnimal[5]
                    ));
                    case ("Bear") -> animals.add(new Bear(
                            newAnimal[1],
                            Integer.parseInt(newAnimal[2]),
                            Boolean.parseBoolean(newAnimal[3]),
                            newAnimal[4],
                            newAnimal[5]
                    ));
                    case ("Kangaroo") -> animals.add(new Kangaroo(
                            newAnimal[1],
                            Integer.parseInt(newAnimal[2]),
                            Boolean.parseBoolean(newAnimal[3]),
                            newAnimal[4],
                            newAnimal[5]
                    ));
                }

            } else {
                System.out.println("???????????????? " + newAnimal[0] + " " + newAnimal[1] + " ???? ???????? ??????????????????, ?????????????????? ???? ?????????????? ???????????????????? ????????????");
            }

        }
        ZooService.saveAnimals(animals);
        return animals;
    }

}
