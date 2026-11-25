import java.util.*;

public class Main {
    interface MienGiam {
        long tinhGiam(long hocPhi);
    }

    static class ChinhSachCS implements MienGiam {
        @Override
        public long tinhGiam(long hocPhi) {
            return hocPhi * 50L / 100;
        }
    }

    static class ChinhSachHN implements MienGiam {
        @Override
        public long tinhGiam(long hocPhi) {
            return hocPhi * 70L / 100;
        }
    }

    static class ChinhSachHB implements MienGiam {
        @Override
        public long tinhGiam(long hocPhi) {
            return Math.min(hocPhi, 3000000L);
        }
    }

    static abstract class SinhVien {
        protected String maSV, maHe;
        protected int soTinChi;
        protected MienGiam mienGiam;

        public SinhVien(String maSV, String maHe, int soTinChi) {
            this.maSV = maSV;
            this.maHe = maHe;
            this.soTinChi = soTinChi;
        }

        public void setMienGiam(MienGiam mienGiam) {
            this.mienGiam = mienGiam;
        }

        public abstract long hocPhi();

        public long soTienGiam() {
            return (mienGiam == null) ? 0 : mienGiam.tinhGiam(hocPhi());
        }

        public long phaiNop() {
            return hocPhi() - soTienGiam();
        }

        @Override
        public String toString() {
            return maSV + " " + maHe + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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
        Map<String, SinhVien> mapSV = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String he = p[0], ma = p[1];
            int tc = Integer.parseInt(p[2]);
            SinhVien sv = null;
            if (he.equals("CQ")) sv = new SinhVienCQ(ma, tc);
            else if (he.equals("CLC")) sv = new SinhVienCLC(ma, tc);
            else if (he.equals("LT")) sv = new SinhVienLT(ma, tc);
            ds.add(sv);
            mapSV.put(ma, sv);
        }

        int m = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < m; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String ma = p[0], cs = p[1];
            SinhVien sv = mapSV.get(ma);
            if (sv == null) {
                System.out.println("NOT FOUND " + ma);
            } else {
                if (cs.equals("CS")) sv.setMienGiam(new ChinhSachCS());
                else if (cs.equals("HN")) sv.setMienGiam(new ChinhSachHN());
                else if (cs.equals("HB")) sv.setMienGiam(new ChinhSachHB());
            }
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }

        String[] cacHe = {"CQ", "CLC", "LT"};
        for (String he : cacHe) {
            int count = 0;
            long tongNop = 0;
            for (SinhVien sv : ds) {
                if (sv.maHe.equals(he)) {
                    count++;
                    tongNop += sv.phaiNop();
                }
            }
            if (count > 0) {
                System.out.println(he + " " + count + " " + tongNop);
            }
        }
        sc.close();
    }
}