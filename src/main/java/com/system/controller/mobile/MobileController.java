package com.system.controller.mobile;

import com.system.util.Md5PwdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * Created by wlh on 2018/4/26.
 */
@Controller
@RequestMapping(value = "/mobile")
public class MobileController {

    /**
     * url接入校验
     *
     * @return
     */
    @RequestMapping(value = "/weChat", method = RequestMethod.GET)
    @ResponseBody
    public String weChat(String signature, String timestamp, String nonce, String echostr) {
//        signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//        timestamp	时间戳
//        nonce	随机数
//        echostr	随机字符串

//        1）将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {WeChatUtil.TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder sb=new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
//        2）将三个参数字符串拼接成一个字符串进行sha1加密
        String mySignature = SecurityUtil.getSha1(sb.toString());

//        3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(mySignature.equals(signature)){
            System.out.println("接入成功");
            return echostr;
        }
        //接入失败
        return null;
    }


    /**
     * 消息处理
     * @return
     */
    @RequestMapping(value = "/weChat", method = RequestMethod.POST)
    @ResponseBody
    public Object handleMessage(@RequestBody InMsgEntity inMsgEntity){
        OutMsgEntity outMsgEntity=new OutMsgEntity();
        //把原来的接收方设置为发送方
        outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
        //把原来的发送方设置为接收方
        outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
        //获取接收的消息类型
        String msgType = inMsgEntity.getMsgType();
        //设置消息的响应类型
        outMsgEntity.setMsgType(msgType);
        //设置消息创建时间
        outMsgEntity.setCreateTime(System.currentTimeMillis());


        String outContent=null;
        if("text".equals(msgType)){
            //用户发送的内容
            String inContent = inMsgEntity.getContent();

            if(inContent.contains("开班")){
                outContent="上海Java基础班第05期于2018/05/10开班\n" +
                        "广州Java基础班第24期于2018/04/02开班";
            }else if(inContent.contains("地址")){
                outContent="北京校区：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" +
                        "广州校区：广州市天河区棠下涌东路大地工业区D栋六楼\n" +
                        "上海校区：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402";
            }else{
                outContent=inContent;
            }
            outMsgEntity.setContent(outContent);
        }else if("image".equals(msgType)){
            outMsgEntity.setMediaId(new String[]{inMsgEntity.getMediaId()});
        }else if("event".equals(msgType)){  //事件
            String event = inMsgEntity.getEvent();
            //判断关注事件
            if("subscribe".equals(event)){
                outContent="欢迎关注！！！";
            }else if("click".equalsIgnoreCase(event)){
                //获取菜单的key值
                String key = inMsgEntity.getEventKey();
                if("classinfo".equals(key)){
                    outContent="上海Java基础班第05期于2018/05/10开班\n" +
                            "广州Java基础班第24期于2018/04/02开班";
                }else if("address".equals(key)){
                    outContent="北京校区：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" +
                            "广州校区：广州市天河区棠下涌东路大地工业区D栋六楼\n" +
                            "上海校区：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402";
                }

            }
            outMsgEntity.setMsgType("text");
            outMsgEntity.setContent(outContent);
        }
        return outMsgEntity;
    }
}
