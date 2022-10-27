public class Test {
    public static void main(String[] args){
//        String s1 = "hello world!";
//        String s2 = "Hello World!";
//        char s = 'a';
//        System.out.println(s1.equals(s2));
//        System.out.println(s1.equalsIgnoreCase(s2));//忽略大小写
//        System.out.println(s1.compareTo(s2));
//        System.out.println(s1.compareToIgnoreCase(s2)); //相同为0 大于为>0 小于为<0
//        System.out.println(s1.startsWith("hello"));
//        System.out.println(s1.endsWith("!"));
//        System.out.println(s1.contains("hello"));
//        System.out.println(s1.substring(2,5));//返回期间字串
//        System.out.println(s1.indexOf('h'));  //找不到就返回-1
//        System.out.println(s1.indexOf('h',1)); //从1角标之后开始找
//        System.out.println(s1.indexOf("world")); //子串第一次出现的位置
//        System.out.println(s1.indexOf("world",7));
//        System.out.println(s1.lastIndexOf("l"));
//        System.out.println(String.valueOf(s));//把字符转化成字符串
        double a = 1.0;
        System.out.println(String.valueOf(a));
        char [] a = new char[10];
        for(int i=0;i<a.length;i++){
            a[i]='a';
        }
        StringBuilder s1 =new StringBuilder("good good study day day up");
        StringBuilder s2 =new StringBuilder("funk study");
        String s3="xixixi";
//        s1.append(a);//在字符串后可以加一个字符数组，string是固定的无法增加的
//        s1.append(a,2,2);  //从第二个开始 往下两个
//        s1.append(s2);//后面接一个字符串
//        s1.deleteCharAt(3);//删除下标为3的字符
//        s1.delete(2,5);//删除下标2-5
//        s1.insert(0,a,2,6);//从s1的第0个位置开始，插入字符数组a，从a的下标2开始六个字符
        s1.replace(1,8,s3);
        System.out.println(s1);
    }
}
