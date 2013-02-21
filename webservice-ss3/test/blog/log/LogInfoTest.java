package blog.log;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.junit.Test;

import com.way.blog.util.MyFormatDate;
import com.way.blog.util.RegexPatternUtil;
import com.way.blog.zone.blog.service.impl.AlbumTypeServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTypeServiceImpl;
import com.way.blog.zone.entity.Album;
import com.way.blog.zone.entity.AlbumType;
import com.way.blog.zone.entity.LogAttachment;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogType;

import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import base.BaseTest;

public class LogInfoTest extends BaseTest {
	
	private LogInfoServiceImpl logInfoService;
	private LogTypeServiceImpl logTypeService;
	private AlbumTypeServiceImpl albumTypeServiceImpl;
	
	public void init(){
		logInfoService = (LogInfoServiceImpl) this.context.getBean("logInfoServiceImpl");
		logTypeService = (LogTypeServiceImpl) this.context.getBean("logTypeServiceImpl");
		albumTypeServiceImpl = (AlbumTypeServiceImpl) this.context.getBean("albumTypeServiceImpl");
	}
	
	@Test
	public void albumType(){
		this.init();
		AlbumType album = albumTypeServiceImpl.findById(1);
		System.out.println("albymtype==>" + album.getCoverImg());
		
	}
	
	@Test
	public void addLogInfo(){
		this.init();
		LogInfo li = new LogInfo();
		li.setLogAllowComment(1);
		li.setLogAllowVisit(1);
		li.setLogContentStatus(1);
		li.setLogIsOriginal(1);
		li.setLogPublishTime(MyFormatDate.getFullDate(new Date()));
		li.setLogTitle("这是一篇测试文章");
		li.setLogText("出处：屌丝一词起源于雷霆三巨头吧对李毅吧毅丝的恶搞称谓，后被魔兽世界吧会员用于嘲讽毅丝，意为劣等毅丝。此后李毅吧友儿童多篇讲述了自己的猥琐吊丝故事，众毅丝不挂纷纷表示男默女泪。自此，吊丝文化诞生。2012年2月，凤凰网报道专题《屌丝：一个字头的诞生》，腾讯网紧跟其后发表《屌丝：庶民的文化胜利》，此后吊丝文化被发扬光大，被社会广泛接受。");
		li.setLogToTop(2);
		LogType lt = logTypeService.findById(1);
		li.setLogType(lt);
		Set<LogInfo> lis = new HashSet<LogInfo>();
		lis.add(li);
		lt.setLogInfos(lis);
		logInfoService.save(li);
	}
	
	@Test
	public void loadLogInfo(){
		this.init();
		for(LogInfo li : logInfoService.loadAll()){
			System.out.println(li.getLogType().getLogTypeName());
			System.out.println(li.getLogText());
			List<LogComment> lcs = new ArrayList<LogComment>(li.getLogComments());
			System.out.println("评论:" + lcs.get(0).getCommentContent());
		}
	}
	
	/**
	 * 更新所有的日志删除状态为0
	 */
	@Test
	public void updateDeleteStatue(){
		this.init();
		List<LogInfo> logInfos = logInfoService.loadAll();
		for (LogInfo logInfo : logInfos) {
			logInfo.setDeleteStatue(0);
			//logInfoService.update(logInfo);
		}
		for (LogInfo logInfo : logInfos) {
			System.out.println(logInfo.getDeleteStatue());
		}
	}
	
	////测试插入日志加附件
	@Test
	public void addLogInfoAndAttachment(){
		this.init();
		LogInfo li = new LogInfo();
		li.setLogAllowComment(1);
		li.setLogAllowVisit(1);
		li.setLogContentStatus(1);
		li.setLogIsOriginal(1);
		li.setLogPublishTime(MyFormatDate.getFullDate(new Date()));
		li.setLogToTop(2);
		li.setLogTitle("测试插入日志加附件");
		li.setLogText("这是一篇测试文章，文章中包含附件，测试看看添加数据的时候是否正常");
		////实例化附件对象
		LogAttachment la = new LogAttachment();
		la.setAttachmentDownloadCount(0);
		la.setAttachmentName("图片1111");
		la.setAttachmentUploadTime(MyFormatDate.getFullDate(new Date()));
		la.setUrl("/upload/images/aa.jpg");
		///设置双向关联
		la.setLogInfo(li);
		Set<LogAttachment> las = new HashSet<LogAttachment>();
		las.add(la);
		li.setLogAttachments(las);
		logInfoService.save(li);

	}
	
	@Test
	public void keyWordTest(){
		this.init();
		LogInfo logInfo = logInfoService.findById(1);
		String text = RegexPatternUtil.Html2Text(logInfo.getLogText());
		//Lucene Document的域名
		System.out.println(text);
		String fieldName = "text";
		//检索内容
		//String text = "IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切分算法。";
		//实例化IKAnalyzer分词器
		Analyzer analyzer = new IKAnalyzer();
		Directory directory = null;
		IndexWriter iwriter = null;
		IndexReader ireader = null;
		IndexSearcher isearcher = null;
		try {
		//建立内存索引对象
		directory = new RAMDirectory();
		//配置IndexWriterConfig
		IndexWriterConfig iwConfig = new
		IndexWriterConfig(Version.LUCENE_34 , analyzer);
		iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		iwriter = new IndexWriter(directory , iwConfig);
		//写入索引
		Document doc = new Document();
		doc.add(new Field("ID", "10000", Field.Store.YES,
		Field.Index.NOT_ANALYZED));
		doc.add(new Field(fieldName, text, Field.Store.YES,
		Field.Index.ANALYZED));
		iwriter.addDocument(doc);
		iwriter.close();
		//搜索过程**********************************
		//实例化搜索器
		ireader = IndexReader.open(directory);

		isearcher = new IndexSearcher(ireader);
		String keyword = "排序";
		//使用QueryParser查询分析器构造Query对象
		QueryParser qp = new QueryParser(Version.LUCENE_34,
		fieldName, analyzer);
		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query query = qp.parse(keyword);
		//搜索相似度最高的5条记录
		TopDocs topDocs = isearcher.search(query , 5);
		System.out.println("命中：" + topDocs.totalHits);
		//输出结果
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (int i = 0; i < topDocs.totalHits; i++){
		Document targetDoc = isearcher.doc(scoreDocs[i].doc);
		System.out.println("内容：" + targetDoc.toString());
		}
		} catch (CorruptIndexException e) {
		e.printStackTrace();
		} catch (LockObtainFailedException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (ParseException e) {
		e.printStackTrace();
		} finally{
		if(ireader != null){
		try {
		ireader.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
		if(directory != null){
		try {
		directory.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
		}
	}
}
