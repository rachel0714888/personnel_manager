package java_personnel_system.service;

import java_personnel_system.DAO.NoticeDao;
import java_personnel_system.DAO.NoticeDaoimpl;
import java_personnel_system.pojo.Notice;
import java_personnel_system.pojo.Position;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/12 13:19
 */
public class NoticeService {
    private static NoticeDao noticeDao = new NoticeDaoimpl();

    public static void addNotice(String noticeContent,String noticeName) throws Exception {
        noticeDao.addNotice(new Notice(noticeContent, noticeName));
    }

    public static void removeNotice(int noticeId) throws Exception {
        if (!noticeDao.noticeIdExist(noticeId)){
            Print.print("公告不存在");
            ManagerView.removeNoticeView();
        }
        noticeDao.removeNotice(noticeId);
        noticeDao.noticeAlter();
    }

    public static void updateNotice(Notice notice) throws Exception {
        if (!noticeDao.noticeIdExist(notice.getNoticeId())){
            Print.print("公告不存在，请重新输入");
            ManagerView.updateNoticeView();
        }
        noticeDao.updateNotice(notice);
    }

    public static void selectAllNotice() throws Exception {
        noticeDao.selectAllNotice();
    }

    public static void selectNoticeName(String noticeName) throws Exception {
        if (!noticeDao.noticeNameExist(noticeName)){
            Print.print("公告不存在，请重新输入");
            MainView.noticeLikeSelectView();
        }
        noticeDao.selectNoticeName(noticeName);
    }

    public static void selectNoticeContent(String noticeContent) throws Exception {
        if (!noticeDao.noticeContentExist(noticeContent)){
            Print.print("公告不存在，请重新输入");
            MainView.noticeLikeSelectView();
        }
        noticeDao.selectNoticeContent(noticeContent);
    }
}
