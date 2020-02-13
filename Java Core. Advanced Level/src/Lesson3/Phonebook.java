package Lesson3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class MainClass {
    public static void main(String[] args) {
        Phonebook phoneBook = new Phonebook();
        phoneBook.add(568762, "Shmakov");
        phoneBook.add(968741,"Ivanova");
        phoneBook.add(254867, "Petrov");
        phoneBook.add(968432, "Shmakov");
        phoneBook.add(125479, "Petrov");
        phoneBook.add(777777, "Putin");

        phoneBook.get("Shmakov");
        phoneBook.get("Ivanova");
        phoneBook.get("Petrov");
        phoneBook.get("Putin");
        phoneBook.get("Maksimov");

    }
}

class Phonebook {

    private Map<Integer, String> phoneBook;

    Phonebook() {
        phoneBook = new HashMap<>();
    }

    public void add(int number, String surname) {
        phoneBook.put(number, surname);
    }

    public void get(String surname){
        if(phoneBook.containsValue(surname)) {
            Set<Map.Entry<Integer, String>> set = phoneBook.entrySet();
            for (Map.Entry<Integer, String> temp : set) {
                if(temp.getValue().equals(surname)) {
                    System.out.println(temp.getValue() + " : " + temp.getKey());
                }
            }
        } else {
            System.out.println(surname + " : " +"Such a name is not on the list");
        }
    }
}

