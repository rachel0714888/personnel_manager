package java_personnel_system.DAO;

import java_personnel_system.pojo.Notice;

/**
 * @auther Rachel
 * @date 2023/6/12 10:13
 */
public interface NoticeDao {
    /**
     * 增加公告
     *
     * @param notice 存放要增加的公告内容和公告名
     * @throws Exception 抛出异常
     */
    void addNotice(Notice notice) throws Exception;

    /**
     * 根据输入的公告id删除公告
     *
     * @param noticeId 存放要删除的公告的公告id
     * @throws Exception 抛出异常
     */
    void removeNotice(int noticeId) throws Exception;

    void noticeAlter() throws Exception;

    /**
     * 根据输入的公告id确定要更改的公告 将输入的公告内容和名字更新到对应的数据上
     *
     * @param notice 存放要更改的公告的id和要更新的公告内容和名字
     * @throws Exception 抛出异常
     */
    void updateNotice(Notice notice) throws Exception;


    /**
     * 查询所有公告
     *
     * @throws Exception 抛出异常
     */
    void selectAllNotice() throws Exception;


    /**根据输入的公告名查询公告信息
     * @param noticeName 存放要查询的公告的公告名或部分公告名
     * @throws Exception 抛出异常
     */
    void selectNoticeName(String noticeName) throws Exception;

    /**根据输入的公告内容查询公告信息
     * @param noticeContent 存放要查询的公告的公告内容或部分公告内容
     * @throws Exception 抛出异常
     */
    void selectNoticeContent(String noticeContent) throws Exception;

    /**根据公告id判断公告是否存在
     * @param noticeId 要查询的公告id
     * @return 存在返回true
     * @throws Exception 抛出异常
     */
    boolean noticeIdExist(int noticeId) throws Exception;

    /**根据公告名判断公告是否存在
     * @param noticeName 要查询的公告名
     * @return 存在返回true
     * @throws Exception 抛出异常
     */
    boolean noticeNameExist(String noticeName) throws Exception;

    /**根据公告内容判断公告是否存在
     * @param noticeContent 要查询的公告内容
     * @return 存在返回true
     * @throws Exception 抛出异常
     */
    boolean noticeContentExist(String noticeContent) throws Exception;
}
