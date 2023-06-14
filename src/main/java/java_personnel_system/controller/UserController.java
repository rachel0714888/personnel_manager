package java_personnel_system.controller;

import java_personnel_system.service.UserService;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;
import java_personnel_system.view.PersonnelView;

/**
 * @auther Rachel
 * @date 2023/6/11 21:17
 */
public class UserController {

    //总经理和人事部长的用户管理
    public static void userController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.userAddView();
                MainView.adminUserView();
                break;
            case "2":
                MainView.userRemoveView();
                MainView.adminUserView();
                break;
            case "3":
                MainView.userChangeView();
                MainView.adminUserView();
                break;
            case "4":
                MainView.userAllSelectView();
                MainView.adminUserView();
                break;
            case "5":
                if (MainView.currentUser.getUserAuthority() == 0) {
                    ManagerView.managerView();
                }
                if (MainView.currentUser.getUserAuthority() == 1) {
                    PersonnelView.personnelView();
                }
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.adminUserView();
        }
    }

    public static void userChangeController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.changeUserNameView();
                MainView.adminUserView();
                break;
            case "2":
                MainView.changeUserPasswordView();
                MainView.adminUserView();
                break;
            case "3":
                MainView.changeUserStaffidView();
                MainView.adminUserView();
                break;
            case "4":
                MainView.changeUserIsOnworkView();
                MainView.adminUserView();
                break;
            case "5":
                MainView.changeUserAuthorityView();
                MainView.adminUserView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.userChangeView();
        }
    }

    public static void userSelectController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.userAllSelectView();
                MainView.adminUserView();
                break;
            case "2":
                MainView.userLikeSelectView();
                MainView.adminUserView();
                break;
            case "3":
                MainView.adminUserView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.userSelectView();
        }
    }

    public static void userLikeSelectController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.userLikeNameSelectView();
                MainView.adminUserView();
                break;
            case "2":
                MainView.userLikeIsOnworkSelectView();
                MainView.adminUserView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.userLikeSelectView();
        }
    }

    public static void userRemoveController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.userIdRemoveView();
                MainView.adminUserView();
                break;
            case "2":
                MainView.userNameRemoveView();
                MainView.adminUserView();
                break;
            case "3":
                MainView.userStaffIdRemoveView();
                MainView.adminUserView();
                break;
            case "4":
                MainView.userIsworkRemoveView();
                MainView.adminUserView();
                break;
            case "5":
                MainView.adminUserView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.userRemoveView();
        }
    }


}

