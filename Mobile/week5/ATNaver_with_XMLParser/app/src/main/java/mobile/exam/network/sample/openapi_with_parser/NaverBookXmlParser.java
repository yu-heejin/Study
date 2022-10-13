package mobile.exam.network.sample.openapi_with_parser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class NaverBookXmlParser {

    private XmlPullParser parser;

    private enum TagType { NONE, TITLE, AUTHOR, LINK, IMAGE };    //해당없음, title, author, link, image

    //parsing 대상인 tag를 상수로 선언
    private final static String ITEM_TAG = "item";
    private final static String TITLE_TAG = "title";
    private final static String AUTHOR_TAG = "author";
    private final static String LINK_TAG = "link";
    private final static String IMAGE_TAG = "image";

    //주의사항: item 태그를 만난 이후의 title을 파싱해야함!
    //dto 객체가 null인지 아닌지일때만 체크해주면 됨
    public NaverBookXmlParser() {
        //XmlPullParserFactory factory = null;

        try {  //예외상황 발생 시 오류 처리
            parser = XmlPullParserFactory.newInstance().newPullParser();
        } catch (XmlPullParserException e) {
            //발생할 수 있는 오류가 여러가지 일 수 있음! 세부 오류 처리가 필요하다
            e.printStackTrace();
        }
    }

    public ArrayList<NaverBookDto> parse(String xml) {
        ArrayList<NaverBookDto> resultList = new ArrayList();
        NaverBookDto dbo = null;
        TagType tagType = TagType.NONE;     //  태그를 구분하기 위한 enum 변수 초기화

        try {
            // 파싱 대상 지정
            parser.setInput(new StringReader(xml));
            int eventType = parser.getEventType(); // 태그 유형 구분 변수 준비

            // parsing 수행 - for 문 또는 while 문으로 구성
            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if(tag.equals(ITEM_TAG)) {
                            dbo = new NaverBookDto();
                        } else if(dbo != null && tag.equals(TITLE_TAG)) {
                            tagType = TagType.TITLE;
                        } else if(tag.equals(AUTHOR_TAG)) {
                            tagType = TagType.AUTHOR;
                        } else if(dbo != null && tag.equals(LINK_TAG)) {
                            tagType = TagType.LINK;
                        } else if(tag.equals(IMAGE_TAG)) {
                            tagType = TagType.IMAGE;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals(ITEM_TAG)) {
                            resultList.add(dbo);
                        }
                        break;

                    case XmlPullParser.TEXT:
                        switch (tagType) {
                            case TITLE:
                                dbo.setTitle(parser.getText());
                                break;
                            case AUTHOR:
                                dbo.setAuthor(parser.getText());
                                break;
                            case LINK:
                                dbo.setLink(parser.getText());
                                break;
                            case IMAGE:
                                dbo.setImageLink(parser.getText());
                                break;
                        }
                        tagType = TagType.NONE;
                        break;
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
