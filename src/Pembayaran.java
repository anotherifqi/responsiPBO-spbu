// Kontrak pembayaran untuk seluruh transaksi bahan bakar di SPBU.

public interface Pembayaran {
    public void bayar(int liter, double nominal);
    public double hitungKembalian(int liter, double nominal);
}
