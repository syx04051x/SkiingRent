package com.alphaz.task.processor;

import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.task.task
 * User: C0dEr
 * Date: 2017/6/23
 * Time: 下午3:22
 * Description:This is a class of com.alphaz.task.task
 */
@Component
public class Process implements ItemProcessor<UserViewModel, UserViewModel> {
    @Override
    public UserViewModel process(UserViewModel item) {
        return null;
    }
}
