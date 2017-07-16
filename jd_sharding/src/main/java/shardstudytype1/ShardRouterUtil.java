package shardstudytype1;

import com.jd.common.util.SequenceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static shardstudytype1.ShardConstants.ORDER_DETAIL_TABLENAME;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/6
 */
public class ShardRouterUtil implements InitializingBean {
    private static final String SEPARATE = "_";
    private static final long ROW_ID = 100000000000000000L;
    private static final long DIV_RAW_ID = 1000000000000L;
    private static final long MAX_RAW_ID = 990000000000L;
    private static final long DS_CNT = 8L;
    private static final int ID_LEN = 19;//id 的位数
    /**
     * @description:注入
     */
    private SequenceUtil sequenceUtil;
    /**
     * <util:map id="hashTableSizeMap" value-type="java.lang.Long">
     * <entry key="user_order" value="128"/>
     * <entry key="user_order_detail" value="128"/>
     * </util:map>
     */
    private Map<String, Long> hashTableSizeMap;

    //订单路由映射：key=startIssueId-endIssueId
    private Map<String, TreasureOrderRouter> orderRouterMap = new HashMap<String, TreasureOrderRouter>();
    //订单路由 开始、结束期Id集合
    private long[] orderStartAndEndIssueIds = null;
    //订单详情路由映射：key=startIssueId-endIssueId
    private Map<String, TreasureOrderDetailRouter> detailRouterMap = new HashMap<String, TreasureOrderDetailRouter>();
    //订单扩展路由 开始、结束期Id集合
    private long[] detailStartAndEndIssueIds = null;
    //订单扩展表路由映射：key=startIssueId-endIssueId
    private Map<String, TreasureOrderExtensionRouter> orderExtensionRouterMap = new HashMap<String, TreasureOrderExtensionRouter>();
    //订单扩展表路由 开始、结束期Id集合
    private long[] orderExtensionStartAndEndIssueIds = null;
    private volatile boolean isInit = false;

    //通过注入dao实现
    private OrderRouterDao orderRouterDao;
    private OrderDetailRouterDao orderDetailRouterDao;
    private OrderExtensionRouterDao orderExtensionRouterDao;

    /**
     * @param
     * @return
     * @description 数据迁移将老的业务Id转为新的业务id
     * 订单、订单明细 Id组成：（2位库名  append  5位表名后缀 ）* 100000000000 + sequenceId  共19位
     * 其他业务表    Id组成：（2位库名  append  5位10000 ）* 100000000000 + sequenceId  共19位
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/6
     */
    public static long getNewIdByOldId(String tableName, long itemId, long sequenceId) {
        //获取数据库的编号，比如11，12,13,14,15,16,17,18
        long dsSerialNum = getDsSerialNumByItemId(itemId);
        long newId=-1;
        if (tableName.equals(ShardConstants.ORDER_TABLENAME) || tableName.equals(ORDER_DETAIL_TABLENAME)) {
            //订单表 || 订单明细表
            //追加的 10001 其实应该是分库中最小编号的订单表
            newId = getNewId(dsSerialNum, 10001, sequenceId);
        } else {//其他只分库的表对应的Id
            newId = getNewId(dsSerialNum, 0, sequenceId);
        }
        if (newId == -1 || newId < DIV_RAW_ID) {// 小于最小值
            throw new IllegalArgumentException(String.format("getNewId is error, tableName=%s, itemId=%s, oldId=%s", tableName, itemId, sequenceId));
        }
        return newId;
    }


    public static long getDsSerialNumByItemId(long itemId) {
        return 11 + (itemId % DS_CNT);
    }

    /**
     * 获取新的Id
     *
     * @param dsSerialNum     数据库编号
     * @param tableNameSuffix 表名后缀, 当tableNameSuffix==0 时则表示只分库不分表
     * @param sequenceId      顺序号
     * @return
     */
    protected static long getNewId(long dsSerialNum, long tableNameSuffix, long sequenceId) {
        return dsSerialNumAppendTableSuffix(dsSerialNum, tableNameSuffix) * DIV_RAW_ID + sequenceId;
    }

    /**
     * 数据库编号(2位) append 表后缀名(5位，不足则前面补0)
     *
     * @param dsSerialNum
     * @param tableNameSuffix
     * @return
     */
    private static Long dsSerialNumAppendTableSuffix(long dsSerialNum, long tableNameSuffix) {
        return Long.valueOf(dsSerialNum + String.format("%05d", tableNameSuffix));
    }

    /**
     * 根据逻辑表名、Id获取数据库编号
     *
     * @param logicTableName
     * @param id
     * @return
     */
    public static long getDsSerialNumById(String logicTableName, long id) throws Exception {

        //库名编号= id(19位) / 100000000000000000L (18位)
        long dsSerialNum = id / ROW_ID;

        //基础库任务表记录Id使用原始的sequenceId; 分库中的任务Id则按规则来处理   检查数据源库编号  2位 11-99 之间  ,id必须大于基础值
        if (!ShardConstants.TREASURE_TASK_TABLENAME.equals(logicTableName) && (dsSerialNum < 11 || dsSerialNum > 99 || id < getRowId())) {
            throw new Exception();

        }
        return dsSerialNum;
    }

    /**
     * 固定值，用于获取库名前缀
     *
     * @return
     */
    public static long getRowId() {
        return ROW_ID;
    }
    public static long getDivRawId() {
        return DIV_RAW_ID;
    }
    /**
     * Id组成：（2位库名  append  5位表后缀 10000 ）* 100000000000 + sequenceId  共19位
     *
     * @return ID 的转为字符串的长度
     */
    public static long getIDLen() {
        return ID_LEN;
    }
    /**
     * @description 初始化
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/7/6
     */
    private void init(){
        //已经初始化了就直接返回
        if(isInit){
            return;
        }
        isInit=true;
        List<TreasureOrderRouter> orderRouterList = orderRouterDao.selectAll();
        List<TreasureOrderDetailRouter> orderDetailRouterList = orderDetailRouterDao.selectAll();
        List<TreasureOrderExtensionRouter> orderExtensionRouterList = orderExtensionRouterDao.selectAll();

        //订单表
        orderStartAndEndIssueIds = new long[orderRouterList.size() * 2];
        for (int index = 0, count = 0; index < orderRouterList.size(); index++) {
            TreasureOrderRouter orderRouter = orderRouterList.get(index);
            long beginId = orderRouter.getBeginIssueId();
            long endId = orderRouter.getEndIssueId();

            String key = beginId + SEPARATE + endId;
            orderRouterMap.put(key, orderRouter);
            orderStartAndEndIssueIds[count++] = beginId;
            orderStartAndEndIssueIds[count++] = endId;
        }

        //订单明细表
        detailStartAndEndIssueIds = new long[orderDetailRouterList.size() * 2];
        for (int index = 0, count = 0; index < orderDetailRouterList.size(); index++) {
            TreasureOrderDetailRouter orderDetailRouter = orderDetailRouterList.get(index);
            long beginId = orderDetailRouter.getBeginIssueId();
            long endId = orderDetailRouter.getEndIssueId();

            String key = beginId + SEPARATE + endId;
            detailRouterMap.put(key, orderDetailRouter);
            detailStartAndEndIssueIds[count++] = beginId;
            detailStartAndEndIssueIds[count++] = endId;
        }

        //订单扩展表
        orderExtensionStartAndEndIssueIds = new long[orderExtensionRouterList.size() * 2];
        for (int index = 0, count = 0; index < orderExtensionRouterList.size(); index++) {
            TreasureOrderExtensionRouter orderExtensionRouter = orderExtensionRouterList.get(index);
            long beginId = orderExtensionRouter.getBeginIssueId();
            long endId = orderExtensionRouter.getEndIssueId();

            String key = beginId + SEPARATE + endId;
            orderExtensionRouterMap.put(key, orderExtensionRouter);
            orderExtensionStartAndEndIssueIds[count++] = beginId;
            orderExtensionStartAndEndIssueIds[count++] = endId;
        }
    }
    /**
     * 根据抢宝项id、期Id查找路由信息表获取表名
     *
     * @param logicTableName
     * @param itemId
     * @param issueId
     * @return
     */
    public String getTableNameByItemIdAndIssueId(String logicTableName, long itemId, long issueId) {

        // todo issueId 需要砍掉开头 7 位  由于路由表存储的是期Id 实际的 sequenceId
        issueId = getRealIssueId(issueId);

        String tableName = StringUtils.EMPTY;
        if (logicTableName.equals(ShardConstants.ORDER_TABLENAME)) {//订单表
            tableName = getOrderTableName(issueId);
        } else if (logicTableName.equals(ORDER_DETAIL_TABLENAME)) {//订单明细表
            tableName = getDetailTableName(issueId);
        } else if (logicTableName.equals(ShardConstants.ORDER_EXTENSION_TABLENAME)) {//订单扩展表
            tableName = getOrderrExtensionTableName(issueId);
        } else {
            tableName = logicTableName;
        }

        return tableName;
    }
    //获取真实的期Id  砍掉开头 7 位  由于路由表存储的是期Id 实际的 sequenceId
    private long getRealIssueId(long issueId) {

        String issueIdStr = StringUtils.substring(String.valueOf(issueId), 7, String.valueOf(issueId).length());

        if (StringUtils.isBlank(issueIdStr)) {
            //log.error("路由表信息传入了非法的 issueId={}, issueIdStr={}", issueId, issueIdStr);
            throw new IllegalArgumentException("路由表信息传入了非法的 issueId=" + issueId);
        }

        return Long.valueOf(issueIdStr);
    }
    //根据期Id查找订单明细表名
    private String getDetailTableName(long issueId) {
        String orderRouterMapKey = searchIssueIdLoction(detailStartAndEndIssueIds, issueId);
        TreasureOrderDetailRouter orderDetailRouter = detailRouterMap.get(orderRouterMapKey);
        Assert.notNull(orderDetailRouter, String.format("根据 issueId=%s 未找到订单明细路由信息", issueId));
        return orderDetailRouter.getDbTableName();
    }

    //根据期Id查找订单表名
    private String getOrderTableName(long issueId) {
        String orderRouterMapKey = searchIssueIdLoction(orderStartAndEndIssueIds, issueId);
        TreasureOrderRouter orderRouter = orderRouterMap.get(orderRouterMapKey);
        Assert.notNull(orderRouter, String.format("根据 issueId=%s 未找到订单路由信息", issueId));
        return orderRouter.getDbTableName();
    }

    //根据期Id查找订单扩展表名
    private String getOrderrExtensionTableName(long issueId) {
        String orderRouterMapKey = searchIssueIdLoction(orderExtensionStartAndEndIssueIds, issueId);
        TreasureOrderExtensionRouter orderExtensionRouter = orderExtensionRouterMap.get(orderRouterMapKey);
        Assert.notNull(orderExtensionRouter, String.format("根据 issueId=%s 未找到订单路由信息", issueId));
        return orderExtensionRouter.getDbTableName();
    }

    private int getIndex(long[] arr, long issueId) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException(" param arr=" + Arrays.toString(arr) + " is illegal");
        }
        if (issueId < arr[0] || issueId > arr[arr.length - 1]) {
            throw new IllegalArgumentException(" param issueId=" + issueId + " is illegal");
        }
        return Arrays.binarySearch(arr, issueId);
    }

    //根据期Id 查找路由区间 拼接成字符串返回
    private String searchIssueIdLoction(long[] arr, long issueId) {

        int index = getIndex(arr, issueId);

        if (index < 0) {// 小于0的情况出现频次高
            return arr[Math.abs(index) - 2] + SEPARATE + arr[Math.abs(index) - 1];
        }

        if (isEven(index)) {
            return arr[Math.abs(index)] + SEPARATE + arr[Math.abs(index + 1)];
        }

        return arr[Math.abs(index) - 1] + SEPARATE + arr[Math.abs(index)];
    }

    //是偶数
    private boolean isEven(int index) {
        return index % 2 == 0;
    }

    /**
     * 根据抢宝项id、期Id查找路由信息表获取表名编号
     * <p/>
     * 所有分表的表名命名规则都为： 表名_编号, 可以根据split获取表名编号
     *
     * @param logicTableName
     * @param itemId
     * @param issueId
     * @return
     */
    protected long getTableNumByItemIdAndIssueId(String logicTableName, long itemId, long issueId) {
        String tableName = getTableNameByItemIdAndIssueId(logicTableName, itemId, issueId);
        String[] tNameArr = tableName.split("_");
        if (tNameArr.length > 1) {
            return Long.valueOf(tNameArr[tNameArr.length - 1]);
        }

        return 0;
    }

    /**
     * 根据抢宝项Id、期id，生成分库业务表主键 Id
     *
     * @param itemId
     * @param issueId
     * @return
     * @throws RuntimeException 非路由使用，如果参数错误会抛运行时异常
     */
    private long getSequenceId(String logicTableName, long itemId, long issueId) throws Exception {
        SeqIdInfoGenerator idGenerator = SeqIdInfoEnum.getSeqIdInfoEnumByTableName(logicTableName);
        SeqIdInfo seqIdInfo = idGenerator.getSeqIdInfo(sequenceUtil, this, logicTableName, itemId, issueId);
        long id = seqIdInfo.getId();
        long routeId = seqIdInfo.getRouteId();
        long dsSerialNum = seqIdInfo.getDsSerialNum();//数据库编号

        if (id > MAX_RAW_ID) {//快用完了
            //log.warn("请检查序列号大小");
        }
        if (routeId == -1) {
            throw new RuntimeException(String.format("itemId=%s or issueid=%s is error", itemId, issueId));
        }

        return getNewId(dsSerialNum, routeId, id);
    }

    private long getSequenceIdByParam(String logicTableName, long itemId, long issueId, long dataBaseSeq) throws Exception {
        SeqIdInfoGenerator idGenerator = SeqIdInfoEnum.getSeqIdInfoEnumByTableName(logicTableName);

        SeqIdInfo seqIdInfo = null;
        while (seqIdInfo == null) {
            SeqIdInfo originalSeqIdInfo = idGenerator.getSeqIdInfo(sequenceUtil, this, logicTableName, itemId, issueId);
            if (originalSeqIdInfo.getDsSerialNum() == dataBaseSeq) {
                seqIdInfo = originalSeqIdInfo;
            }
        }

        if (seqIdInfo.getId() > MAX_RAW_ID) {//快用完了
          //  log.warn("请检查序列号大小");
        }
        if (seqIdInfo.getRouteId() == -1) {
            throw new RuntimeException(String.format("itemId=%s or issueid=%s is error", itemId, issueId));
        }

        return getNewId(seqIdInfo.getDsSerialNum(), seqIdInfo.getRouteId(), seqIdInfo.getId());
    }

    public String getTableNameByHash(String logicTableName, long shardCondition) {
        Long size = hashTableSizeMap.get(logicTableName);
        return size == null ? null : logicTableName + "_" + ((shardCondition % size) + 1);
    }

    public String getTableNameByHash(String logicTableName, String shardCondition) {
        long hashCode = Math.abs(hashCode(shardCondition));
        return getTableNameByHash(logicTableName, hashCode);
    }

    public String getTableNameById(String logicTableName, long id) {
        // 截取的表名编号可能为：10001(按规则分表)   00001(哈希分表)    00000(只分库)
        String tableNameSuffix = StringUtils.substring(id + "", 2, 7);
        tableNameSuffix = Long.valueOf(tableNameSuffix) == 0 ? "" : Long.valueOf(tableNameSuffix) + "";
        //String tableName = logicTableName + tableNameSuffix;
        return logicTableName + "_" + tableNameSuffix;
    }

    private int hashCode(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        int h = 0;
        int len = str.length();
        char val[] = str.toLowerCase().toCharArray();

        for (int i = 0; i < len; i++) {
            h = 31 * h + val[i];
        }
        return h;
    }

    /**
     * 生成订单明细ID
     *
     * @param itemId
     * @param issueId
     * @return
     * @throws RuntimeException 非路由使用，如果参数错误会抛运营时异常
     */
    public long getOrderDetailSequence(long itemId, long issueId) throws Exception {
        return getSequenceId(ORDER_DETAIL_TABLENAME, itemId, issueId);
    }

    /**
     * 生成订单ID
     *
     * @param itemId
     * @param issueId
     * @return
     */
    public long getOrderSequence(long itemId, long issueId) throws Exception {
        return getSequenceId(ShardConstants.ORDER_TABLENAME, itemId, issueId);
    }

    /**
     * 生成订单扩展表ID
     *
     * @param itemId
     * @param issueId
     * @return
     */
    public long getOrderExtensionSequence(long itemId, long issueId) throws Exception {
        return getSequenceId(ShardConstants.ORDER_EXTENSION_TABLENAME, itemId, issueId);
    }

    /**
     * 生成抢宝项ID
     *
     * @return
     */
    public long getItemSequence() throws Exception {
        return getSequenceId(ShardConstants.TREASURE_ITEM_TABLENAME, 0, 0);
    }

    public long getItemSequenceByParam(long dataBaseSeq) throws Exception {
        return getSequenceIdByParam(ShardConstants.TREASURE_ITEM_TABLENAME, 0, 0, dataBaseSeq);
    }

    /**
     * 生成抢宝期ID
     *
     * @return
     */
    public long getIssueSequence(long itemId) throws Exception {
        return getSequenceId(ShardConstants.TREASURE_ISSUE_TABLENAME, itemId, 0);
    }

    public long getCodeSequence(long itemId) throws Exception {// 抢宝号
        return getSequenceId(ShardConstants.CODE_TABLENAME, itemId, 0);
    }

    public long getItemVenderSequence(long itemId) throws Exception {// 夺宝项商家关系表
        return getSequenceId(ShardConstants.TREASURE_ITEM_VENDER_TABLENAME, itemId, 0);
    }

    public long getRefundSequence(long itemId) throws Exception {//	退款表
        return getSequenceId(ShardConstants.TREASURE_REFUND_TABLENAME, itemId, 0);
    }

    public long getCardSequence(long itemId) throws Exception {//	卡密表
        return getSequenceId(ShardConstants.TREASURE_CARD_TABLENAME, itemId, 0);
    }

    public long getSendAwardSequence(long itemId) throws Exception {//	派奖表
        return getSequenceId(ShardConstants.TREASURE_SEND_AWARD_TABLENAME, itemId, 0);
    }

    public long getTaskSequence(long itemId) throws Exception {//	任务流水表
        return getSequenceId(ShardConstants.TREASURE_TASK_TABLENAME, itemId, 0);
    }

    public long getOpenAwardSequence(long itemId) throws Exception {//	开奖记录表
        return getSequenceId(ShardConstants.TREASURE_OPEN_AWARD_TABLENAME, itemId, 0);
    }

    public long getCountAwardSequence(long itemId) throws Exception {//	算奖记录表
        return getSequenceId(ShardConstants.TREASURE_COUNT_AWARD_TABLENAME, itemId, 0);
    }

    public long getBasicCountAwardSequence(long itemId) throws Exception {//	算奖记录基础表
        return getSequenceId(ShardConstants.TREASURE_BASIC_COUNT_AWARD_TABLENAME, itemId, 0);
    }

    public long getBuyStatSequence(long itemId) throws Exception {//	购买次数统计表
        return getSequenceId(ShardConstants.TREASURE_BUY_STAT_TABLENAME, itemId, 0);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        if(hashTableSizeMap==null||hashTableSizeMap.size()<1){
            throw new Exception();
        }
        init();
        Assert.notNull(orderStartAndEndIssueIds,"订单路由表信息加载失败");
        Assert.notNull(detailStartAndEndIssueIds,"订单详情路右边信息加载失败");
        Assert.notNull(orderExtensionStartAndEndIssueIds,"订单扩展表信息加载失败");
    }
}
