import java.util.*;

public class Main {
    static abstract class SinhVien {
        protected String maSV, maHe;
        protected int soTinChi;

        public SinhVien(String maSV, String maHe, int soTinChi) {
            this.maSV = maSV;
            this.maHe = maHe;
            this.soTinChi = soTinChi;
        }

        public abstract long hocPhi();

        @Override
        public String toString() {
            return maSV + " " + maHe + " " + hocPhi();
        }
    }

    static class SinhVienCQ extends SinhVien {
        public SinhVienCQ(String maSV, int soTinChi) {
            super(maSV, "CQ", soTinChi);
        }

        @Override
        public long hocPhi() {
            return soTinChi * 450000L;
        }
    }

    static class SinhVienCLC extends SinhVien {
        public SinhVienCLC(String maSV, int soTinChi) {
            super(maSV, "CLC", soTinChi);
        }

        @Override
        public long hocPhi() {
            return soTinChi * 675000L;
        }
    }

    static class SinhVienLT extends SinhVien {
        public SinhVienLT(String maSV, int soTinChi) {
            super(maSV, "LT", soTinChi);
        }

        @Override
        public long hocPhi() {
            return soTinChi * 450000L + 2000000L;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String he = p[0], ma = p[1];
            int tc = Integer.parseInt(p[2]);
            if (he.equals("CQ")) ds.add(new SinhVienCQ(ma, tc));
            else if (he.equals("CLC")) ds.add(new SinhVienCLC(ma, tc));
            else if (he.equals("LT")) ds.add(new SinhVienLT(ma, tc));
        }
        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
        sc.close();
    }
}