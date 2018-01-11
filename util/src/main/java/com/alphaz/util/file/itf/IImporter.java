package com.alphaz.util.file.itf;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.file.itf
 * User: C0dEr
 * Date: 2017/3/23
 * Time: 上午11:46
 * Description:This is a class of com.alphaz.util.file.itf
 */
public interface IImporter {

    void excute();

    boolean convertData();

    boolean readFile(boolean isCheckContainer);

}