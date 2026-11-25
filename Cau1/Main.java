import java.util.*;

public class Main {
    static class SinhVien {
        private String maSV, hoTen, ngaySinh;

        public SinhVien(String maSV, String hoTen, String ngaySinh) {
            this.maSV = maSV.trim();
            this.hoTen = chuanHoaTen(hoTen);
            this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
        }

        private String chuanHoaTen(String s) {
            String[] words = s.trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String w : words) {
                if (!w.isEmpty()) {
                    sb.append(Character.toUpperCase(w.charAt(0)))
                      .append(w.substring(1).toLowerCase())
                      .append(" ");
                }
            }
            return sb.toString().trim();
        }

        private String chuanHoaNgaySinh(String s) {
            String[] p = s.trim().split("/");
            int d = Integer.parseInt(p[0]);
            int m = Integer.parseInt(p[1]);
            return String.format("%02d/%02d/%s", d, m, p[2]);
        }

        @Override
        public String toString() {
            return maSV + " " + hoTen + " " + ngaySinh;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String ns = sc.nextLine();
            ds.add(new SinhVien(ma, ten, ns));
        }
        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
        sc.close();
    }
}