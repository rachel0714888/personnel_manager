package java_personnel_system;

import static org.junit.Assert.assertTrue;

import java_personnel_system.pojo.User;
import java_personnel_system.service.*;
import java_personnel_system.util.ConnectionPool;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>());

    @Test
    public void test() throws Exception {
        //每有一个用户使用系统，为他分配一个线程
        threadPool.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                MainView.mainView();
            }
        });
    }

    /**
     * 用户添加
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void userAdd() throws Exception {
        MainView.userAddView();
    }

    /**
     * 用户删除
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void userRemove() throws Exception {
        MainView.userRemoveView();
    }

    /**
     * 用户修改
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void userChange() throws Exception {
        MainView.userChangeView();
    }

    /**
     * 用户查询
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void userSelect() throws Exception {
        MainView.userSelectView();
    }

    /**
     * 员工增加
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void staffAdd() throws Exception {
        MainView.staffAddView();
    }

    /**
     * 员工信息修改
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void staffChange() throws Exception {
        MainView.updateStaffView();
    }

    /**
     * 员工信息搜索
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void staffSelect() throws Exception {
        MainView.staffSelectView();
    }

    /**
     * 员工删除
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void staffRemove() throws Exception {
        MainView.staffRemoveView();
    }

    /**
     * 增加职位
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void positionAdd() throws Exception {
        ManagerView.addPositionView();
    }

    /**
     * 更改职位信息
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void positionUpdate() throws Exception {
        ManagerView.changePositionMsgView();
    }

    /**
     * 查询职位信息
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void positionSelect() throws Exception {
        ManagerView.positionSelectView();
    }

    /**
     * 删除职位
     *
     * @throws Exception 抛出异常
     */
    @Test
    public void positionRemove() throws Exception {
        ManagerView.positionRemoveView();
    }

    /**增加公告
     * @throws Exception 抛出异常
     */
    @Test
    public void noticeAdd() throws Exception {
        ManagerView.addNoticeView();
    }

    /**更改公告
     * @throws Exception 抛出异常
     */
    @Test
    public void noticeUpdate()throws Exception{
        ManagerView.updateNoticeView();
    }

    /**公告查询
     * @throws Exception 抛出异常
     */
    @Test
    public void noticeSelect()throws Exception{
        MainView.noticeSelectView();
    }

    /**公告删除
     * @throws Exception 抛出异常
     */
    @Test
    public void noticeRemove()throws Exception{
        ManagerView.removeNoticeView();
    }

    /**增加部门
     * @throws Exception 抛出异常
     */
    @Test
    public void departmentAdd()throws Exception{
        ManagerView.addDepartmentView();
    }

    /**更改部门信息（由于不是从部门页面进的所以这里最后没有跳转）
     * @throws Exception 抛出异常
     */
    @Test
    public void departmentUpdate()throws Exception{
        ManagerView.changeDepartmentMsgView();
    }

    /**删除部门
     * @throws Exception 抛出异常
     */
    @Test
    public void departmentRemove()throws Exception{
        ManagerView.departmentRemoveView();
    }


}