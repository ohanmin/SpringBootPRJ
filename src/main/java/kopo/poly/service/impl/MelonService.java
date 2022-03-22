package kopo.poly.service.impl;

import kopo.poly.dto.MelonDTO;
import kopo.poly.service.IMelonService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("MelonService")
public class MelonService implements IMelonService {
    @Resource(name = "MelonMapper")
    private IMelonService melonService;// MongoDB에 저장할 Mapper
    @Override
    public int collectMelonSong() throws Exception {
        int res = 0;
        List<MelonDTO> pList = new LinkedList<>();
        //멜론 Top100중 50위까지 정보 가져오는 페이지
        String url = "https://www.melon.com/chart/index.htm";
        //JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
        Document doc = Jsoup.connect(url).get();
        //<div class="service_list_song"> 이 태그 내에서 있는 HTML소스만 element에 저장됨
        Elements element = doc.select("div.service_list_song");
        //멜론 100위까지 차트
        for(Element songInfo : element.select("div.wrap_song_info")){
            //크롤링을 통해 데이터 저장하기
            String song = CmmUtil.nvl(songInfo.select("div.ellipsis.rank01 a").text()); //노래
            String singer = CmmUtil.nvl(songInfo.select("div.ellipsis.rank02 a").text());//가수
            log.info("song : " + song);
            log.info("singer : " + singer);
            //가수와 노래 정보가 모두 수집되었다면, 저장함
            if((song.length() > 0) && (singer.length() >0)){
                MelonDTO pDTO = new MelonDTO();
                pDTO.setCollectTime(DateUtil.getDateTime("yyyyMMddhhmmss"));
            }
        }
        return 0;
    }

    @Override
    public List<MelonDTO> getSongList() throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> getSingerSongCnt() throws Exception {
        return null;
    }
}