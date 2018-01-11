package com.alphaz.task.writer;

import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.task.writer
 * User: C0dEr
 * Date: 2017/6/23
 * Time: 下午3:55
 * Description:This is a class of com.alphaz.task.writer
 */
@Component
public class Writer implements ItemWriter<UserViewModel> {
    @Override
    public void write(List<? extends UserViewModel> items) throws Exception {

    }
}
