package java_personnel_system.controller;

import java_personnel_system.service.StaffService;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;
import java_personnel_system.view.OrdinaryUserView;
import java_personnel_system.view.PersonnelView;

/**
 * @auther Rachel
 * @date 2023/6/12 10:18
 */
public class StaffController {

    public static void staffController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.staffAddView();
                MainView.staffView();
                break;
            case "2":
                MainView.staffRemoveView();
                MainView.staffView();
                break;
            case "3":
                MainView.updateStaffView();
                MainView.staffView();
                break;
            case "4":
                MainView.staffSelectView();
                MainView.staffView();
                break;
            case "5":
                if (MainView.currentUser.getUserAuthority()==0){
                    ManagerView.managerView();
                }
                if (MainView.currentUser.getUserAuthority()==1){
                    PersonnelView.personnelView();
                }
                if (MainView.currentUser.getUserAuthority()==2){
                    OrdinaryUserView.ordinaryUserView();
                }
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.staffView();
        }
    }

    public static void staffRemoveController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.removeStaffIdView();
                break;
            case "2":
                MainView.removeStaffNameView();
                break;
            case "3":
                MainView.staffView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.staffRemoveView();
        }
    }

    public static void staffUpdateController(String key)throws Exception{
        switch (key) {
            case "1":
                MainView.updateStaffNameView();
                MainView.staffView();
                break;
            case "2":
                MainView.updateStaffSexView();
                MainView.staffView();
                break;
            case "3":
                MainView.updateStaffDepartmentIdView();
                MainView.staffView();
                break;
            case "4":
                MainView.updateStaffPositionIdView();
                MainView.staffView();
                break;
            case "5":
                MainView.updateStaffNationView();
                MainView.staffView();
                break;
            case "6":
                MainView.updateStaffEducationView();
                MainView.staffView();
                break;
            case "7":
                MainView.updateStaffIdentityidView();
                MainView.staffView();
                break;
            case "8":
                MainView.updateStaffPhonenumView();
                MainView.staffView();
                break;
            case "9":
                MainView.updateStaffMsgView();
                MainView.staffView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.updateStaffView();
        }
    }

    public static void staffSelectController(String key) throws Exception {
        switch (key) {
            case "1":
                StaffService.selectAllStaffMsg();
                MainView.staffView();
                break;
            case "2":
                MainView.staffLikeSelectView();
                MainView.staffView();
                break;
            case "3":
                MainView.staffView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.staffSelectView();
        }
    }

    public static void staffLikeSelectController(String key) throws Exception {
        switch (key) {
            case "1":
                MainView.selectStaffIdView();
                MainView.staffView();
                break;
            case "2":
                MainView.selectStaffNameView();
                MainView.staffView();
                break;
            case "3":
                MainView.selectStaffSexView();
                MainView.staffView();
                break;
            case "4":
                MainView.selectStaffDepartmentIdView();
                MainView.staffView();
                break;
            case "5":
                MainView.selectStaffPrositionIdView();
                MainView.staffView();
                break;
            case "6":
                MainView.selectStaffNationView();
                MainView.staffView();
                break;
            case "7":
                MainView.selectStaffEducationView();
                MainView.staffView();
                break;
            case "8":
                MainView.selectStaffIdentityIdView();
                MainView.staffView();
                break;
            case "9":
                MainView.selectStaffPhonenumView();
                MainView.staffView();
                break;
            default:
                Print.print("输入有误，请重新输入");
                MainView.staffLikeSelectView();
        }
    }
}
