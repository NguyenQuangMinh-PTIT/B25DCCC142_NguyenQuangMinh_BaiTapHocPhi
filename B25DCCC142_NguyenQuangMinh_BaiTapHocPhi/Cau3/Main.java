import java.util.*;

public class Main {
    static class DuLieuKhongHopLeException extends Exception {
        public DuLieuKhongHopLeException(String message) {
            super(message);
        }
    }

    static abstract class SinhVien implements Comparable<SinhVien> {
        protected String maSV, maHe;
        protected int soTinChi;

        public SinhVien(String maSV, String maHe, int soTinChi) {
            this.maSV = maSV;
            this.maHe = maHe;
            this.soTinChi = soTinChi;
        }

        public abstract long hocPhi();

        @Override
        public int compareTo(SinhVien o) {
            int cmp = Long.compare(o.hocPhi(), this.hocPhi());
            if (cmp != 0) return cmp;
            return this.maSV.compareTo(o.maSV);
        }

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

    static class QuanLyHocPhi {
        private ArrayList<SinhVien> danhSach = new ArrayList<>();

        public void themSinhVien(String maHe, String maSV, String strTinChi) throws DuLieuKhongHopLeException {
            if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
                throw new DuLieuKhongHopLeException("Sai ma he");
            }
            int tc;
            try {
                tc = Integer.parseInt(strTinChi);
            } catch (NumberFormatException e) {
                throw new DuLieuKhongHopLeException("Tin chi khong phai so nguyen");
            }
            if (tc < 1 || tc > 30) {
                throw new DuLieuKhongHopLeException("Tin chi ngoai pham vi");
            }
            for (SinhVien sv : danhSach) {
                if (sv.maSV.equals(maSV)) {
                    throw new DuLieuKhongHopLeException("Trung ma sinh vien");
                }
            }
            if (maHe.equals("CQ")) danhSach.add(new SinhVienCQ(maSV, tc));
            else if (maHe.equals("CLC")) danhSach.add(new SinhVienCLC(maSV, tc));
            else danhSach.add(new SinhVienLT(maSV, tc));
        }

        public void inKetQua() {
            Collections.sort(danhSach);
            long tong = 0;
            for (SinhVien sv : danhSach) {
                System.out.println(sv);
                tong += sv.hocPhi();
            }
            System.out.println("TONG " + tong);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        QuanLyHocPhi ql = new QuanLyHocPhi();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            try {
                ql.themSinhVien(p[0], p[1], p[2]);
            } catch (DuLieuKhongHopLeException e) {
                System.out.println("INVALID " + p[1]);
            }
        }
        ql.inKetQua();
        sc.close();
    }
}