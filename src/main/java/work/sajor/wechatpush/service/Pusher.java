package work.sajor.wechatpush.service;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import work.sajor.wechatpush.util.CaiHongPi;
import work.sajor.wechatpush.util.JiNianRi;
import work.sajor.wechatpush.util.PYQWenAn;

/**
 *@ClassName Pusher
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 16:03
 */
@Service
public class Pusher {
    /**
     * 测试号的appId和secret
     */
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    //模版id
    @Value("${wechat.templateId}")
    private String templateId;

    @Value("${tian.caihongpi.key}")
    private String key;

    /**
     * 恋爱
     */
    @Value("${target.lianAi}")
    private String lianAi;
    /**
     * 领证
     */
    @Value("${target.linZheng}")
    private String linZheng;
    /**
     * 结婚
     */
    @Value("${target.jieHun}")
    private String jieHun;
    /**
     * 生日
     */
    @Value("${target.shengRi}")
    private String shengRi;


    @Autowired
    Tianqi tianqiService;

    //appID
    //wx86317a917b8d05bd
    //appsecret
    //d2a0e352bed2714f3e62187a2ee4bae6

    public void push(String openId){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        String appId = "wx86317a917b8d05bd";
        String secret = "d2a0e352bed2714f3e62187a2ee4bae6";
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        openId = "oG6yK6FclvQsC1X2-607gcE_7uBU";
        templateId = "Fz26vhbDFi3ZAuyXUZg2VE4tDQmKUd1eN_pt_5uv5KA";
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = tianqiService.getNanjiTianqi();
        templateMessage.addData(new WxMpTemplateData("riqi",todayWeather.getString("date") + "  "+ todayWeather.getString("week"),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",todayWeather.getString("text_day"),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("chengshi","广州市","#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("low",todayWeather.getString("low") + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("currenttemp",todayWeather.getString("temp") + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("high",todayWeather.getString("high")+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPi.getCaiHongPi(key),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRi.getLianAi(lianAi)+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",JiNianRi.getShengRi(shengRi)+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju",CaiHongPi.getJinJu()+"","#C71585"));
        templateMessage.addData(new WxMpTemplateData("pyq", PYQWenAn.getJinJu()+"","#C71585"));
//        templateMessage.addData(new WxMpTemplateData("jiehun",JiNianRi.getJieHun()+""));
//        templateMessage.addData(new WxMpTemplateData("linzhen",JiNianRi.getLinZhen(linZheng)+"","#FF6347"));
//        String beizhu = "";
//        if(JiNianRi.getJieHun(jieHun) % 365 == 0){
//            beizhu = "今天是结婚纪念日！";
//        }
//        if(JiNianRi.getLianAi(lianAi) % 365 == 0){
//            beizhu = "今天是恋爱纪念日！";
//        }
//        if(JiNianRi.getLinZhen(linZheng) % 365 == 0){
//            beizhu = "今天是领证纪念日！";
//        }
//       templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));


        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
    public void push2(String openId){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        String appId = "wx86317a917b8d05bd";
        String secret = "d2a0e352bed2714f3e62187a2ee4bae6";
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        openId = "oG6yK6NWKw_iJwEGMD4FDGNw49kk";
        templateId = "Fz26vhbDFi3ZAuyXUZg2VE4tDQmKUd1eN_pt_5uv5KA";
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = tianqiService.getNanjiTianqi();
        templateMessage.addData(new WxMpTemplateData("riqi",todayWeather.getString("date") + "  "+ todayWeather.getString("week"),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",todayWeather.getString("text_day"),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("chengshi","广东省 广州市","#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("low",todayWeather.getString("low") + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("currenttemp",todayWeather.getString("temp") + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("high",todayWeather.getString("high")+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPi.getCaiHongPi(key),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRi.getLianAi(lianAi)+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",JiNianRi.getShengRi(shengRi)+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju",CaiHongPi.getJinJu()+"","#C71585"));
//        templateMessage.addData(new WxMpTemplateData("jiehun",JiNianRi.getJieHun()+""));
//        templateMessage.addData(new WxMpTemplateData("linzhen",JiNianRi.getLinZhen(linZheng)+"","#FF6347"));
//        String beizhu = "";
//        if(JiNianRi.getJieHun(jieHun) % 365 == 0){
//            beizhu = "今天是结婚纪念日！";
//        }
//        if(JiNianRi.getLianAi(lianAi) % 365 == 0){
//            beizhu = "今天是恋爱纪念日！";
//        }
//        if(JiNianRi.getLinZhen(linZheng) % 365 == 0){
//            beizhu = "今天是领证纪念日！";
//        }
//       templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));


        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
