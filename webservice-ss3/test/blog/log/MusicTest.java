package blog.log;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.blog.service.impl.MusicServiceImpl;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.Music;

import base.BaseTest;

/**
 * 
 * @author fenzaiway
 * 发布音频测试
 */
public class MusicTest extends BaseTest {
	
	private MusicServiceImpl musicService;
	private BlogZoneServiceImpl blogZoneService;
	
	public void init(){
		musicService = (MusicServiceImpl) this.context.getBean("musicServiceImpl");
		blogZoneService = (BlogZoneServiceImpl)this.context.getBean("blogZoneServiceImpl");
	}
	
	////为空间发布音频
	@Test
	public void addMusic(){
		this.init();
		Music m = new Music();
		m.setMusicName("江南");
		m.setPublishTime(MyFormatDate.getFullDate(new Date()));
		m.setPlayCount(0);
		m.setSinger("林俊杰");
		m.setUrlSource("http://music.baidu.com/china/江南.mp3");
		////取出空间
		BlogZone bz = blogZoneService.findByProperty("zoneUrl", "127.0.0.1/fenzaiway2233").get(0);
		///设置双向关联
		m.setBlogZone(bz);
		Set<Music> ms = new HashSet<Music>();
		ms.add(m);
		bz.setMusics(ms);
		musicService.save(m);
	}
	
}
