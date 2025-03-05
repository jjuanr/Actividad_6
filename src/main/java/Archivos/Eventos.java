package Archivos;

import java.io.*;
import java.util.*;

public class Eventos {

    private File archivo;
    private boolean Error;

    public Eventos() {
        archivo = new File("Contactos.txt");
    }

    public void Agregar_Texto(String texto) {
        try (FileWriter escritor = new FileWriter(archivo, true)) {
            escritor.write(texto + "\n");
            Error = false;
        } catch (IOException e) {
            Error = true;
        }
    }

    public void Eliminar_Dato(String nombre) {
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        archivo = new File("Contactos.txt");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");
                String nombreArchivo = partes[0];

                if (!nombreArchivo.equalsIgnoreCase(nombre)) {
                    lineas.add(linea);  // Guardamos solo las líneas que no coinciden
                } else {
                    encontrado = true;
                }
            }
        } catch (IOException e) {
            Error = true;
            return;
        }

        if (!encontrado) {
            Error = true;
            return;
        }

        // Reescribir el archivo sin el dato eliminado
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
            Error = false;
        } catch (IOException e) {
            Error = true;
        }
    }


    public String Obtener_Celular(String nombre) {
        archivo = new File("Contactos.txt");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");
                if (partes.length == 2) {
                    String nombreArchivo = partes[0];
                    String celular = partes[1];

                    if (nombreArchivo.equalsIgnoreCase(nombre)) {
                        return celular;  // Retorna el número si el nombre coincide
                    }
                }
            }
        } catch (IOException e) {
            Error = true;
        }

        return null;  // Retorna null si no encuentra el nombre
    }

    public void Editar_Dato(String nombre, String nuevoCelular) {
        archivo = new File("Contactos.txt");
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");
                if (partes.length == 2) {
                    String nombreArchivo = partes[0];

                    if (nombreArchivo.equalsIgnoreCase(nombre)) {
                        lineas.add(nombre + "!" + nuevoCelular); // Reemplaza el número de celular
                        encontrado = true;
                    } else {
                        lineas.add(linea); // Guarda la línea original
                    }
                }
            }
        } catch (IOException e) {
            Error = true;
            return;
        }

        if (!encontrado) {
            Error = true;
            return;
        }

        // Sobrescribir el archivo con los datos actualizados
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
            Error = false;
        } catch (IOException e) {
            Error = true;
        }
    }

    public void Verificar_Dato(String nombre, String A) {
        boolean encontrado = false;
        archivo = new File("Contactos.txt");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("!");  // Separar nombre y celular
                String nombreArchivo = partes[0];    // Tomar solo el nombre
                System.out.println(nombre);

                if (nombreArchivo.equalsIgnoreCase(nombre)) {
                    System.out.println("Jo");
                    encontrado = true;
                    break;  // Si lo encontramos, salimos del bucle
                }
            }
        } catch (IOException e) {
            Error = true;
            return;
        }
        System.out.println(encontrado);
        // Lógica corregida para definir Error correctamente
        if (A.equals("Crear")) {
            Error = encontrado;  // Si ya existe, hay error
        } else if (A.equals("Eliminar")) {
            Error = !encontrado; // Si no existe, hay error
        }
    }


    public boolean getError() {
        return Error;
    }
}