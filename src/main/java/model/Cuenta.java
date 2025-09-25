package model;

public class Cuenta {

    protected float saldo;
    protected int numConsignaciones;
    protected int numRetiros;
    protected float tasaAnual; // porcentaje, e.g. 12f significa 12%
    protected float comisionMensual;

    // constructor
    public Cuenta(float saldoInicial, float tasaAnual) {
        this.saldo = saldoInicial;
        this.tasaAnual = tasaAnual;
        this.numConsignaciones = 0;
        this.numRetiros = 0;
        this.comisionMensual = 0f;
    }

    // GETTERS y SETTERS
    public float getSaldo() { return saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }

    public int getNumConsignaciones() { return numConsignaciones; }
    public void setNumConsignaciones(int numConsignaciones) { this.numConsignaciones = numConsignaciones; }

    public int getNumRetiros() { return numRetiros; }
    public void setNumRetiros(int numRetiros) { this.numRetiros = numRetiros; }

    public float getTasaAnual() { return tasaAnual; }
    public void setTasaAnual(float tasaAnual) { this.tasaAnual = tasaAnual; }

    public float getComisionMensual() { return comisionMensual; }
    public void setComisionMensual(float comisionMensual) { this.comisionMensual = comisionMensual; }

    // Métodos
    public void consignar(float cantidad) {
        if (cantidad > 0) {
            setSaldo(getSaldo() + cantidad);
            setNumConsignaciones(getNumConsignaciones() + 1);
        }
    }

    public void retirar(float cantidad) {
        if (cantidad > 0 && cantidad <= getSaldo()) {
            setSaldo(getSaldo() - cantidad);
            setNumRetiros(getNumRetiros() + 1);
        } else {
            System.out.println("Fondos insuficientes para retirar $" + cantidad);
        }
    }

    // tasaAnual es porcentaje -> dividir por 100 y por 12 meses
    public void calcularInteresMensual() {
        float interesMensual = (getTasaAnual() / 12f) / 100f * getSaldo();
        setSaldo(getSaldo() + interesMensual);
    }

    // resta la comision mensual y aplica interés
    public void extractoMensual() {
        setSaldo(getSaldo() - getComisionMensual());
        calcularInteresMensual();
        // normalmente la comisión se calcula en la clase hija o se reinicia al final del mes:
        setComisionMensual(0f); // reiniciar para el siguiente mes
    }

    public void imprimir() {
        System.out.println("=== Cuenta ===");
        System.out.println("Saldo: $" + getSaldo());
        System.out.println("Consignaciones: " + getNumConsignaciones());
        System.out.println("Retiros: " + getNumRetiros());
        System.out.println("Tasa anual: " + getTasaAnual() + "%");
        System.out.println("Comisión mensual: $" + getComisionMensual());
    }
}
