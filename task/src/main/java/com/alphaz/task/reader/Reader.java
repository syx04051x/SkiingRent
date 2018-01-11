package com.alphaz.task.reader;

import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.task.reader
 * User: C0dEr
 * Date: 2017/6/23
 * Time: 下午3:55
 * Description:This is a class of com.alphaz.task.reader
 */
@Component
public class Reader implements ItemReader<UserViewModel> {
    @Override
    public UserViewModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
