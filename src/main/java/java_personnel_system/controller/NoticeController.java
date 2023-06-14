package java_personnel_system.controller;

import java_personnel_system.service.NoticeService;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;
import java_personnel_system.view.OrdinaryUserView;

/**
 * @auther Rachel
 * @date 2023/6/12 14:10
 */
public class NoticeController {
    public static void noticeSelectViewController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.selectAllNoticeView();
                if (MainView.currentUser.getUserAuthority()==0){
                    ManagerView.noticeView();
                }
                OrdinaryUserView.ordinaryUserView();
                break;
            case "2":
                MainView.noticeLikeSelectView();
                if (MainView.currentUser.getUserAuthority()==0){
                    ManagerView.noticeView();
                }
                OrdinaryUserView.ordinaryUserView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.noticeSelectView();
        }
    }

    public static void noticeLikeSelectController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.selectNoticeNameView();
                ManagerView.noticeView();
                break;
            case "2":
                MainView.selectNoticeContentView();
                ManagerView.noticeView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.noticeLikeSelectView();
        }
    }
}
