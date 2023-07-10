package Java.AdressBook;

import javax.swing.*;
import java.io.*;
import java.util.*;

class PersonInfo {
    String name;
    String address;
    String phoneNumber;

    //Paramiterized constructor
    PersonInfo(String n, String a, String p) {
        name = n;
        address = a;
        phoneNumber = p;
    }

    // display on GUI
    void display() {
        JOptionPane.showMessageDialog(null, "Name: " + name + "\n" + "Address: " + address + "\n" + "Phone no: " + phoneNumber);
    }
}

    class AddressBook {
        ArrayList persons;

        // constructor
        AddressBook() {
            persons = new ArrayList();
            loadPersons();
        }

        // adding a Person Object
        void addPerson() {
            String name = JOptionPane.showInputDialog("Enter name");
            String address = JOptionPane.showInputDialog("Enter address");
            String phoneNumber = JOptionPane.showInputDialog("Enter phone no: ");
            // creating a person object
            PersonInfo p = new PersonInfo(name, address, phoneNumber);
            persons.add(p);
        }

        // displaying all persons
        void searchPerson(String n) {
            for (int i = 0; i < persons.size(); i++) {
                PersonInfo p = (PersonInfo) persons.get(i);
                if (n.equals(p.name)) {
                    p.display();
                }
            }
        }

        // deleting a person
        void deletePerson(String n) {
            for (int i = 0; i < persons.size(); i++) {
                PersonInfo p = (PersonInfo) persons.get(i);
                if (n.equals(p.name)) {
                    persons.remove(p);
                }
            }
        }

        // saving Person Record
        void savePersons() {
            try {
                PersonInfo p;
                String line;
                FileWriter fw = new FileWriter("AddressBook.txt");
                PrintWriter pw = new PrintWriter(fw);
                for (int i = 0; i < persons.size(); i++) {
                    p = (PersonInfo)persons.get(i);
                    line = p.name + "," + p.address + "," + p.phoneNumber;
                    // writer line to AddressBook.txt
                    pw.println(line);
                }
                pw.flush();
                pw.close();
                fw.close();
            } catch (IOException ioEx) {
                System.out.println(ioEx);
            }
        }


        // loading Person Record from .txt file
        void loadPersons() {
            String tokens[] = null;
            String name, add, phone;
            try {
                FileReader fr = new FileReader("AddressBook.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    tokens = line.split(",");
                    name = tokens[0];
                    add = tokens[1];
                    phone = tokens[2];
                    PersonInfo p = new PersonInfo(name, add, phone);
                    persons.add(p);
                    line = br.readLine();
                }
                br.close();
                fr.close();
            } catch(IOException ioEx) {
                System.out.println(ioEx);
        }   
    }

}

public class Test {
    public static void main(String[] args) {
        AddressBook ab = new AddressBook();
        String input, s;
        int ch;
        
        while (true) {
            input = JOptionPane.showInputDialog("Enter 1 to Add\nEnter 2 to Search\nEnter 3 to Delete\nEnter 4 to Exit");
            ch = Integer.parseInt(input);

            switch (ch) {
                case 1:
                    ab.addPerson();
                    break;
                case 2:
                    s = JOptionPane.showInputDialog("Enter name to search: ");
                    ab.searchPerson(s);
                    break;
                case 3:
                    s = JOptionPane.showInputDialog("Enter name to delete: ");
                    ab.deletePerson(s);
                    break;
                case 4:
                    ab.savePersons();
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Input");
            }
        }
    }
}
