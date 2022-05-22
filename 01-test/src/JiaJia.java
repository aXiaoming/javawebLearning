public class JiaJia {

    public static void main(String[] args) {

        // 无论老师在这追着你多少遍，你自己不想动，就永远为0
        int a = 0;
        for (int i = 1; i <= 3; i++) {
            a = a++;
        }
        a = a++;
        System.out.println(a);


    }
}
