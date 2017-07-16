package shardstudytype1;

import com.jd.common.util.SequenceUtil;


/**
 * Created by wangyingjie1 on 2016/4/2.
 */
public enum SeqIdInfoEnum implements SeqIdInfoGenerator {

    TREASURE_ORDER("treasure_order", "抢宝订单表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_ORDER_SEQUENCE);
            long dsSerialNum = ShardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = shardRouterUtil.getTableNumByItemIdAndIssueId(logicTableName, itemId, issueId);

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_ORDER_EXTENSION("treasure_order_extension", "抢宝订单扩展表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_ORDER_EXTENSION_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = shardRouterUtil.getTableNumByItemIdAndIssueId(logicTableName, itemId, issueId);

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_DETAIL("treasure_detail", "抢宝订单明细表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_DETAIL);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = shardRouterUtil.getTableNumByItemIdAndIssueId(logicTableName, itemId, issueId);

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_ITEM("treasure_item", "夺宝项表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_ITEM_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumByItemId(id);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }
    },

    TREASURE_ITEM_VENDER("treasure_item_vender", "夺宝项商家关系表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_ITEM_VENDER_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_ISSUE("treasure_issue", "期表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_ISSUE_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }
    },

    TREASURE_CODE("treasure_code", "夺宝号表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_CODE_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_REFUND("treasure_refund", "退款表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_REFUND_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_CARD("treasure_card", "卡密表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_CARD_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_SEND_AWARD("treasure_send_award", "派奖表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_SEND_AWARD);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_TASK("treasure_task", "任务流水表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_TASK_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_OPEN_AWARD("treasure_open_award", "开奖记录表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_OPEN_AWARD);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_BUY_STAT("treasure_buy_stat", "购买次数统计表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_BUY_STAT);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_COUNT_AWARD("treasure_count_award", "算奖记录表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_COUNT_AWARD_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    },

    TREASURE_BASIC_COUNT_AWARD("treasure_basic_count_award", "算奖记录基础表") {
        public SeqIdInfo getSeqIdInfo(SequenceUtil sequenceUtil, ShardRouterUtil shardRouterUtil, String logicTableName, long itemId, long issueId) throws Exception {

            long id = sequenceUtil.get(DBPKConstants.TREASURE_BASIC_COUNT_AWARD_SEQUENCE);
            long dsSerialNum = shardRouterUtil.getDsSerialNumById(logicTableName, itemId);
            long routeId = 0;

            return new SeqIdInfo(id, routeId, dsSerialNum);
        }

    };


    private String tableName;
    private String desc;

    private SeqIdInfoEnum(String tableName, String desc) {
        this.tableName = tableName;
        this.desc = desc;
    }

    public static SeqIdInfoEnum getSeqIdInfoEnumByTableName(String tableName) {
        for (SeqIdInfoEnum seqIdInfoEnum : SeqIdInfoEnum.values()) {
            if (seqIdInfoEnum.getTableName().equals(tableName)) {
                return seqIdInfoEnum;
            }
        }
        throw new RuntimeException("SeqIdInfoEnum not exist, tableName:" + tableName);
    }


    public String getDesc() {
        return desc;
    }

    public String getTableName() {

        return tableName;
    }

}
