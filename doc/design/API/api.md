
|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|	|
|dSn	|String	|设备的sn	|
|dTypeId	|String	|设备的类型id	|
|dTypeName	|String	|设备的类型名称	|
|currentStateId	|String	|当前的运行态id	|
|connectIp	|String	|连接服务器的ip 地址	|
|ip	|String	|设备的ip	|
|status	|Integer	|设备的状态	|
|warningCode	|Integer>	|警告代码	|
|currentTime	|Long	|当前更新的时间	|
|ownerId	|String	|持有人的id	|
|ownerName	|String	|持有人的名称	|
|deviceUserLabel	|String	|设备的备注名称	|
|userRemark	|String	|用户自定义备注信息	|
|devTemper	|Float	|设备温度	|
|devHumidity	|Float	|设备的湿度	|
|detectorTemper	|Float	|检测器温度	|
|detectorHumidity	|Float	|检测器的湿度	|
|lampTemper	|Float	|光源的温度	|
|devSoftVersion	|String	|程序的版本号	|
|dSnRef	|String	|设备的sn参考值	|
|starupTime	|Long	|开机时间	|
|platformId	|String	|平台的id	|
|GPS	|String	|GPS 的状态	|
|productionDate	|Long	|生产日期	|
|batch	|String	|生产批次	|
|flowState	|Integer	|流转状态	|
|devAddTime	|Long	|设备添加的时间	|
|lastOffLineTime	|Long	|上一次的离线时间	|
|MAC	|String	|设备的MAC地址	|
|lockBy	|String	|当前设备被何人锁定	|
|preheatState	|Integer	|设备的预热状态  0 预热中   1 预热完成  2 用户关闭卤灯	|
|imStatus	|DevImStatus	|	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|id	|String	|对应记录的id	|
|typeName	|String	|对应的类型名称	|
|snRef	|String	|设备类型的对应的sn索引值	|
|image	|String	|该设备类型对应的图片地址	|
|updateTime	|Timestamp	|更新时间	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|id	|String	|对应的设备的id	|
|typeId	|String	|设备类型的id	|
|typeName	|String	|设备的类型名称	|
|dSn	|String	|设备的sn	|
|batch	|String	|设备的生产批次	|
|productionDate	|Date	|设备的生产日期	|
|createDate	|Timestamp	|设备的添加日期	|
|MAC	|String	|设备的mac地址	|
|isActive	|Integer	|激活状态	|
|activeDate	|Timestamp	|激活日期	|
|flowState	|Integer	|流转状态	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|系统产生的样品编号	|
|sampleNo	|String	|用户自定义的样品编号	|
|userId	|String	|用户的id	|
|breedId	|String	|品种的id	|
|breedName	|String	|品种名称	|
|dSn	|String	|当前的设备sn	|
|createTime	|Long	|当前样品的创建时间	|
|lastCollectTime	|Long	|当前样品的采集时间	|
|collectIp	|String	|采集机器的IP	|
|sampleRemark	|String	|样品的备注	|
|state	|Integer	|状态   默认    正常1 ，0 未产生光谱 ，-1已删除	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|行业id	|
|industryName	|String	|行业名称	|
|createTime	|Long	|记录创建时间	|
|updateTime	|Long	|记录更新时间	|
|state	|Integer	|状态	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|id	|Integer	|	|
|hexId	|String	|16进制的id索引	|
|paramName	|String	|	|
|readOnly	|Integer	|是否是只读属性	|
|isGlobal	|Integer	|是否是全局参数	|
|unit	|String	|值的单位	|
|type	|Integer	|参数类型	|
|len	|Integer	|参数对应的数据长度	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|id	|String	|样本的id	|
|sampleId	|String	|所属样品的id	|
|collectTime	|Timestamp	|采集的时间	|
|sampleSetId	|String	|样品集id	|
|dSn	|String	|设备的sn	|
|spectrum	|Object	|光谱数据	|
|spectrumJson	|String	|存放在Hbase 中的光谱数据	|
|devEnvFactorsJson	|String	|存放在和base中的设备环境变量json数据	|
|userId	|String	|用户ID	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|id	|String	|样本的id	|
|sampleId	|String	|所属样品的id	|
|collectTime	|Timestamp	|采集的时间	|
|sampleSetId	|String	|样品集id	|
|dSn	|String	|设备的sn	|
|spectrum	|Float>	|光谱数据	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|id	|Integer	|	|
|typeName	|String	|设备参数分类名称	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|dsn	|String	|设备SN号	|
|adValues	|Integer>>	|白板数据	|
|absValues	|Float>>	|聚苯乙烯数据	|
|time	|Long	|采集时间	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|属性ID	|
|attrName	|String	|属性名称	|
|attrType	|String	|属性类型	|
|breedId	|String	|品种ID	|
|breedName	|String	|品种的名称	|
|industryId	|String	|行业的id	|
|industryName	|String	|行业的名称	|
|required	|Integer	|必要属性	|
|attrValues	|String	|属性值	|
|defaultValue	|String	|默认值	|
|userId	|String	|用户ID	|
|createdAt	|Integer	|创建时间	|
|updatedAt	|Integer	|更新时间	|
|flag	|Integer	|删除标记	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|指标ID	|
|indicatorName	|String	|指标名称	|
|breedId	|String	|品种ID	|
|breedName	|String	|品种名称	|
|industryId	|String	|行业ID	|
|industryName	|String	|行业名称	|
|userId	|String	|用户ID	|
|indicatorType	|Integer	|指标类型 0定量和1定性	|
|typeName	|String	|类型名称	|
|unit	|String	|单位	|
|createdAt	|Integer	|创建日期	|
|updatedAt	|Integer	|更新日期	|
|flag	|Integer	|删除标记	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|	|
|dSn	|String	|对应的设备sn	|
|status	|Integer	|各种状态	|
|warningCode	|Integer>	|警告标识代号	|
|GPS	|Double>	|GPS 状态	|
|MAC	|String	|设备的MAC地址	|
|devTemper	|Float	|设备温度	|
|devHumidity	|Float	|设备的湿度	|
|detectorTemper	|Float	|检测器温度	|
|detectorHumidity	|Float	|检测器的湿度	|
|lampTemper	|Float	|光源的温度	|
|updateTime	|Long	|更新时间	|
|devSoftVersion	|String	|程序的版本	|
|connectIp	|String	|当前设备连接的服务器ip	|
|preheatState	|Integer	|设备的预热状态  0 预热中   1 预热完成  2 用户关闭卤灯	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|BASE_TYPE	|int	|	|
|INDICATOR_TYPE	|int	|	|
|sampleId	|String	|样品的id	|
|attrId	|String	|属性的id	|
|attrName	|String	|属性的名称	|
|attrValue	|String	|属性的值	|
|attrType	|Integer	|属性的类型	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|样品集的id	|
|sampleSetName	|String	|样品集名称	|
|sampleSetDesc	|String	|样品集描述	|
|breedId	|String	|品种的id	|
|breedName	|String	|品种的名称	|
|industryId	|String	|行业的id	|
|industryName	|String	|行业的名称	|
|userId	|String	|用户的id	|
|dSn	|String	|设备的SN	|
|dName	|String	|品种的名称	|
|createTime	|Long	|创建时间	|
|updateTime	|Long	|更新时间	|
|isSystemAutoCreated	|Integer	|是不是系统创建的样品集	|
|sampleCount	|Integer	|样品集中样品数统计	|
|state	|Integer	|状态标识为  -1 表示删除了   默认1  状态正常	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|品种ID	|
|industryId	|String	|行业ID	|
|industryName	|String	|行业名称	|
|breedName	|String	|品种名称	|
|userId	|String	|用户ID	|
|image	|String	|图片	|
|backgroundColor	|String	|品种图片的背景颜色	|
|enName	|String	|英文名称	|
|createdAt	|Integer	|创建日期	|
|updatedAt	|Integer	|更新日期	|
|flag	|Integer	|删除标记	|
|attriList	|String	|属性集	|
|maxSpecimen	|Integer	|单个样品最大样本	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|记录的主键	|
|sampleId	|String	|样品的id	|
|sampleSetId	|String	|样品集的id	|
|state	|Integer	|状态	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|dsn	|String	|设备SN号	|
|adValue	|Integer>	|白板数据	|
|time	|Long	|采集时间	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|dsn	|String	|设备SN号	|
|gps	|Double>	|GPS信息	|
|mac	|String	|设备MAC地址	|
|devTemper	|Float	|设备温度	|
|devHumidity	|Float	|设备湿度	|
|detectorTemper	|Float	|检测器温度	|
|detectorHumidity	|Float	|检测器的湿度	|
|updateTime	|Long	|更新时间	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|	|
|recordId	|String	|对应的记录id	|
|absValue	|Float>	|对应的abs值	|
|time	|Long	|采集的时间	|
|state	|Integer	|当前记录的状态	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|	|
|recordId	|String	|对应的记录id	|
|adValue	|Integer>	|对应的ad值	|
|time	|Long	|采集的时间	|
|state	|Integer	|当前记录的状态	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|sampleId	|String	|样品ID	|
|industryId	|String	|行业ID	|
|industryName	|String	|行业名称	|
|breedId	|String	|品种ID	|
|breedName	|String	|品种名称	|
|indicatorId	|String	|指标ID	|
|indicatorName	|String	|指标名称	|
|attrId	|String	|属性ID	|
|attrType	|Integer	|属性类型  0:指标类型 1:基础类型	|
|attrName	|String	|属性名称	|
|attrValue	|String	|属性值	|


|参数名称         |类型      |描述       |
|:-------------|:-------------|:-------------|
|_id	|String	|	|
|dSn	|String	|对应的设备的sn	|
|type	|Integer	|当前记录的类型	|
|state	|Integer	|当前记录状态   -5 时代表的是出厂时的参数    0 未生效    1 记录已经生效  -1 删除	|
|errCode	|Integer	|检测异常码   0  时代表成功  其他代表异常	|
|startTime	|Long	|开始的时间	|
|startStateId	|String	|开启时的设备状态id	|
|endTime	|Long	|结束时间	|
|finishStateId	|String	|结束时的状态id	|
|data	|Object	|设置对外的返回结果   不做保存	|
|remark	|Object	|备注  通常 在设备自检上生效	|

