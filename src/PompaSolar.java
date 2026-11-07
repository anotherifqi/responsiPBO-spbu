// Pompa Solar untuk kendaraan diesel dan kebutuhan industri.

public class PompaSolar extends PompaBahanBakar implements Pembayaran {
    // Konfigurasi pompa Solar tanpa stok awal
    public PompaSolar(String kodePompa) {
        super(kodePompa, "Solar", HARGA_SOLAR, 0);
    }

    // Konfigurasi pompa Solar dengan stok awal tertentu
    public PompaSolar(String kodePompa, int stokAwal) {
        super(kodePompa, "Solar", HARGA_SOLAR, stokAwal);
    }

    // Memvalidasi stok sebelum menyalurkan Solar
    @Override
    public void salurkan(int liter) {
        if (liter > 0 && getStokLiter() >= liter) {
            setStokLiter(getStokLiter() - liter);
            setLiterTerjual(getLiterTerjual() + liter);
            System.out.println("[Pompa " + getKodePompa() + "] Solar tersalurkan sebanyak " + liter + " liter.");
        } else {
            System.out.println("[Pompa " + getKodePompa() + "] Stok Solar tidak mencukupi.");
        }
    }

    // Menampilkan informasi pompa dan segmen layanan
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("[Pompa " + getKodePompa() + "] Layanan: kendaraan diesel dan industri.");
    }

    // Memproses pembayaran transaksi Solar
    @Override
    public void bayar(int liter, double nominal) {
        double total = hitungTotal(liter);

        if (nominal >= total && getStokLiter() >= liter && liter > 0) {
            salurkan(liter);
            System.out.println("[Kasir] Pembayaran Solar berhasil. Total: Rp " + total);
        } else {
            System.out.println("[Kasir] Pembayaran Solar gagal. Nominal atau stok tidak mencukupi.");
        }
    }

    // Menghitung kembalian transaksi Solar
    @Override
    public double hitungKembalian(int liter, double nominal) {
        if (nominal >= hitungTotal(liter)) {
            return nominal - hitungTotal(liter);
        } else {
            return 0;
        }
    }
}
