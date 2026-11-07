// Kelas dasar untuk seluruh unit pompa bahan bakar di SPBU.

public abstract class PompaBahanBakar {
    public static final String NAMA_SPBU = "SPBU 34.123.45";
    public static final double HARGA_PERTALITE = 10000;
    public static final double HARGA_SOLAR = 6800;
    public static final int STOK_MAKSIMAL = 8000;

    // Identitas unit pompa yang tidak berubah selama masa operasional
    private final String kodePompa;
    private final String jenisBahanBakar;

    // Data operasional pompa yang dapat diperbarui
    private double hargaPerLiter;
    private int stokLiter;
    private int literTerjual;

    // Konfigurasi dasar pompa tanpa stok awal
    public PompaBahanBakar(String kodePompa, String jenisBahanBakar) {
        this(kodePompa, jenisBahanBakar, 0, 0);
    }

    // Konfigurasi lengkap pompa dengan harga dan stok awal
    public PompaBahanBakar(String kodePompa, String jenisBahanBakar, double hargaPerLiter, int stokLiter) {
        this.kodePompa = kodePompa;
        this.jenisBahanBakar = jenisBahanBakar;
        this.hargaPerLiter = hargaPerLiter;
        this.stokLiter = stokLiter;
        this.literTerjual = 0;
    }

    public String getKodePompa() {
        return kodePompa;
    }

    public String getJenisBahanBakar() {
        return jenisBahanBakar;
    }

    public double getHargaPerLiter() {
        return hargaPerLiter;
    }

    // Memperbarui harga bahan bakar sesuai kebijakan SPBU
    public void setHargaPerLiter(double hargaPerLiter) {
        this.hargaPerLiter = hargaPerLiter;
    }

    public int getStokLiter() {
        return stokLiter;
    }

    public void setStokLiter(int stokLiter) {
        this.stokLiter = stokLiter;
    }

    public int getLiterTerjual() {
        return literTerjual;
    }

    public void setLiterTerjual(int literTerjual) {
        this.literTerjual = literTerjual;
    }

    // Menghitung total pembayaran berdasarkan volume bahan bakar
    public double hitungTotal(int liter) {
        return liter * hargaPerLiter;
    }

    // Menambah persediaan bahan bakar selama kapasitas tangki mencukupi
    public void isiStok(int liter) {
        if (stokLiter + liter <= STOK_MAKSIMAL) {
            stokLiter = stokLiter + liter;
            System.out.println("[Sistem] Stok " + jenisBahanBakar + " diperbarui menjadi " + stokLiter + " liter.");
        } else {
            System.out.println("[Sistem] Penambahan stok " + jenisBahanBakar + " melebihi kapasitas tangki.");
        }
    }

    // Menampilkan ringkasan kondisi operasional pompa
    public void tampilkanInfo() {
        System.out.println("[Pompa " + kodePompa + "] SPBU: " + NAMA_SPBU);
        System.out.println("[Pompa " + kodePompa + "] Jenis bahan bakar: " + jenisBahanBakar);
        System.out.println("[Pompa " + kodePompa + "] Harga per liter: Rp " + hargaPerLiter);
        System.out.println("[Pompa " + kodePompa + "] Stok tersedia: " + stokLiter + " liter");
        System.out.println("[Pompa " + kodePompa + "] Total terjual: " + literTerjual + " liter");
    }

    // Setiap unit pompa wajib memiliki mekanisme penyaluran bahan bakar
    public abstract void salurkan(int liter);
}
