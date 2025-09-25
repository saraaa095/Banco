package model;

public class CuentaCorriente extends Cuenta {
    private float sobregiro;

    public CuentaCorriente(float saldoInicial, float tasaAnual) {
        super(saldoInicial, tasaAnual);
        this.sobregiro = 0f;
    }

    public float getSobregiro() { return sobregiro; }
    public void setSobregiro(float sobregiro) { this.sobregiro = sobregiro; }

    @Override
    public void retirar(float cantidad) {
        if (cantidad <= getSaldo()) {
            setSaldo(getSaldo() - cantidad);
        } else {
            float diferencia = cantidad - getSaldo();
            setSobregiro(getSobregiro() + diferencia);
            setSaldo(0f);
        }
        setNumRetiros(getNumRetiros() + 1);
    }

    @Override
    public void consignar(float cantidad) {
        if (cantidad <= 0) return;

        if (getSobregiro() > 0f) {
            if (cantidad >= getSobregiro()) {
                float resto = cantidad - getSobregiro();
                setSobregiro(0f);
                if (resto > 0f) {
                    super.consignar(resto); // esto incrementa numConsignaciones
                } else {
                    // si resto == 0 no incrementamos por segunda vez
                    setNumConsignaciones(getNumConsignaciones() + 1);
                }
            } else {
                // solo reduce el sobregiro
                setSobregiro(getSobregiro() - cantidad);
                setNumConsignaciones(getNumConsignaciones() + 1);
            }
        } else {
            super.consignar(cantidad); // agrega al saldo e incrementa numConsignaciones
        }
    }

    @Override
    public void extractoMensual() {
        super.extractoMensual();
    }

    @Override
    public void imprimir() {
        System.out.println("=== Cuenta Corriente ===");
        System.out.println("Saldo: $" + getSaldo());
        System.out.println("Comisión mensual: $" + getComisionMensual());
        System.out.println("Número de transacciones: " + (getNumConsignaciones() + getNumRetiros()));
        System.out.println("Sobregiro: $" + getSobregiro());
    }
}

