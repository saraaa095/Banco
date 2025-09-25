package model;

public class CuentaAhorros extends Cuenta {
    private boolean activa;

    public CuentaAhorros(float saldoInicial, float tasaAnual) {
        super(saldoInicial, tasaAnual);
        verificarEstado();
    }

    private void verificarEstado() {
        this.activa = getSaldo() >= 10000f;
    }

    public boolean isActiva() {
        return activa;
    }

    @Override
    public void consignar(float cantidad) {
        if (isActiva()) {
            super.consignar(cantidad);
            verificarEstado();
        } else {
            System.out.println("Cuenta inactiva. No se puede consignar.");
        }
    }

    @Override
    public void retirar(float cantidad) {
        if (isActiva()) {
            super.retirar(cantidad);
            verificarEstado();
        } else {
            System.out.println("Cuenta inactiva. No se puede retirar.");
        }
    }

    @Override
    public void extractoMensual() {
        // Si retiros > 4, cobrar 1000 por cada retiro adicional
        if (getNumRetiros() > 4) {
            int retirosExtra = getNumRetiros() - 4;
            setComisionMensual(retirosExtra * 1000f);
        } else {
            setComisionMensual(0f);
        }

        super.extractoMensual(); // resta la comisión y aplica interés
        verificarEstado(); // actualizar estado según nuevo saldo
    }

    @Override
    public void imprimir() {
        System.out.println("=== Cuenta de Ahorros ===");
        System.out.println("Saldo: $" + getSaldo());
        System.out.println("Comisión mensual: $" + getComisionMensual());
        System.out.println("Número de transacciones: " + (getNumConsignaciones() + getNumRetiros()));
        System.out.println("Estado: " + (isActiva() ? "Activa" : "Inactiva"));
    }
}

