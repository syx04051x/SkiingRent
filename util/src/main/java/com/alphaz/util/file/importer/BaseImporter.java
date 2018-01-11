package com.alphaz.util.file.importer;

import com.alphaz.util.file.itf.IImporter;
import com.alphaz.util.io.FileUtil;
import com.alphaz.util.valid.ValideHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.file.importer
 * User: C0dEr
 * Date: 2017/3/23
 * Time: 上午11:43
 * Description:This is a class of com.alphaz.util.file.importer
 */
public class BaseImporter<T> implements IImporter {
    private String FilePath;
    protected String EntityName;
    private List<String> SourceData;
    protected List<List<String>> HandledData;
    public String org;

    public BaseImporter(String entityName, String path, String org) {
        FilePath = path + "/" + entityName;
        EntityName = entityName;
        HandledData = new ArrayList<>();
        this.org = org;
    }


    public void excute() {
        if (!readFile(true)) {
            return;
        }
        if (!convertData()) {
            return;
        }
        processData();
    }

    @Override
    public boolean convertData() {
        for (String line : SourceData) {
            List<String> str = Arrays.asList(line.split("\\|"));
            HandledData.add(str);
        }
        if (ValideHelper.isNullOrEmpty(HandledData)) {
            return false;
        }
        return true;
    }


    @Override
    public boolean readFile(boolean isCheckContainer) {
        SourceData = FileUtil.readFileByline(FilePath);
        if (ValideHelper.isNullOrEmpty(SourceData)) {
            return false;
        }
        if (isCheckContainer) {
            if (!SourceData.get(0).trim().equals("BEGIN") || !SourceData.get(SourceData.size()).equals("END")) {
                return false;
            } else {
                SourceData = SourceData.subList(1, SourceData.size() - 1);
            }
        }
        return true;
    }


    public void processData() {

    }


}
