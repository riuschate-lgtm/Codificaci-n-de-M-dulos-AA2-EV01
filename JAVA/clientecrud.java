package com.elektrasas.main;

import com.elektrasas.dao.ClienteDAO;
import com.elektrasas.model.Cliente;

public class TestClienteCRUD {

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();

        // INSERTAR
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setPrimerNombre("Juan");
        nuevoCliente.setApellidoPaterno("Perez");
        nuevoCliente.setEmail("juan@test.com");
        nuevoCliente.setPassword("123456");
        nuevoCliente.setEstado("activo");
        clienteDAO.insertarCliente(nuevoCliente);

        // LISTAR
        clienteDAO.listarClientes().forEach(c ->
                System.out.println(c.getIdCliente() + " - " + c.getEmail())
        );

        // ACTUALIZAR
        clienteDAO.actualizarEstadoCliente(1, "inactivo");

        // ELIMINAR
        // clienteDAO.eliminarCliente(5);
    }
}
