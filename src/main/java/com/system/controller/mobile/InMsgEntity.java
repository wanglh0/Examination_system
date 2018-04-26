package com.system.controller.mobile;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wlh on 2018/4/26.
 */

/*  @XmlRootElement是一个类级别注解，主要属性为name，意为指定根节点的名字。
        往上面看前面举了个微信传过来的xml数据的例子里，里面的根节点就是"xml"，所以这里就直接设置name="xml"

    @XmlAccessorType用于定义这个类中的何种类型需要映射到XML中
    XmlAccessType.PROPERTY：代表映射这个类中的属性（get/set方法）到XML
       XmlAccessType.FIELD：代表映射这个类中的所有字段到XML(我选用的，现在的字段名刚好是大写开头了)*/


@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class InMsgEntity {
    private String ToUserName;      //开发者微信号
    private String FromUserName;      //发送方帐号（一个OpenID）
    private Long CreateTime;      //消息创建时间 （整型）
    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     */
    private String MsgType;
    private String Content;      //文本消息内容
    private Long MsgId;      //消息id，64位整型

    // 图片链接（由系统生成）
    private String PicUrl;
    // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String MediaId;

    /**
     * 事件类型
     * subscribe(订阅)
     * unsubscribe(取消订阅)
     * LOCATION(上报地理位置)
     * CLICK(点击普通的菜单)
     * VIEW(点击跳转链接的菜单)
     */
    private String Event;

    private String EventKey;  //菜单按钮的key值


    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
