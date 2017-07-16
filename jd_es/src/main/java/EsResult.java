import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public class EsResult<T> implements Serializable {
    private static final long serialVersionUID = -1422287752717698961L;
    private boolean success;
    private String content;
    private long consumeTime = -1L;
    private int pageNum = 1;
    private int pageSize = 20;
    private int totalCount;
    private List<Map<String, T>> data = new ArrayList();

    public EsResult() {
    }

    public EsResult(int pageNum, int pageSize) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        }
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalPages() {
        int _total = this.totalCount / this.pageSize;
        if (this.totalCount % this.pageSize != 0) {
            ++_total;
        }

        return _total;
    }

    public int getCurrentPageNo() {
        return this.getStartIndex() / this.pageSize + 1;
    }

    public int getStartIndex() {
        return (this.pageNum - 1) * this.pageSize;
    }

    public int getEndIndex() {
        int k = this.getStartIndex() + this.pageSize;
        return k < this.totalCount ? k - 1 : this.totalCount - 1;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getConsumeTime() {
        return this.consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Map<String, T>> getData() {
        return this.data;
    }

    public void setData(List<Map<String, T>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EsResult{" +
                "success=" + success +
                ", content='" + content + '\'' +
                ", consumeTime=" + consumeTime +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", data=" + data +
                '}';
    }
}
