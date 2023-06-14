package java_personnel_system.controller;

import java_personnel_system.DAO.UserDao;
import java_personnel_system.DAO.UserDaoimpl;
import java_personnel_system.service.StaffService;
import java_personnel_system.service.UserService;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.OrdinaryUserView;

/**
 * @auther Rachel
 * @date 2023/6/12 14:51
 */
public class OrdinaryUserController {

    public static void ordinaryUserController(String key) throws Exception {
        switch (key){
            case "1":
                OrdinaryUserView.userView();
                OrdinaryUserView.ordinaryUserView();
                break;
            case "2":
                OrdinaryUserView.staffView();
                OrdinaryUserView.ordinaryUserView();
                break;
            case "3":
                MainView.noticeSelectView();
                OrdinaryUserView.ordinaryUserView();
                break;
            default:
                Print.print("输入有误");
                OrdinaryUserView.ordinaryUserView();
        }
    }

    public static void userController(String key) throws Exception {
        switch (key){
            case "1":
                Print.ordinaryUserMsgPrint(MainView.currentUser);
                OrdinaryUserView.userView();
                break;
            case "2":
                OrdinaryUserView.userChangeView();
                OrdinaryUserView.userView();
                break;
            default:
                Print.print("输入有误");
                OrdinaryUserView.userView();
        }
    }

    public static void staffController(String key) throws Exception {
        switch (key){
            case "1":
                StaffService.selectStaffIdNoInput(MainView.currentUser.getUserStaffId());
                OrdinaryUserView.ordinaryUserView();
                break;
            case "2":
                OrdinaryUserView.ordinaryUserUpdateStaffMsgView();
                OrdinaryUserView.ordinaryUserView();
                break;
            default:
                Print.print("输入有误");
                OrdinaryUserView.staffView();
        }
    }

    public static void staffUpdateController(String key)throws Exception {
        switch (key) {
            case "1":
                MainView.updateStaffNameView();
                OrdinaryUserView.staffView();
                break;
            case "2":
                MainView.updateStaffSexView();
                OrdinaryUserView.staffView();
                break;
            case "3":
                MainView.updateStaffNationView();
                OrdinaryUserView.staffView();
                break;
            case "4":
                MainView.updateStaffIdentityidView();
                OrdinaryUserView.staffView();
                break;
            case "5":
                MainView.updateStaffPhonenumView();
                OrdinaryUserView.staffView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                OrdinaryUserView.updateStaffView();
        }
    }
}
