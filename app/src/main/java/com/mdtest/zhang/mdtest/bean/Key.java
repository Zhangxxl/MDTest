package com.mdtest.zhang.mdtest.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by zhangxx on 2017/2/15.
 * MDTest --> com.mdtest.zhang.mdtest.bean
 */

@Table("key_list")
public class Key {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int _id;
    public String id;
    public String expire;
    public String community_name;
    public String sdk_userid;
    public String communityid;
    public String key_name;
    public String is_wx_share;
    public String plat_community;
    public String pid;
    public long deadline;
    public String key_content;
}
