// Pompa Pertalite dengan layanan pembayaran dan loyalitas pelanggan.

public class PompaPertalite extends PompaBahanBakar implements Pembayaran, LayananDigital {
    // Akumulasi poin loyalitas pelanggan
    private int poin;

    // Konfigurasi pompa Pertalite tanpa stok awal
    public PompaPertalite(String kodePompa) {
        super(kodePompa, "Pertalite", HARGA_PERTALITE, 0);
    }

    // Konfigurasi pompa Pertalite dengan stok awal tertentu
    public PompaPertalite(String kodePompa, int stokAwal) {
        super(kodePompa, "Pertalite", HARGA_PERTALITE, stokAwal);
    }

    // Memvalidasi stok sebelum menyalurkan Pertalite
    @Override
    public void salurkan(int liter) {
        if (liter > 0 && getStokLiter() >= liter) {
            setStokLiter(getStokLiter() - liter);
            setLiterTerjual(getLiterTerjual() + liter);
            System.out.println("[Pompa " + getKodePompa() + "] Pertalite tersalurkan sebanyak " + liter + " liter.");
        } else {
            System.out.println("[Pompa " + getKodePompa() + "] Stok Pertalite tidak mencukupi.");
        }
    }

    // Menampilkan informasi pompa beserta poin pelanggan
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("[Pompa " + getKodePompa() + "] Poin pelanggan: " + poin);
    }

    // Memproses pembayaran dan menambahkan poin pelanggan
    @Override
    public void bayar(int liter, double nominal) {
        double total = hitungTotal(liter);

        if (nominal >= total && getStokLiter() >= liter && liter > 0) {
            salurkan(liter);
            poin = poin + (int) (total / 10000);
            System.out.println("[Kasir] Pembayaran Pertalite berhasil. Total: Rp " + total);
        } else {
            System.out.println("[Kasir] Pembayaran Pertalite gagal. Nominal atau stok tidak mencukupi.");
        }
    }

    // Menghitung kembalian pembayaran pelanggan
    @Override
    public double hitungKembalian(int liter, double nominal) {
        if (nominal >= hitungTotal(liter)) {
            return nominal - hitungTotal(liter);
        } else {
            return 0;
        }
    }

    // Mencetak struk digital untuk pelanggan
    @Override
    public void cetakStruk() {
        System.out.println("===== STRUK DIGITAL PERTALITE =====");
        System.out.println("SPBU: " + NAMA_SPBU);
        System.out.println("Pompa: " + getKodePompa());
        System.out.println("Bahan bakar: " + getJenisBahanBakar());
        System.out.println("Harga per liter: Rp " + getHargaPerLiter());
        System.out.println("Volume terjual: " + getLiterTerjual() + " liter");
        System.out.println("Total transaksi: Rp " + hitungTotal(getLiterTerjual()));
        System.out.println("Poin pelanggan: " + poin);
    }

    // Mengambil saldo poin pelanggan
    @Override
    public int getPoin() {
        return poin;
    }
}
