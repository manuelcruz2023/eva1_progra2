package co.edu.uptc.eval1.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.eval1.models.Person;

public class ReadTxt {
    private String path;
    private BufferedReader br;

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> readTxt() throws IOException {
        List<String> persons = new ArrayList<>();
        File file = new File(path);
        FileReader fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line = "";
        while (br.readLine() != null) {
            persons.add(line += br.readLine());
        }
        fr.close();
        br.close();
        return persons;
    }

    public byte[] readBytes() throws IOException {
        File File_Path = new File(path);
        FileInputStream File_Input_Stream = new FileInputStream(File_Path);
        byte[] Demo_Array = new byte[(int) File_Path.length()];
        File_Input_Stream.read(Demo_Array);
        File_Input_Stream.close();
        return Demo_Array;
    }

    public List<Person> completPersons() throws IOException {
        byte[] listBytes = readBytes();
        List<Person> persons = new ArrayList<>();
        int iterador = 0;

        for (int o = 0; o < 811; o++) {
            int cont1 = 0;
            int cont2 = 0;
            int cont3 = 0;
            int cont4 = 0;

            Person person = new Person();

            if (cont1 == 0) {
                int iterador1 = iterador + 9;
                int age = 0;
                String StringAge = "";
                for (int i = iterador; i <= iterador1; i++) {
                    if (listBytes[i] != 32) {
                        StringAge += (char) listBytes[i];
                    }
                    iterador++;
                }
                age = Integer.parseInt(StringAge);
                person.setAge(age);
                cont1++;
            }

            if (cont2 == 0) {
                int iterador2 = iterador + 39;

                String nombre = "";
                int contEspacios = 0;
                for (int i = iterador; i <= iterador2; i++) {
                    if (listBytes[i] != 32) {
                        nombre += (char) listBytes[i];
                    } else if (!(listBytes[i - 1] == 32) && contEspacios == 0) {
                            nombre += " ";
                            contEspacios++;
                    }
                    iterador++;
                }
                person.setName(nombre);
                cont2++;
            }

            if (cont3 == 0) {
                int iterador3 = iterador + 39;
                String apellido = "";
                for (int i = iterador; i <= iterador3; i++) {
                    if (listBytes[i] != 32) {
                        apellido += (char) listBytes[i];
                    }
                    iterador++;
                }
                person.setLastName(apellido);
                cont3++;
            }

            if (cont4 == 0) {
                int iterador4 = iterador + 19;
                int salario = 0;
                String salarioA = "";
                String salarioB = "";
                for (int i = iterador; i <= iterador4; i++) {
                    if (listBytes[i] != 10) {
                        salarioA += (char) listBytes[i];
                        iterador++;
                    }
                }
                for (int i = 0; i < salarioA.length(); i++) {
                    String temp = salarioA.substring(i, i + 1);
                    if (!temp.equals(" ")) {
                        salarioB += temp;
                    }
                }
                salario = Integer.parseInt(salarioB);
                person.setSalary(salario);
                iterador++;
                cont4++;
            }
            persons.add(person);
        }
        return persons;
    }

    public List<String> puntoA() throws Exception {
        List<Person> listaPersonas = completPersons();
        List<Person> puntoAList = new ArrayList<>();
        List<String> puntoAListString = new ArrayList<>();
        long promedioSalarios = 0;
        for (Person person : listaPersonas) {
            promedioSalarios += person.getSalary();
        }
        promedioSalarios = promedioSalarios / listaPersonas.size();
        for (Person person : listaPersonas) {
            if (person.getSalary() > promedioSalarios) {
                puntoAList.add(person);
            }
        }
        for (Person person : puntoAList) {
            puntoAListString.add(person.getName() + "%" + person.getLastName() + "%" + person.getSalary() + "%"
                    + person.getAge() + "\n");
        }
        return puntoAListString;
    }

    public void createFileA(String nombreArchivo) throws Exception {
        List<String> persons = puntoA();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String elemento : persons) {
                bw.write(elemento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> puntoB() throws Exception {
        List<Person> listaPersonas = completPersons();
        List<Person> puntoBList = new ArrayList<>();
        long promedioSalarios = 0;
        for (Person person : listaPersonas) {
            promedioSalarios += person.getSalary();
        }
        promedioSalarios = promedioSalarios / listaPersonas.size();
        for (Person person : listaPersonas) {
            if (person.getSalary() < promedioSalarios) {
                puntoBList.add(person);
            }
        }
        List<String> listaPersonasString = new ArrayList<>();
        for (Person person : puntoBList) {
            listaPersonasString.add(person.getAge() + "###" + person.getName() + " " + person.getLastName() + "###"
                    + person.getSalary() + "\n");
        }
        return listaPersonasString;
    }

    public void createFileB(String nombreArchivo) throws Exception {
        List<String> persons = puntoB();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String elemento : persons) {
                bw.write(elemento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}