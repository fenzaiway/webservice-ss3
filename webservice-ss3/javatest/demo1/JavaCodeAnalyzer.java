package demo1;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class JavaCodeAnalyzer {
    public  static void  main(String[] args) {
        String filename = JOptionPane.showInputDialog(null,"请输入文件路径：");
        if(filename.endsWith("a")){
            CountFile(filename);
        }
        else
            System.out.println("文本错误！不是JAVA文件！");
    }


    public  static void CountFile(String filename) {
        String str;
        try {
            str = fromFile(filename);
            count(str);
            CountKeyWord(str);
        }
        catch (FileNotFoundException e) {
            System.out.print("该文件不存在或不可访问");
            System.exit(0);
        } 
        catch (IOException e) {
            System.out.print("失败的或中断的 I/O 操作");
             System.exit(0);
        }
    }
 
    public static void count(String str) {
        String strTemp;
        double codeNumber=0;
        double remarkNumber=0;
        double emptyNumber=0;
        double total;
        if (str.length()>0) {
            while (str.indexOf('\n')!=-1) {
                strTemp = str.substring(0, str.indexOf('\n')).trim();
                str = str.substring(str.indexOf('\n') + 1, str.length());
   
                if (strTemp.length()>0&&(strTemp.charAt(0)=='*'||strTemp.charAt(0)=='/')) {
                    remarkNumber++;
                } 
                else if(strTemp.length()>0&&((strTemp.charAt(0)>'A'&&strTemp.charAt(0)<'Z')||(strTemp.charAt(0)=='=')||(strTemp.charAt(0)>'a'&&strTemp.charAt(0)<'z')||(strTemp.charAt(0)=='}')||(strTemp.charAt(0)=='{')||(strTemp.charAt(0)=='@')||(strTemp.charAt(0)=='(')||(strTemp.charAt(0)=='@'))){
                    codeNumber++;
                }
                else {
                     emptyNumber++;
                }
            }
        }
        total=remarkNumber+codeNumber+emptyNumber;
        double r=remarkNumber/total;
        double c=codeNumber/total;
        double e=emptyNumber/total;
        System.out.println( "代码行数：" + codeNumber + "行"+" "+"占"+c*100+"%");
        System.out.println( "注释行数：" + remarkNumber + "行"+" "+"占"+r*100+"%");
        System.out.println( "空行行数：" + emptyNumber + "行"+" "+"占"+e*100+"%");
    }

    private static String fromFile(String filename) throws FileNotFoundException,IOException {
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[(int) file.length()];
        fis.read(b);
        fis.close();
  
        return new String(b);
    }
    
    private static void CountKeyWord(String content1){
        String[] keyWordString = {"abstract", "assert", " boolean", "break", "byte", "case", "catch", "char",
            "class", "continue", "default", "do", "double", "else", "enum", "estends", "false", "final", "finally", "float",
            "for", "if", "implements", "import", "instanceof", "int", "intrface", "long", "native", "new", "null", "package", "private",
            "protected", "public", "return", "short", "static", "super", "switch", "synchronized", "this", "throw", "throws", "translent",
            "try", "true", "vold", "volatile", "while"};

        int[] keywords=new int[keyWordString.length];
        String commentRegex1 = "/\\*(.*?)\\*/";
        Pattern pattern1 = Pattern.compile(commentRegex1, Pattern.DOTALL);
        Matcher matcher1 = pattern1.matcher(content1);
        content1 = matcher1.replaceAll("");


        String commentRegex2 = "//(.*)";
        Pattern pattern2 = Pattern.compile(commentRegex2);
        Matcher matcher2 = pattern2.matcher(content1);
        content1 = matcher2.replaceAll("");

        content1 = content1.replace("\".*\"", "\"\"");//将所有字符串直接量的内容置空
         int i;
        for ( i = 0; i < keyWordString.length; i++) {

            Keyword keyword = new Keyword(keyWordString[i], 0);
            int fromindex = 0;
            int index;
            String regex = "[\\W]" + keyword.getKeyword() + "[\\W]";
            while ((index = content1.indexOf(keyword.getKeyword(), fromindex)) > 0) {
                String s;
                if (index > 0) {
                    s = content1.substring(index - 1, index + keyword.getKeyword().length() + 1);
                } else {
                    s = "" + content1.substring(index, index + keyword.getKeyword().length() + 1);

                }
                if (s.matches(regex)) {

                    keyword.increase();
                }
                fromindex = index + 1;
            }
            keywords[i] = keyword.getAmount();
        }
        int[] t=new int[keyWordString.length];
        for(i=0;i<keyWordString.length;i++)
            t[i]=keywords[i];
        for( i=0;i<keyWordString.length-1;i++)
            for(int j=0;j<keyWordString.length-i-1;j++){
               if(t[j]<t[j+1]){
                    int temp=t[j];
                    t[j]=t[j+1];
                    t[j+1]=temp;
                }
            }
        for(i=0;i<keyWordString.length;i++){
            if(keywords[i]==t[0])
                System.out.println(keyWordString[i]+":"+keywords[i]);
        }
        for(i=0;i<keyWordString.length;i++){
            if(keywords[i]==t[1])
                System.out.println(keyWordString[i]+":"+keywords[i]);
        }
        for(i=0;i<keyWordString.length;i++){
            if(keywords[i]==t[2])
                System.out.println(keyWordString[i]+":"+keywords[i]);
        }
        for(i=0;i<keyWordString.length;i++){
            if(keywords[i]==t[3])
                System.out.println(keyWordString[i]+":"+keywords[i]);
        }
        for(i=0;i<keyWordString.length;i++){
            if(keywords[i]==t[4])
                System.out.println(keyWordString[i]+":"+keywords[i]);
        }
    }
}