package Archivos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Principal extends JFrame implements ActionListener {

    private Container Contenedor;
    private JButton Crear, Editar, Eliminar, Leer;
    private JLabel Nombre, Celular;
    private JTextField Campo_Nombre, Campo_Celular;

    public Principal() {
        Inicio();
        setTitle("Ventana principal");
        setSize(390, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void Inicio()   {
        Contenedor = getContentPane();
        Contenedor.setLayout(null);
        Nombre = new JLabel();
        Nombre.setText("Nombre");
        Nombre.setBounds(50, 30, 135, 23);
        Campo_Nombre = new JTextField();
        Campo_Nombre.setBounds(180, 30, 135, 23);
        Celular = new JLabel();
        Celular.setText("Celular");
        Celular.setBounds(50, 110, 135, 23);
        Campo_Celular = new JTextField();
        Campo_Celular.setBounds(180, 110, 135, 23);
        Crear = new JButton();
        Crear.setText("Crear");
        Crear.setBounds(10, 160, 85, 23);
        Crear.addActionListener(this);
        Editar = new JButton();
        Editar.setText("Editar");
        Editar.setBounds(100, 160, 85, 23);
        Editar.addActionListener(this);
        Eliminar = new JButton();
        Eliminar.setText("Eliminar");
        Eliminar.setBounds(190, 160, 85, 23);
        Eliminar.addActionListener(this);
        Leer = new JButton();
        Leer.setText("Leer");
        Leer.setBounds(280, 160, 85, 23);
        Leer.addActionListener(this);
        Contenedor.add(Nombre);
        Contenedor.add(Celular);
        Contenedor.add(Campo_Nombre);
        Contenedor.add(Campo_Celular);
        Contenedor.add(Crear);
        Contenedor.add(Editar);
        Contenedor.add(Eliminar);
        Contenedor.add(Leer);
    }

    public void actionPerformed(ActionEvent evento) {
        Eventos archivo = new Eventos();
        if (evento.getSource() == Crear)  {
            if (Campo_Nombre.getText().trim().isEmpty() || Campo_Celular.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos","Error", JOptionPane.ERROR_MESSAGE);
            }   else    {
                String Object = getString();
                String nombre = Campo_Nombre.getText();
                archivo.Verificar_Dato(nombre, "Crear");
                boolean Error = archivo.getError();
                if (Error) {
                    JOptionPane.showMessageDialog(null, "El dato ya existe en la lista", "Error", JOptionPane.ERROR_MESSAGE);
                    Campo_Celular.setText("");
                    Campo_Nombre.setText("");
                }
                else {
                    archivo.Agregar_Texto(Object);
                    JOptionPane.showMessageDialog(null, "Datos Agregados","Exito", JOptionPane.INFORMATION_MESSAGE);
                    Campo_Celular.setText("");
                    Campo_Nombre.setText("");
                }
            }
        }
        if (evento.getSource() == Eliminar) {
            String dato = Campo_Nombre.getText();
            archivo.Eliminar_Dato(dato);
            boolean Error = archivo.getError();
            if (Error) {
                JOptionPane.showMessageDialog(null, "No hay dato para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Dato eliminado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (evento.getSource() == Editar) {
            if (Campo_Nombre.getText().trim().isEmpty() || Campo_Celular.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String nombre = Campo_Nombre.getText().substring(0,1).toUpperCase() + Campo_Nombre.getText().substring(1).toLowerCase();
                String nuevoCelular = Campo_Celular.getText();

                archivo.Verificar_Dato(nombre, "Eliminar");  // Verifica si el nombre existe
                boolean Error = archivo.getError();

                if (Error) {
                    JOptionPane.showMessageDialog(null, "No se encontró el contacto", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    archivo.Editar_Dato(nombre, nuevoCelular);
                    JOptionPane.showMessageDialog(null, "Datos actualizados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (evento.getSource() == Leer) {
            if (Campo_Nombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar el campo de nombre", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String nombre = Campo_Nombre.getText().substring(0,1).toUpperCase() + Campo_Nombre.getText().substring(1).toLowerCase();
                String celular = archivo.Obtener_Celular(nombre);

                if (celular != null) {
                    Campo_Celular.setText(celular);  // Si encuentra el nombre, llena el campo celular
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el contacto", "Error", JOptionPane.ERROR_MESSAGE);
                    Campo_Celular.setText("");  // Limpia el campo celular si no se encuentra el nombre
                }
            }
        }
    }

    private String getString() {
        String Nombres, Apellidos, Celular, Oficio, Rol_Familiar, Estrato, Object;
        Nombres = Campo_Nombre.getText().substring(0,1).toUpperCase() + Campo_Nombre.getText().substring(1).toLowerCase();
        Celular = Campo_Celular.getText();
        Object = Nombres + "!" + Celular;
        return Object;
    }
}