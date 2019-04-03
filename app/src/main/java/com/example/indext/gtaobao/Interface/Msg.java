package com.example.indext.gtaobao.Interface;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    @Id(autoincrement = true)
    private Long id;
    private String content;
    private int type;
    private int Img_view;
    private String name1;
    private String name2;
    private String name3;
    private int imageViewId;
    @Generated(hash = 1548982186)
    public Msg(Long id, String content, int type, int Img_view, String name1,
            String name2, String name3, int imageViewId) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.Img_view = Img_view;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.imageViewId = imageViewId;
    }
    @Generated(hash = 23037457)
    public Msg() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getImg_view() {
        return this.Img_view;
    }
    public void setImg_view(int Img_view) {
        this.Img_view = Img_view;
    }
    public String getName1() {
        return this.name1;
    }
    public void setName1(String name1) {
        this.name1 = name1;
    }
    public String getName2() {
        return this.name2;
    }
    public void setName2(String name2) {
        this.name2 = name2;
    }
    public String getName3() {
        return this.name3;
    }
    public void setName3(String name3) {
        this.name3 = name3;
    }
    public int getImageViewId() {
        return this.imageViewId;
    }
    public void setImageViewId(int imageViewId) {
        this.imageViewId = imageViewId;
    }
}