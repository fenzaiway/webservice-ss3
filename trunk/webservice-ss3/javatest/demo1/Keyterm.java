package demo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;

public class Keyterm  {
    int threshold=4;
//	List<String> ht_r=new ArrayList<String>();
//	List<String> ht_n=new ArrayList<String>();
	int hr=0;
	double seed=50;
	static Hashtable refer_ht=new Hashtable();
	
   

	//FileInputStream fr;
public void calPrw() throws IOException{
	
    String t1="";
    String[][] s=new String[1000000][2];
	int index=0;
		InputStream fr=Keyterm.class.getClassLoader().getResourceAsStream("all_prw.txt");
    	InputStreamReader ri=new InputStreamReader(fr);
	    BufferedReader bf=new BufferedReader(ri);
			try {
				while((t1=bf.readLine())!=null){
					s[index]=t1.split(" ");
					index++;
				}
				bf.close();
				ri.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		for(int i=0;i<s.length;i++){
			if((s[i][0]!=null)&&(s[i][1]!=null)){
				//System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhh"+s[i][0]+","+s[i][1]);
				refer_ht.put(s[i][0], s[i][1]);
			}
		}
		
	
	/////////////////////////////
	System.out.println("htt是：："+refer_ht);
}

public Hashtable getSeeds(Hashtable refer_ht,Hashtable ht){
	    // System.out.println("getSeeds...");
		Hashtable ht1=new Hashtable();
		
		//遍历哈希表中的key，选出这一篇文章的seeds
		for(Object i1:ht.keySet()){
		 //System.out.println(i);
		 for(Object j:refer_ht.keySet()){
			 if(i1.equals(j)){
				 //类型转换函数将object类型的key值转换为double类型
				double d1=Double.parseDouble(ht.get(i1).toString());
				double d=Double.parseDouble(refer_ht.get(j).toString());
				 double s=d1/d;
				// double s=Math.log(s0);
				 if(s>seed)//大于特定阈值的存储起来，选为种子
				 {
				 DecimalFormat df1=new DecimalFormat("##.####");
				 String s1=df1.format(s);
				 ht1.put(i1, s1);
				 }
			 }
		 }
	}
		System.out.println("seeds为***"+ht1);
		return ht1;
		
	}

/* 获取包含种子i的所有字串
 * i为一个种子，txt为待处理的doc所合并成的字符串
 * 所得结果存在一个字符串数组中
 */
public Hashtable getAllSubs(int threshold, String i,String txt){
	//System.out.println("getAllSubs...");
    Hashtable ht_all_subterms=new Hashtable();
	String result="";
	int k=0;
	Keyterm ck=new Keyterm();
	//System.out.println("种子为-------------------------------------------------------------"+i);
	String regex="([\\u4e00-\\u9fa5]|[A-Za-z]){1,8}"+i+"([\\u4e00-\\u9fa5]|[A-Za-z]){1,8}";
	Pattern p = Pattern.compile(regex);
	Matcher m = p.matcher(txt);
	while(m.find())
	{ 
		result=m.group();
		if(result.length()<=15)//这里输出的是能匹配上的最长字符串
		{
			k=result.indexOf(i.toString());
			for(int left=0;left<k;left++)
			  {
			   for(int right=k+1;right<(result.length()+1);right++)
			    {
				 String rsult_ss=result.substring(left, right);//result包含种子i的所有字串
				 int c1=ck.getCount(txt, rsult_ss);//每个字串在d中出现的频率
				 if(c1>threshold)
				 ht_all_subterms.put(rsult_ss, c1);//找出包含种子i的所有子串	
			    }
			  }
	}

  }
	return ht_all_subterms;
}
/*
 * 将result_tmp中满足频率threshold的最长的串找出来，并且若其子串频率减去最长串频率还是大于threshold,将最长字串找出，结果存入ht_result
 * result_tmp是一个种子一个，ht_result是一篇文章一个
 */
public void getTerms(int threshold,Hashtable ht_all_subterms,Hashtable ht_result){
	Keyterm ck=new Keyterm();
    String[] contined_sub=new String[100];
    int cs=0,j=0;
    String[] result_tmp=new String[100000];
	List<String> result_tmplist=Arrays.asList(result_tmp);
	int rt=0;
	
	for(Object has:ht_all_subterms.keySet()){
		  result_tmp[rt]=has.toString();
	      rt++;
	
}
	
	if(result_tmp!=null){
	
	String Max=ck.getMaxlength(result_tmp);
	if((Max!=null)&&(Max.length()!=0))
	{

	ht_result.put(Max, ht_all_subterms.get(Max));//获取包含种子i的最长字符串
	
	}
	for(j=0;j<result_tmp.length;j++){
		if((result_tmp[j]!=null)&&(result_tmp[j]!=Max)){
		   int c1=(new Integer(ht_all_subterms.get(Max).toString())).intValue();
		    int c2=(new Integer(ht_all_subterms.get(result_tmp[j]).toString())).intValue();
			if((result_tmp[j].length()==Max.length())){
				ht_result.put(result_tmp[j],c2);//存入包含种子i的长度和Max一样的字符串
		
			}
			else if(Max.contains(result_tmp[j])) //找出包含种子i的最长字符串的子串
			{
				int c3=c2-c1;
				if(c3>threshold){
					contined_sub[cs]=result_tmp[j];
					cs++;
				}
			}
			}
		}

	if(contined_sub!=null)//只要满足频率的最长的子串
	{
	String MaxSub=ck.getMaxlength(contined_sub);
	if(MaxSub!=null){
		int c=(new Integer(ht_all_subterms.get(MaxSub).toString())).intValue();
		ht_result.put(MaxSub, c);
	}
	}
	}

}
/*
 * 将每篇文章的ht_result中的结果存到一个数组ht_r中，ht_n存相应的频率
 * 
 * */
public  void getAllTerms(Hashtable ht_result,List<String> ht_r,List<String> ht_n ){
	//System.out.println();synchronized
	//System.out.println("getAllTerms...");
	//List<String> ht_rlist=Arrays.asList(ht_r);
	for(Object key1:ht_result.keySet()){
		if(key1!=null)
		{
			if(!ht_r.contains(key1))//如果list里面不包含这个词
			{
				ht_r.add(key1.toString());
				ht_n.add(ht_result.get(key1).toString());
			//ht_r[hr]=key1.toString();
			//ht_n[hr]=(new Integer(ht_result.get(ht_r[hr]).toString())).intValue();
			//System.out.println(ht_r[hr]+"***"+ht_n[hr]+"***"+hr);
		//	hr++;
			}
			else//如果包含这个词
			{
				int loc=ht_r.indexOf(key1);
				if(loc!=-1){
			    int old=Integer.parseInt(ht_n.get(loc));
					//System.out.println(key1.toString()+"^^^^"+loc);
				int fresh=old+(new Integer(ht_result.get(key1).toString())).intValue();
				ht_n.get(loc).replace(ht_n.get(loc), new Integer(fresh).toString());
				}
				
			}
		}
		}
 for(int hh=0;hh<ht_r.size();hh++){
	 if(ht_r.get(hh)!=null)
	 {
		System.out.println("docManage中getAllTerms方法运行完后的结果为ht_r[j]"+ht_r.get(hh));
		//all.put(ht_r[hr], ht_n[hr]);
		
	 }
 }
	
}
public void docManage(String content,int threshold,List<String> ht_r,List<String> ht_n ){
	
	Hashtable ht=readDoc(content);
	System.out.println("ht::"+ht);
    //获得种子
    Hashtable ht1=new Hashtable();
    ht1=getSeeds(refer_ht, ht);
    //获取候选的terms
    int j=0,h=1,q=1,n=0;
    Hashtable ht_result=new Hashtable();
    String txt=content;	
    /////////////////////////////////////////////////
    for(Object i1:ht1.keySet())//对每一个种子
    {   
    	Hashtable ht_all_subterms=getAllSubs(threshold, i1.toString(), txt);//获得包含种子i的所有子串
	    getTerms(threshold, ht_all_subterms, ht_result);//获取包含种子i的关键词
	
    }//种子循环

//一篇doc的所有种子循环完了，得到所有关键词，存在ht_result里面
    getAllTerms(ht_result,ht_r,ht_n);
    

	
}
public Hashtable action(String filepath,int threshold,List<String> ht_r,List<String> ht_n) throws IOException{
	//RecordTime t=new RecordTime();
	//t.RecordTime0();
	 int j=0;
	 String line="",s="";
	  calPrw();//获得prw
	  String regex0="[，，。！？；：”“,、 一 ,+)(*【】（）] ";
	  Hashtable fr=new Hashtable();
	 // String fl[]=FileList.getFiles(filepath);//读入指定目录下的所有文件
//	  for(j=0;j<fl.length;j++){
//      	System.out.println("这是第"+j+"个文本~~");
//      //	System.out.println(list.get(j));
//      	BufferedReader buf=new BufferedReader(new FileReader(fl[j]));
//      	 while((line=buf.readLine())!=null)
//       	 { s=s+line;}
//      	String content=s;
//    	content=content.replaceAll(regex0, "");
//    	docManage(content,threshold,ht_r,ht_n);
//      }
		
		
		System.out.println("1111111111111111111111");
//		for(Object r:fr.keySet()){
//			System.out.println(r+"-----"+fr.get(r));
//		}
	  //  result_tmp=sort(result_tmp);//按字符串长度排序
		   // System.out.println("排完序了");
		    String[] stoplist={"的","在","个","是","日","到","我"};
			   for(j=0;j<ht_r.size();j++){
					if(ht_r.get(j)!=null){
						   for(int j1=0;j1<stoplist.length;j1++){
							   String regex="("+stoplist[j1]+"([\\u4e00-\\u9fa5]+))|(([\\u4e00-\\u9fa5]+)"+stoplist[j1]+")|("+stoplist[j1]+"([\\u4e00-\\u9fa5]+)"+stoplist[j1]+")";
						   //String regex="(的(([\\u4e00-\\u9fa5]|[A-Za-z])+))|((([\\u4e00-\\u9fa5]|[A-Za-z])+)的)|(的(([\\u4e00-\\u9fa5]|[A-Za-z])+)的)";
						   Pattern p=Pattern.compile(regex);
						   Matcher m=p.matcher(ht_r.get(j));
						   while(m.find()){
							  // String result=m.group();
							    // System.out.println("result::"+result);
							   if((ht_r.get(j).charAt(ht_r.get(j).length()-1))==stoplist[j1].charAt(0)){
								   ht_r.get(j).replace(ht_r.get(j), ht_r.get(j).substring(0, ht_r.get(j).length()-1));
							   }
							   if(ht_r.get(j).charAt(0)==stoplist[j1].charAt(0)){
								   ht_r.get(j).replace(ht_r.get(j), ht_r.get(j).substring(1));
								  // ht_r.get(j)=ht_r.get(j).substring(1);
							   }
							   if(((ht_r.get(j).charAt(ht_r.get(j).length()-1))==stoplist[j1].charAt(0))&&(ht_r.get(j).charAt(0)==stoplist[j1].charAt(0))){
								   ht_r.get(j).replace(ht_r.get(j), ht_r.get(j).substring(1,ht_r.get(j).length()-1));
								 //  ht_r.get(j)=ht_r.get(j).substring(1,ht_r[j].length()-1);
							   }
							   
						   }
						//ht_n[j]=(new Integer(all.get(ht_r[j]).toString())).intValue();
					}
					}
				}
			   for(int hh=0;hh<ht_r.size();hh++){
					 if(ht_r.get(hh)!=null)
					 {
						System.out.println("去掉停用词以后的ht_r.get(j)为："+ht_r.get(hh));
						//all.put(ht_r[hr], ht_n[hr]);
						
					 }
				 }
		 
		String[] ht_final=new String[ht_r.size()];
		int[] ht_nfinal=new int[ht_r.size()];
		int hf=0;
		 //System.out.println("去重");
		 
		for(j=ht_r.size()-1;j>1;j--){
			//System.out.println(ht_r[j]+"#################"+ht_n[j]);
			if(ht_r.get(j)!=null){
				
				for(int i1=0;i1<j;i1++){
					if(ht_r.get(i1)!=null){
						if((ht_r.get(j)).equals(ht_r.get(i1))){
							//System.out.println(ht_r[i1]+"#################"+ht_n[i1]);
							(ht_r.get(j)).replace(ht_r.get(j), "");
							//ht_n[i1]+=ht_n[j];
						}
					}
				}
			}
		}
//		List<String> l=new ArrayList();
		String ht_r1[]=new String[ht_r.size()];
		for(int i=0;i<ht_r.size();i++){
			ht_r1[i]=ht_r.get(i);
		}
		String max=getMaxlength(ht_r1);
//		Hashtable fr=new Hashtable();
		   for(int i1=0;i1<ht_r1.length;i1++){
			   if((ht_r1[i1]!=null)&&(ht_r1[i1]!="")){
				   ht_final[hf]=ht_r1[i1];
				   ht_nfinal[hf]=new Integer(ht_n.get(i1)).intValue();
				  // System.out.println("ht_r[i1]+++"+ht_r[i1]);
				//  result+=ht_r[i1]+";";
				  // if((ht_final[hf].length()>2)&&(!max.contains(ht_final[hf]))){
					 //  System.out.println(ht_final[hf]+"···"+ht_nfinal[hf]);
					   fr.put(ht_final[hf], ht_nfinal[hf]);
				  // l.add(ht_final[hf]);
				 //  }
				 
			   }
		   }
		//t.dispose();
	

		return fr;
		
		  
	}


		//************************************************************8
	/**
	* 方法名称：getSortedHashtable
	* 参数：Hashtable h 引入被处理的散列表 
	* 描述：将引入的hashtable.entrySet进行排序，并返回,按value在本程序中就是按频率排序
	*/
	@SuppressWarnings("unchecked")
	public static Map.Entry[] getSortedHashtable(Hashtable h) {
		  Set set = h.entrySet();
		  Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
		  Arrays.sort(entries, new Comparator() {
		   public int compare(Object arg0, Object arg1) {
		    int key1 = Integer.parseInt(((Map.Entry) arg0).getValue().toString());
		    int key2 = Integer.parseInt(((Map.Entry) arg1).getValue().toString());
		    return ((Comparable) new Integer(key1)).compareTo(new Integer( key2)); //---当然为了降序排只要把key1和key2互换一下
		   }
		  });
		  return entries;
		 }
	//***************************************************************
	public String[] sort(String[] s){//按长度排序
		//System.out.println("进入分类函数*~*");
		String tmp="";
		int length=0,i=0,j=0;
		for(j=s.length-1;j>1;j--){
		 for(i=0;i<j;i++){
			if((s[i]!=null)&&(s[i+1]!=null)&&(s[i+1].length()>s[i].length())){
				tmp=s[i];
				s[i]=s[i+1];
				s[i+1]=tmp;
				length=s[i+1].length();
			}
		}}
//		 for(i=0;i<s.length;i++){
//			 if(s[i]!=null){
//			// System.out.println("s[i]"+s[i]);}
//		 }
		return s;
	}
	//***************************************************************
		
    //得出长度最大的字符串
		public String getMaxlength(String[] s ){	 
			int i = 0, max = 0, maxLength = 0;
		//	if(s.length!=0){
			while((i < s.length)&&(s[i]!=null)){
				
				   int len = s[i].length();
				   if(len > maxLength){
				    maxLength = len;
				    max = i;
				   }  
				   i++;
				  }
			//System.out.println("最长字符串是--"+s[max]+",长度是--"+maxLength);
			//}
			
			return s[max];
		}
		
    
	
	
	//**********************************************************************

	//***********************************************************************
	//计算词语在文本中的频率
	 public int frequency(String word, String file) {

    	 int count = 0;
      try {
       BufferedReader buf = new BufferedReader(new FileReader(file));
       String str = "";
       
       while ((str = buf.readLine()) != null) {
        int index = 0;
        while (index != -1) {
         index = str.indexOf(word);
         if (index == -1) {
          break;
         }
         str = str.substring(index + word.length(), str.length());
         count++;
        }
       }
       //System.out.println(count);
      } catch (FileNotFoundException e) {
       e.printStackTrace();
      } catch (IOException e) {
       e.printStackTrace();
      }
      return count;
     }

	
	//*************************************************************************


//***************************************************************************


public Hashtable readDoc(String content){//将指定文本文件中每个字符在该文本的概率算出来

	Keyterm rd=new Keyterm();
	String[] Character=new String[3000];
	String[] En_name=new String[3000];
	int pos=0,pos1=0;
	Hashtable ht=new Hashtable();
	Hashtable ht1=new Hashtable();
	FileReader in=null;
	BufferedReader bf=null;
	String s=null;
	String b=null;
	int n=0;
	
	List<String> CharacterList = Arrays.asList(Character);
	List<String> EnList = Arrays.asList(En_name);
	s=content;
	String regex="[【】，，。！？；：”“,、 一 ,+)(*（）]";
	s=s.replaceAll(regex, "");
	String regex1="[A-Za-z]{1,20}";
    Pattern p = Pattern.compile(regex1);
    Matcher m = p.matcher(s);
    String regex2="[\\u4e00-\\u9fa5]";
    Pattern p1 = Pattern.compile(regex2);
    Matcher m1= p1.matcher(s);
    //处理英文单词
    while(m.find()){
 	  // System.out.println("进入while循环啦！！！");
 	   String tmp=m.group();
 	   if(EnList.contains(tmp)){
 		   continue;
 	   }
 	   En_name[pos]=tmp;
 	   pos++;
 }
    for(int i=0;i<En_name.length;i++){
 	   if(En_name[i]!=null)
 	   { //System.out.println("英文单词为---"+En_name[i]);
 		   ht.put(En_name[i],(double) rd.getCount(s, En_name[i])/(double)s.length());
 		   n++;
 		//   System.out.println(n);
 		 // System.out.println("英文单词为---"+En_name[i]+"---概率为---"+rd.getCount(s, En_name[i])/s.length());
 	   }
 	  
    }
	//处理汉字
	while(m1.find()){
		String tmp1=m1.group();
		if(CharacterList.contains(tmp1)){
			continue;
		}
		Character[pos1]=tmp1;
		pos1++;
	}
	for(int j=0;j<Character.length;j++){
		if(Character[j]!=null){
			ht.put(Character[j], (double)rd.getCount(s, Character[j])/(double)s.length());
			n++;
			// System.out.println(n);
			//System.out.println("中文汉字为---"+Character[j]+"---概率为---"+(double)rd.getCount(s, Character[j])/(double)s.length());
		}
	}
	//System.out.println("readDoc里面的那个ht::"+ht);
	return ht;
}


//***********************************************************
   public String TotalLine(String dirpath) {//读入文件夹下所有文件合并为一个字符串
 	String temp="";
 	String total="";
	BufferedReader br=null;
	StringBuffer pathName=new StringBuffer(dirpath);
	File directory=new File(pathName.toString());
	File[] files=directory.listFiles();
	if(directory.isDirectory()){
		for(int t=0;t<files.length;t++){
			//System.out.println(files[t]);
			try {
				br=new BufferedReader(new FileReader(files[t]));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while((temp=br.readLine())!=null){
					total=total+temp;
					//System.out.println("合并后的文本为-------"+total);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
	String regex="[，。！？；：”“—,、 一……]";
	total=total.replaceAll(regex, "");
	//System.out.println("合并后的文本为-------"+total);
	return total;
}

//***********************************************************************
   //计算子串在给定字符串出现的次数
public int getCount(String str, String sign) {     
    // 查找某一字符串中str，特定子串sign的出现次数     
    if (str == null)     
        return 0;     
    int i = str.length();     
    str = str.replaceAll(sign, "");// 反串中的字符sign替换成""     
    return (i - str.length()) / sign.length();     
}


	
	}