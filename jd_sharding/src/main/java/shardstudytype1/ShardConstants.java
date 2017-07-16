package shardstudytype1;

/**
 * Created by wangyingjie1 on 2016/3/18.
 */
public class ShardConstants {

    /**
     * 默认的逻辑表名
     */
    public static final String DEFAULT_TABLE_NAME = "logicTableName";

    /**
     * 传递给myibatis引擎的参数对象中代表表名的默认属性名
     */
    public static final String DEFAULT_TABLE_NAME_FIELD = "physicTableName";

    /**
     * 用户路由计算的默认属性
     */
    public static final String DEFAULT_SHARDING_FIELD = "shardField";

    /**
     * 数据源名称
     */
    public static final String DEFAULT_DATASOURCE_NAME = "dataSourceName";

    // 订单明细表逻辑表名
    public static final String ORDER_DETAIL_TABLENAME = "treasure_detail";
    public static final String ORDER_DETAIL_MERGE_TABLENAME = "treasure_detail_merge";
    // 订单明细表路由字段
    public static final String ORDER_DETAIL_ROUTER_FIELD = "id";
    // 用户订单明细表逻辑表名
    public static final String USER_ORDER_DETAIL_TABLENAME = "user_order_detail";
    public static final String USER_ORDER_DETAIL_ROUTER_FIELD = "userPin";

    // 订单表逻辑表名
    public static final String ORDER_TABLENAME = "treasure_order";
    public static final String ORDER_MERGE_TABLENAME = "treasure_order_merge";

    // 订单表路由字段
    public static final String ORDER_ROUTER_FIELD = "orderId";

    // 订单扩展表逻辑表名
    public static final String ORDER_EXTENSION_TABLENAME = "treasure_order_extension";
    // 订单扩展表路由字段
    public static final String ORDER_EXTENSION_ROUTER_FIELD = "id";

    // 用户订单表逻辑表名
    public static final String USER_ORDER_TABLENAME = "user_order";
    public static final String USER_ORDER_ROUTER_FIELD = "userPin";

    // 夺宝号表
    public static final String CODE_TABLENAME = "treasure_code";

    // 夺宝项表
    public static final String TREASURE_ITEM_TABLENAME = "treasure_item";
    public static final String TREASURE_ITEM_MERGE_TABLENAME = "treasure_item_merge";


    // 期表
    public static final String TREASURE_ISSUE_TABLENAME = "treasure_issue";

    // 评论表
    public static final String TREASURE_COMMENT = "treasure_comment";

    public static final String TREASURE_ISSUE_MERGE_TABLENAME = "treasure_issue_merge";

    //  夺宝项商家关系表
    public static final String TREASURE_ITEM_VENDER_TABLENAME = "treasure_item_vender";

    //	鲜花记录表
    public static final String TREASURE_FLOWER_LOG_TABLENAME = "treasure_flower_log";

    //短信记录表
    public static final String TREASURE_SMS_TABLENAME = "treasure_sms";

    //  退款表
    public static final String TREASURE_REFUND_TABLENAME = "treasure_refund";

    //  卡密表
    public static final String TREASURE_CARD_TABLENAME = "treasure_card";

    //  评论表
    public static final String TREASURE_COMMENT_TABLENAME = "treasure_comment";

    //	派奖表
    public static final String TREASURE_SEND_AWARD_TABLENAME = "treasure_send_award";
    public static final String TREASURE_SEND_AWARD_MERGE_TABLENAME = "treasure_send_award_merge";

    //  任务流水表
    public static final String TREASURE_TASK_TABLENAME = "treasure_task";

    //	开奖记录表
    public static final String TREASURE_OPEN_AWARD_TABLENAME = "treasure_open_award";

    //	算奖记录表
    public static final String TREASURE_COUNT_AWARD_TABLENAME = "treasure_count_award";
    //  分库中记录的算奖依据表
    public static final String TREASURE_BASIC_COUNT_AWARD_TABLENAME = "treasure_basic_count_award";

    //	购买次数统计表
    public static final String TREASURE_BUY_STAT_TABLENAME = "treasure_buy_stat";

    //  将erporderId 与 本地订单号的映射前缀
    public static final String ERPORDERID_ORDERID = "erpidmap";
}
