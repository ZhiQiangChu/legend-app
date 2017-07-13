D+快检平台V2.1后端设计
====================

消息推送
--------
> 已实现

用户中心
-------
功能需求：用户中心的个人资料增加选择/修改用户行业信息的功能，行业可多选

实现：
1. 基本思路：将用户行业信息、用户所属公司、公司地址、联系人、qq号码等作为扩展信息抽象出一个新的服务`user-app`

2. 创建数据库`user_app`，新增用户扩展信息表`dp_app_user_info`
```sql
CREATE DATABASE IF NOT EXISTS `user_app`;
CREATE TABLE IF NOT EXISTS `user_app`.`dp_app_user_info` (
  `user_id` CHAR(32)  NOT NULL COMMENT '用户ID',
  `user_name` VARCHAR(32) NOT NULL COMMENT '用户名称',
  `contact` VARCHAR(32) NULL COMMENT '联系人',
  `phone` VARCHAR(32) NULL COMMENT '联系人电话',
  `company_name` VARCHAR(128) NULL  COMMENT '公司名称',
  `company_address` VARCHAR(255) NULL COMMENT '公司地址',
  `qq` VARCHAR(32) NULL COMMENT 'QQ号码',
  `industry_ids` TEXT  NULL COMMENT '用户所属行业ID，多个用,分割',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户所属行业ID，多个用,分割',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```


3. 接口定义

- [新增普通用户扩展信息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=66&docId=66&interId=3724)
- [获取普通用户扩展信息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=66&docId=66&interId=3726)

用户行业信息mock:
```json
{
    "code": 0,
    "result": {
        "userId": "27359d876b14cd8416cb1d949279d579",
        "userName": "sondon",
        "contact": "张三",
        "phone": "13512345678",
        "companyName": "广州讯动网络有限公司",
        "companyAddress": "广州天河区华景软件园",
        "qq": "12345678",
        "industryIds": "d54185b71f614c30a396ac4bc44d3269,d54185b71f614c30a396ac4bc44d326",
        "created_at": 1497927857000,
        "updatedAt": 1497927857000
    }
}
```
- [更新普通用户扩展信息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=66&docId=66&interId=3725)


检管应用-品控分析
---------------
功能需求：品控分析目前只显示定量指标，现在需要根据指标类型（定量或者定性）来分别展示不同结果

实现：
1. 接口定义

- [获取日均曲线信息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=9&docId=9&interId=1135)，响应信息实例：

a) 定量指标检测记录mock
```json
{
    "code": 0,
    "result": {
        "quantitative": [
            {
                "indicatorId": "fc0aab196753411a9e336bf53bb3bde5",
                "sampleCount": 50,
                "detectTime": "2017.06.14",
                "indctValue": 9.23
            },
            {
                "indicatorId": "fc0aab196753411a9e336bf53bb3bde5",
                "sampleCount": 40,
                "detectTime": "2017.06.15",
                "indctValue": 9.45
            }
        ]
    }
}

```
b) 定性指标检测记录mock
```json
{
    "code": 0,
    "result": {
        "negative": [
            {
                "indicatorId": "0a6ceccd9d3747abab39057d6446fedf",
                "sampleCount": 50,
                "detectTime": "2017.06.14",
                "indctValue": 15
            },
            {
                "indicatorId": "0a6ceccd9d3747abab39057d6446fedf",
                "sampleCount": 40,
                "detectTime": "2017.06.15",
                "indctValue": 10
            }
        ],
        "positive": [
            {
                "indicatorId": "0a6ceccd9d3747abab39057d6446fedf",
                "sampleCount": 50,
                "detectTime": "2017.06.14",
                "indctValue": 35
            },
            {
                "indicatorId": "0a6ceccd9d3747abab39057d6446fedf",
                "sampleCount": 40,
                "detectTime": "2017.06.15",
                "indctValue": 30
            }
        ]
    }
}
```

- [获取不同品种属性检测结果信息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=9&docId=9&interId=1136)

a) 定量指标检测记录mock
```json
{
    "code": 0,
    "result": {
        "quantitative": [
            {
                "time": "2017.06.14",
                "indValue": 9.23
            },
            {
                "time": "2017.06.15",
                "indValue": 9.25
            }
        ]
    }
}
```
b) 定性指标检测记录mock
```json
{
    "code": 0,
    "result": {
        "negative": [
            {
                "time": "2017.06.14",
                "indValue": 10
            },
            {
                "time": "2017.06.15",
                "indValue": 9
            }
        ],
        "positive": [
            {
                "time": "2017.06.14",
                "indValue": 30
            },
            {
                "time": "2017.06.15",
                "indValue": 41
            }
        ]
    }
}
```

2. 结果按照检测时间顺序排列，对于定性结果，阴性、阳性分别封装两个不同的数组

检测中心-记录-修改样品属性
-----------
功能需求：可修改检测记录样品属性的样品编号

实现：
1. 接口定义

- [修改样品接口](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=16&docId=16&interId=221)增加请求非必填参数`sampleNo`


设备信息
----------
功能需求：目前不同型号设备的图片都是用NGD-D1的图片，现修改为不同型号能有对应不同的图片

实现：
1. 接口定义

- [查询指定设备类型信息](http://sapi.sondon.net/auth/doc/inter/forwardInfo.htm?projId=38&docId=38&interId=1639)

样品集
----------
功能需求：样品集对于定性指标分别显示正、负样品的理化值数量

实现：（待定）






