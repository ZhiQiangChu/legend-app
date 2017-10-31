# D+快检平台V2.2后端设计


## 一、第一阶段

### 平台可根据不同设备配置不同的参比、自检流程
> 需求目前不明确，设计延后


### 前端增加消息通知
- 设计思路： 
    - 定义需要监听的系统消息kafka主题: `sy_biz_notice`
    - 定义通用的系统消息实体:
    
    ```java
        
    public class WSMsg {
    
        /** 发送给那个用户 */
        private String toUserId;
    
        /** 发送接收的目的地   当这个参数为All的时候发送给当前用户所有应用端 */
        private String appId;
    
        /** 当前请求的token */
        private String token;
    
        /** 当前的结果码 */
        private int code;
    
        /** 返回的数据 */
        private Object data;
    
        /** 提示的消息 */
        private String msg;
    
        /** 当前的消息业务编号 */
        private int msgNo;
    
        /** 消息的标题 */
        private String title;
    
        /** 消息内容 */
        private String content;
    
        /** 是否需要持久化 */
        private boolean needPersist;
    }
 
    ```
    - 根据`needPersist`决定是否对收到的消息进行持久化
    
- 数据模型设计
    ```sql
    CREATE DATABASE `dp_message`;
    
    -- Table `message` 消息表
    CREATE TABLE IF NOT EXISTS `dp_message`.`message` (
      `id` char(32) NOT NULL COMMENT '消息ID',
      `title` varchar(255) NOT NULL COMMENT '标题',
      `content` text NOT NULL COMMENT '内容',
      `msg_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '消息分类：0：通告，1：消息，2：提醒, 3:即时',
      `priority` tinyint(3) NOT NULL DEFAULT '2' COMMENT '优先级：0:紧急,1:重要,2:普通',
      `sender_type` tinyint(3) NULL COMMENT '发送对象类型:0:系统，1：客服，2：用户',
      `receiver_type` tinyint(4) NOT NULL COMMENT '接收对象类型:0:全体，1：平台，2：应用，3：分组，4：多用户，5：单用户',
      `reply_msg_id` char(32) DEFAULT NULL COMMENT '回复消息编号',
      `platform_id` char(32) DEFAULT NULL COMMENT '平台编号',
      `app_id` char(32)  NULL COMMENT '应用编号',
      `group_id` char(32) NULL COMMENT '用户分组编号',
      `org_id` char(32)  NULL COMMENT '组织ID',
      `sender_id` char(32) NULL COMMENT '发送人用户ID',
      `is_revoked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否撤销',
      `revoked_at` timestamp NULL DEFAULT NULL COMMENT '撤销时间',
      `is_removed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
      `removed_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
      `sel_email` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有选择邮件发送',
      `sel_sms` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有选择短信发送',
      `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_at` timestamp NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    
    -- Table `user_msg_subscribe` 用户消息标记表
    CREATE TABLE IF NOT EXISTS `dp_message`.`user_msg_subscribe`(
      `msg_id` char(32) NOT NULL COMMENT '消息ID',
      `user_id` char(32) NOT NULL COMMENT '用户ID',
      `is_read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否阅读',
      `read_at` timestamp NULL DEFAULT NULL COMMENT '阅读时间',
      `is_removed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
      `remove_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
      `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_at` timestamp NULL  COMMENT '更新时间',
      PRIMARY KEY (`msg_id`,`user_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    ```
- 接口设计
    - [获取未读消息数量](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=57&docId=57&interId=3181)
    - [列出用户发送或接收到的消息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=57&docId=57&interId=2970)
    - [查看消息详情](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=57&docId=57&interId=4859)
    - [阅读消息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=57&docId=57&interId=2971)
    - [删除消息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=57&docId=57&interId=2973)

### 设备显示湿度
> 已经实现，调用[查询设备的即时状态](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=38&docId=38&interId=1647)接口可以获取

### 增加自检提示
> 后端已实现,`DM_IM_STATUS`已包含上次自检时间，上一次参比时间

### 设备预热提示
> 设备状态已经包含了预热状态
    
### 增加建模任务    
- 实现思路：
    - 创建表保存建模任务相关信息
    - 预计建模时间，这个前端建模前已计算好，可以传到后台
    - 实时更新建模进度
    - 是否考虑增加实际建模所需时间
- 数据模型(mongodb)设计：

  ```java
    @Entity("MODEL_TASK")
    public class ModelTask {
        
        @Property("_id")
        @Id
        private String id;
    
        /** 任务编号 */
        private String taskNo;
    
        /** 用户ID */
        private String userId;
    
        /** 品种ID */
        private String breedId;
    
        /** 品种名称 */
        private String breedName;
    
        /** 样品集ID */
        private String sampleSetId;
    
        /** 样品集名称 */
        private String sampleSetName;
    
        /** 指标ID */
        private String indicatorIds;
    
        /** 建模开始时间 */
        private Long startTime;
    
        /** 建模结束时间 */
        private Long endTime;
    
        /** 预计消耗时间 */
        private Long estimatedTime;
    
        /** 建模状态 0:正在建模 1:建模完成 */
        private int state;
    
        /** 更新时间 */
        private Long updateAt;
    
    }
  ```
- 接口设计
    - [获取建模任务列表](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=64&docId=64&interId=4863),mock数据:
    
    ```json
    {
        "code": 0,
        "result": [
            {
                "id": "52a70aa4c19a4eea895a40369fffe73b",
                "taskNo": "Modeling-Task_btpYvhXxrIio7YI",
                "userId": "8e4aefa9beaa426c9b54d6103a53d71e",
                "breedId": "d1a999eaa43b4e8fb3777b46a89af63b",
                "breedName": "豆粕",
                "sampleSetId": "00f67411dafe40968af2e841996743a6",
                "sampleSetName": "发酵豆粕",
                "indicatorIds": "7d3650912a534aa99ccbb14faf46b58e,7b45ad416c194e1b8d19dbbc40b2f107",
                "startTime": 1501122270624,
                "endTime": 1501122290624,
                "estimatedTime": 20000,
                "state": 1
            },
            {
                "id": "c1795963ebb94dca9f0f04683e901514",
                "taskNo": "Modeling-Task_Mc7HjJeyPvHpmbL",
                "userId": "8e4aefa9beaa426c9b54d6103a53d71e",
                "breedId": "d1a999eaa43b4e8fb3777b46a89af63b",
                "breedName": "豆粕",
                "sampleSetId": "00f67411dafe40968af2e841996743a6",
                "sampleSetName": "发酵豆粕",
                "indicatorIds": "7d3650912a534aa99ccbb14faf46b58e,7b45ad416c194e1b8d19dbbc40b2f107",
                "startTime": 1501222816444,
                "endTime": 1501222836444,
                "estimatedTime": 20000,
                "state": 1
            }
        ]
    }
    ```
    
### 设备配置独立于快测服务
- 功能分解：
   - 修改绑定设备的备注信息
- 实现思路：
   - 增加修改设备信息接口
   - MySQL设备绑定信息表`d_bind_record`增加备注信息字段`user_remark`:
   
       ```sql
           use `dp_device`;
           -- 增加设备备注信息字段
           ALTER TABLE `d_bind_record` ADD COLUMN `user_remark` VARCHAR(255) NULL COMMENT '设备备注信息';
       ```
   - Mongodb设备流转状态集合`DEV_IM_STATUS`增加备注信息字段`userRemark`
   - MySQL更新成功后同步更新Mongodb
- 接口设计
   - [更新设备备注信息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=38&docId=38&interId=4858),mock : 
   
   ```json
        {
            "code": 0,
            "result": {
                "dId": "f92833407ddf12a34ce28457b754941b",
                "dSn": "485602951173",
                "ownerId": "59c8f6e8fece4302ac0c6cbc6c5ac9b2",
                "ownerName": "sondon73",
                "bindTime": "2016-12-12 16:13:08",
                "updateAt": "2016-12-12 16:13:08",
                "deviceUserLabel": "NGD-D1-1173",
                "userRemark": "赵铁柱"
            }
        }
   ```

### 设备自检，测白板和聚苯乙烯的次数调整为可配置
> 待定

    
## 二、第二阶段

### 用户中心支持修改头像
> 后台已实现

### 样本数量达到上限后的提示    
> 后台已实现

### 可按样品编号搜索样品
- 实现思路：
    - 在原有[获取样品列表](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=16&docId=16&interId=218)接口增加按样品编号过滤
    - 模糊搜索，对大小写字母不敏感


### 检管应用-工作报表增加设备筛选条件
- 实现思路：目前只对当前设备
- 接口设计
    - [检测工作统计](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=9&docId=9&interId=1133)
    - [检测达标统计](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=9&docId=9&interId=1134)
- 评估上面接口是否可以扩展，否则新增接口

### 样品集-可对样品进行移除，对样本进行删除
- 功能分解：
    - 样品集，可对比查看某样品下样本光谱
    - 样品集，隐藏样品的删除功能，增加样品的移除功能
    - 点击移除按钮可移除对应样品
- 实现思路：
- 接口设计：

### 样品集-可选择隐藏样品列表的指标
- 实现思路：
    - 根据样品集的品种查出对应的所有指标
    - 样品列表理化数据的指标只显示用户选择的指标
    - 保存该用户对于该样品集选择的指标
- 接口设计

### 品种筛选框支持autocomplete

###　系统模型支持查看模型报告
> 后端已实现