package app;

import model.CuentaAhorros;
import model.CuentaCorriente;

public class aplicacion {
    public static void main(String[] args) {
        // Prueba de CuentaAhorros
        CuentaAhorros ca = new CuentaAhorros(12000f, 12f); // saldo 12.000, tasa 12%
        ca.consignar(2000f);
        ca.retirar(3000f);
        ca.retirar(2000f);
        ca.retirar(1000f);
        ca.retirar(500f);
        ca.retirar(500f);
        ca.extractoMensual();
        ca.imprimir();

        System.out.println("---------------");

        // Prueba de  CuentaCorriente
        CuentaCorriente cc = new CuentaCorriente(5000f, 10f);
        cc.retirar(6000f); // genera sobregiro 1000
        cc.consignar(600f); // reduce sobregiro a 400
        cc.consignar(500f); // cubre sobregiro y agrega resto al saldo
        cc.extractoMensual();
        cc.imprimir();
    }
}


